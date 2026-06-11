package com.mobile.uph24si3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private ImageView ivDashProfile;
    private TextView tvDashNama, tvDashTTL, tvDashGender, tvDashJurusan, tvDashStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ivDashProfile = findViewById(R.id.ivDashProfile);
        tvDashNama = findViewById(R.id.tvDashNama);
        tvDashTTL = findViewById(R.id.tvDashTTL);
        tvDashGender = findViewById(R.id.tvDashGender);
        tvDashJurusan = findViewById(R.id.tvDashJurusan);
        tvDashStatus = findViewById(R.id.tvDashStatus);

        loadProfileData();
    }

    private void loadProfileData() {
        SharedPreferences sharedPreferences = getSharedPreferences("ProfileData", MODE_PRIVATE);
        
        String nama = sharedPreferences.getString("NAMA", "-");
        String tempat = sharedPreferences.getString("TEMPAT", "-");
        String tanggal = sharedPreferences.getString("TANGGAL", "-");
        String gender = sharedPreferences.getString("GENDER", "-");
        String jurusan = sharedPreferences.getString("JURUSAN", "-");
        String status = sharedPreferences.getString("STATUS", "-");
        String imageUriStr = sharedPreferences.getString("IMAGE_URI", null);

        tvDashNama.setText("Nama: " + nama);
        tvDashTTL.setText("TTL: " + tempat + ", " + tanggal);
        tvDashGender.setText("Jenis Kelamin: " + gender);
        tvDashJurusan.setText("Jurusan: " + jurusan);
        tvDashStatus.setText("Status: " + status);

        if (imageUriStr != null) {
            ivDashProfile.setImageURI(Uri.parse(imageUriStr));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.menu_edit_profile) {
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_logout) {
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProfileData(); // Refresh data when returning from Edit Profile
    }
}
