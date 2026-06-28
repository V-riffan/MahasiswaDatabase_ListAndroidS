package com.mobile.uph24si3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText etNim, etNama, etSearch;
    private Button btnTambah, btnUpdate, btnHapus, btnSort;
    private ListView lvMahasiswa;
    
    private DatabaseHelper dbHelper;
    private List<Mahasiswa> listMahasiswa;
    private MahasiswaAdapter adapter;
    private int selectedId = -1;
    private String sortOrder = "ASC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etSearch = findViewById(R.id.etSearch);
        btnTambah = findViewById(R.id.btnTambah);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnHapus = findViewById(R.id.btnHapus);
        btnSort = findViewById(R.id.btnSort);
        lvMahasiswa = findViewById(R.id.lvMahasiswa);

        refreshData();

        btnTambah.setOnClickListener(v -> {
            String nim = etNim.getText().toString();
            String nama = etNama.getText().toString();
            if (!nim.isEmpty() && !nama.isEmpty()) {
                dbHelper.addMahasiswa(nim, nama);
                clearForm();
                refreshData();
                Toast.makeText(this, "Data ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(v -> {
            if (selectedId != -1) {
                String nim = etNim.getText().toString();
                String nama = etNama.getText().toString();
                dbHelper.updateMahasiswa(selectedId, nim, nama);
                clearForm();
                refreshData();
                Toast.makeText(this, "Data diupdate", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Pilih data yang akan diupdate", Toast.LENGTH_SHORT).show();
            }
        });

        btnHapus.setOnClickListener(v -> {
            if (selectedId != -1) {
                dbHelper.deleteMahasiswa(selectedId);
                clearForm();
                refreshData();
                Toast.makeText(this, "Data dihapus", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Pilih data yang akan dihapus", Toast.LENGTH_SHORT).show();
            }
        });

        btnSort.setOnClickListener(v -> {
            if (Objects.equals(sortOrder, "ASC")) {
                sortOrder = "DESC";
            } else {
                sortOrder = "ASC";
            }
            String sortText = "SORT: " + sortOrder;
            btnSort.setText(sortText);
            refreshData();
        });

        lvMahasiswa.setOnItemClickListener((parent, view, position, id) -> {
            Mahasiswa m = listMahasiswa.get(position);
            selectedId = m.getId();
            etNim.setText(m.getNim());
            etNama.setText(m.getNama());
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                refreshData();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void refreshData() {
        listMahasiswa = dbHelper.getAllMahasiswa(etSearch.getText().toString(), sortOrder);
        adapter = new MahasiswaAdapter(this, listMahasiswa);
        lvMahasiswa.setAdapter(adapter);
    }

    private void clearForm() {
        etNim.setText("");
        etNama.setText("");
        selectedId = -1;
    }
}
