# Fruit List App (Aplikasi Daftar Buah-buahan)

Aplikasi Android berbasis Java yang menampilkan daftar buah-buahan tropis dengan antarmuka yang bersih, modern, dan informatif. Aplikasi ini menggunakan `ListView` dengan kustomisasi penuh dan mendukung navigasi ke halaman detail untuk setiap item.

## ✨ Fitur Utama
- **Daftar Buah Interaktif**: Menampilkan 10 jenis buah dengan ikon emoji yang berwarna-warni.
- **Header Bold & Besar**: Desain header yang tegas dengan padding besar (30dp) untuk visibilitas maksimal di berbagai perangkat.
- **Informasi Detail Lengkap**: Setiap buah dilengkapi dengan data asal-usul, rasa, deskripsi mendalam, serta manfaat kesehatan.
- **UX yang Nyaman**: Navigasi kembali yang mudah dan ruang scroll ekstra di bagian bawah daftar agar item terakhir tidak tertutup sistem.

## 📱 Tampilan Antarmuka
- **Halaman Utama**: Daftar buah menggunakan `ListView` dengan pemisah (`divider`) antar item.
- **Halaman Detail**: Menggunakan `ScrollView` untuk memastikan semua informasi dapat dibaca dengan nyaman, dilengkapi dengan "Hero Area" berwarna hijau yang menampilkan emoji buah dalam ukuran besar.

## 🛠️ Struktur Kode
- `MainActivity.java`: Logika utama untuk inisialisasi data dan pengaturan `ListView`.
- `DetailActivity.java`: Menangani tampilan informasi detail berdasarkan data yang dikirim melalui `Intent`.
- `Fruit.java`: Kelas model data yang mengimplementasikan `Serializable`.
- `FruitAdapter.java`: Penghubung (bridge) antara data objek `Fruit` dengan layout XML.
- `res/layout/`: Berisi file desain XML (`activity_main`, `activity_detail`, `list_item_fruit`).

## 🚀 Cara Menjalankan
1. Clone atau download repositori ini.
2. Buka folder proyek di **Android Studio**.
3. Pastikan Gradle melakukan sinkronisasi dengan sukses.
4. Klik **Run** untuk menginstal aplikasi di Emulator atau HP Android Anda.

---
*Dikembangkan dengan ❤️ untuk tugas pemrograman Mobile 2024.*
