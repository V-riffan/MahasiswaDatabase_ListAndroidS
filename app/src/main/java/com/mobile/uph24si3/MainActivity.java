package com.mobile.uph24si3;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etAngka1, etAngka2;
    private TextView tvHasil;
    private Button btnTambah, btnKurang, btnKali, btnBagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hubungkan komponen XML ke Java
        etAngka1 = findViewById(R.id.etAngka1);
        etAngka2 = findViewById(R.id.etAngka2);

        tvHasil = findViewById(R.id.tvHasil);

        btnTambah = findViewById(R.id.btnTambah);
        btnKurang = findViewById(R.id.btnKurang);
        btnKali = findViewById(R.id.btnKali);
        btnBagi = findViewById(R.id.btnBagi);

        // Tombol tambah
        btnTambah.setOnClickListener(v -> hitung("+"));

        // Tombol kurang
        btnKurang.setOnClickListener(v -> hitung("-"));

        // Tombol kali
        btnKali.setOnClickListener(v -> hitung("*"));

        // Tombol bagi
        btnBagi.setOnClickListener(v -> hitung("/"));
    }

    private void hitung(String operator) {

        String angka1Str = etAngka1.getText().toString().trim();
        String angka2Str = etAngka2.getText().toString().trim();

        // Validasi input kosong
        if (angka1Str.isEmpty() || angka2Str.isEmpty()) {
            Toast.makeText(this, "Masukkan kedua angka!", Toast.LENGTH_SHORT).show();
            return;
        }

        double angka1 = Double.parseDouble(angka1Str);
        double angka2 = Double.parseDouble(angka2Str);

        double hasil = 0;

        switch (operator) {
            case "+":
                hasil = angka1 + angka2;
                break;

            case "-":
                hasil = angka1 - angka2;
                break;

            case "*":
                hasil = angka1 * angka2;
                break;

            case "/":
                if (angka2 == 0) {
                    Toast.makeText(this, "Tidak bisa dibagi dengan nol!", Toast.LENGTH_SHORT).show();
                    return;
                }
                hasil = angka1 / angka2;
                break;
        }

        tvHasil.setText("Hasil: " + hasil);
    }
}