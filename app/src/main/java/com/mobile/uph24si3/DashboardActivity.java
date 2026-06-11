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
    private TextView tvWelcome, tvDashNama, tvDashUsername, tvDashTTL, tvDashHobi, tvDashBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_dashboard);

        ivDashProfile = findViewById(R.id.ivDashProfile);
        tvWelcome = findViewById(R.id.tvWelcome);
        tvDashNama = findViewById(R.id.tvDashNama);
        tvDashUsername = findViewById(R.id.tvDashUsername);
        tvDashTTL = findViewById(R.id.tvDashTTL);
        tvDashHobi = findViewById(R.id.tvDashHobi);
        tvDashBio = findViewById(R.id.tvDashBio);

        loadProfileData();
    }

    private void loadProfileData() {
        SharedPreferences sharedPreferences = getSharedPreferences("ProfileData", MODE_PRIVATE);
        
        String nama = sharedPreferences.getString("NAMA", "-");
        String username = sharedPreferences.getString("USERNAME", "admin");
        String tempat = sharedPreferences.getString("TEMPAT", "-");
        String tanggal = sharedPreferences.getString("TANGGAL", "-");
        String hobi = sharedPreferences.getString("HOBI", "-");
        String bio = sharedPreferences.getString("BIO", "-");
        String imageUriStr = sharedPreferences.getString("IMAGE_URI", null);

        tvWelcome.setText("Welcome, " + nama);
        tvDashNama.setText("Nama Lengkap: " + nama);
        tvDashUsername.setText("Username: " + username);
        tvDashTTL.setText("Tempat, Tgl Lahir: " + tempat + ", " + tanggal);
        tvDashHobi.setText("Hobi: " + hobi);
        tvDashBio.setText(bio);

        if (imageUriStr != null) {
            try {
                ivDashProfile.setImageURI(Uri.parse(imageUriStr));
            } catch (Exception e) {
                // Jika terjadi error (misal: permission), gunakan gambar default
                ivDashProfile.setImageResource(R.drawable.ic_launcher_foreground);
            }
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
        loadProfileData();
    }
}
