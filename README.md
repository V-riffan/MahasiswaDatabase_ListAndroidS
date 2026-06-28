# UPH 24SI3 - Student Management System (Aplikasi Manajemen Mahasiswa)

Aplikasi Android yang dirancang untuk mengelola data mahasiswa secara efisien menggunakan database lokal SQLite. Aplikasi ini mendukung operasi CRUD lengkap (Create, Read, Update, Delete) dengan antarmuka yang intuitif dan responsif.

## ✨ Fitur Utama
- **Manajemen Data (CRUD)**: Menambah, melihat, memperbarui, dan menghapus data mahasiswa (NIM dan Nama).
- **Pencarian Real-time**: Mencari mahasiswa berdasarkan Nama atau NIM secara langsung saat mengetik melalui fitur `TextWatcher`.
- **Pengurutan (Sorting)**: Mengurutkan daftar mahasiswa berdasarkan Nama secara *Ascending* (A-Z) maupun *Descending* (Z-A).
- **Penyimpanan Lokal (SQLite)**: Menggunakan database internal Android sehingga data tetap tersimpan secara permanen meskipun aplikasi ditutup atau perangkat dimulai ulang.
- **Antarmuka Modern**: Menggunakan elemen visual seperti `bg_box` untuk form input dan kartu data yang rapi dalam `ListView`.

## 🛠️ Arsitektur & Penjelasan Kode

### 1. Model Data (`Mahasiswa.java`)
Kelas ini adalah representasi objek data. Berisi atribut `id`, `nim`, dan `nama` beserta metode getter dan setter-nya. ID digunakan sebagai kunci unik untuk mengidentifikasi data di database.

### 2. Manajemen Database (`DatabaseHelper.java`)
Menggunakan `SQLiteOpenHelper` untuk menangani siklus hidup database:
- **`onCreate()`**: Mendefinisikan struktur tabel `mahasiswa` saat database pertama kali dibuat.
- **`addMahasiswa()`**: Menjalankan perintah SQL `INSERT`.
- **`getAllMahasiswa()`**: Mengambil data menggunakan `SELECT` dengan dukungan klausa `WHERE` untuk pencarian dan `ORDER BY` untuk pengurutan.
- **`updateMahasiswa()` & `deleteMahasiswa()`**: Menangani pembaruan dan penghapusan record berdasarkan ID unik.

### 3. Penghubung Data (`MahasiswaAdapter.java`)
Merupakan `BaseAdapter` khusus yang memetakan data dari objek Java ke dalam tampilan visual `ListView`. Ia memastikan setiap baris data mahasiswa ditampilkan dengan format yang konsisten sesuai desain `list_item_mahasiswa.xml`.

### 4. Kontroler Utama (`MainActivity.java`)
Mengatur alur jalannya aplikasi:
- Menghubungkan kode dengan elemen UI melalui `findViewById`.
- Menangani interaksi pengguna seperti klik tombol TAMBAH, UPDATE, dan HAPUS.
- **`refreshData()`**: Metode sentral yang selalu dipanggil untuk mengambil data terbaru dari database dan menampilkannya kembali ke layar.
- **`clearForm()`**: Membersihkan inputan pengguna setelah operasi berhasil dilakukan.

### 5. Desain Antarmuka (XML)
- **`activity_main.xml`**: Layout utama yang menggunakan kombinasi `LinearLayout` untuk menyusun form input di bagian atas dan daftar data di bagian bawah secara proporsional.
- **`list_item_mahasiswa.xml`**: Layout baris tunggal untuk menampilkan informasi setiap mahasiswa dalam bentuk kartu biru muda yang menarik.

## 🚀 Cara Penggunaan
1. **Tambah Data**: Masukkan NIM dan Nama Lengkap pada form, lalu klik tombol **TAMBAH**.
2. **Pencarian**: Gunakan kolom pencarian untuk memfilter daftar mahasiswa secara instan berdasarkan nama atau NIM.
3. **Pembaruan Data**: Ketuk salah satu item dalam daftar. Data mahasiswa tersebut akan muncul kembali di form atas. Lakukan perubahan, lalu klik tombol **UPDATE**.
4. **Hapus Data**: Ketuk item yang ingin dihapus, lalu klik tombol **HAPUS**.
5. **Urutkan Daftar**: Klik tombol **SORT** untuk membalikkan urutan daftar antara Nama A-Z (ASC) atau Z-A (DESC).

---
*Proyek ini dikembangkan dengan Java di Android Studio untuk tujuan edukasi dalam pengembangan aplikasi mobile.*
