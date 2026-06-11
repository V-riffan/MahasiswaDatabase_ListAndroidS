package com.mobile.uph24si3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ImageView ivProfile;
    private Button btnPilihFoto, btnSimpan;
    private EditText etNamaLengkap, etTempatLahir, etTanggalLahir, etHobi, etBio;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        // Initialize views
        ivProfile = findViewById(R.id.ivProfile);
        btnPilihFoto = findViewById(R.id.btnPilihFoto);
        btnSimpan = findViewById(R.id.btnSimpan);
        etNamaLengkap = findViewById(R.id.etNamaLengkap);
        etTempatLahir = findViewById(R.id.etTempatLahir);
        etTanggalLahir = findViewById(R.id.etTanggalLahir);
        etHobi = findViewById(R.id.etHobi);
        etBio = findViewById(R.id.etBio);

        loadExistingData();

        // Date Picker
        etTanggalLahir.setOnClickListener(v -> showDatePicker());

        // Select Photo
        ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        imageUri = result.getData().getData();
                        
                        // Menambahkan persistable permission agar URI tetap bisa diakses setelah app restart
                        try {
                            getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        } catch (Exception e) {
                            // Beberapa provider mungkin tidak mendukung persistable permission
                        }
                        
                        ivProfile.setImageURI(imageUri);
                    }
                }
        );

        btnPilihFoto.setOnClickListener(v -> {
            // Menggunakan ACTION_OPEN_DOCUMENT agar bisa menggunakan takePersistableUriPermission
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            pickImageLauncher.launch(intent);
        });

        // Simpan button
        btnSimpan.setOnClickListener(v -> {
            if (validateInputs()) {
                saveData();
                Toast.makeText(this, "Profile Berhasil Diupdate", Toast.LENGTH_SHORT).show();
                finish(); // Langsung kembali ke Dashboard
            } else {
                Toast.makeText(this, "Mohon lengkapi semua data profile!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadExistingData() {
        SharedPreferences sharedPreferences = getSharedPreferences("ProfileData", MODE_PRIVATE);
        etNamaLengkap.setText(sharedPreferences.getString("NAMA", ""));
        etTempatLahir.setText(sharedPreferences.getString("TEMPAT", ""));
        etTanggalLahir.setText(sharedPreferences.getString("TANGGAL", ""));
        etHobi.setText(sharedPreferences.getString("HOBI", ""));
        etBio.setText(sharedPreferences.getString("BIO", ""));

        String imageUriStr = sharedPreferences.getString("IMAGE_URI", null);
        if (imageUriStr != null) {
            try {
                imageUri = Uri.parse(imageUriStr);
                ivProfile.setImageURI(imageUri);
            } catch (Exception e) {
                ivProfile.setImageResource(R.drawable.ic_launcher_foreground);
            }
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("ProfileData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        
        editor.putString("NAMA", etNamaLengkap.getText().toString());
        editor.putString("TEMPAT", etTempatLahir.getText().toString());
        editor.putString("TANGGAL", etTanggalLahir.getText().toString());
        editor.putString("HOBI", etHobi.getText().toString());
        editor.putString("BIO", etBio.getText().toString());
        
        if (imageUri != null) {
            editor.putString("IMAGE_URI", imageUri.toString());
        }
        
        editor.apply();
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> etTanggalLahir.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1),
                year, month, day);
        datePickerDialog.show();
    }

    private boolean validateInputs() {
        String nama = etNamaLengkap.getText().toString().trim();
        String tempat = etTempatLahir.getText().toString().trim();
        String tanggal = etTanggalLahir.getText().toString().trim();
        String hobi = etHobi.getText().toString().trim();
        String bio = etBio.getText().toString().trim();

        return !nama.isEmpty() && !tempat.isEmpty() && !tanggal.isEmpty() && !hobi.isEmpty() && !bio.isEmpty();
    }
}
