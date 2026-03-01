# Bookeping - Aplikasi Kasir (Cashier Management System)

Aplikasi desktop berbasis Java untuk manajemen kasir toko kelontong. Sistem ini mengelola data pengguna, produk, supplier, serta mencatat transaksi pembelian dan penjualan dengan update stok otomatis.

## ✨ Fitur Utama

### 👥 Manajemen Pengguna
- Login dengan username dan password terenkripsi
- Multi-level pengguna (admin/kasir)
- Kelola data pengguna (tambah, edit, hapus)

### 📦 Manajemen Produk
- Kelola data produk (ID, nama, stok, harga beli, harga jual)
- Update stok otomatis saat transaksi
- Pencarian produk

### 🚚 Manajemen Supplier
- Kelola data supplier
- Tracking supplier untuk pembelian barang

### 💰 Transaksi Penjualan
- Mencatat penjualan produk ke konsumen
- **Trigger otomatis:** Stok berkurang saat transaksi penjualan
- Menyimpan history penjualan dengan tanggal

### 📥 Transaksi Pembelian
- Mencatat pembelian produk dari supplier
- **Trigger otomatis:** Stok bertambah saat transaksi pembelian
- Menyimpan history pembelian dengan tanggal

## 🛠️ Teknologi yang Digunakan

- **Bahasa:** Java (JDK 8+)
- **GUI:** Java Swing
- **Database:** MySQL / MariaDB (via XAMPP/phpMyAdmin)
- **Koneksi Database:** JDBC (Connector/J)
- **IDE:** NetBeans
- **Tools:** phpMyAdmin untuk manajemen database

## 📊 Struktur Database

Database: `bokkeping`

### Tabel Utama:
1. **pengguna** - Data pengguna aplikasi
2. **produk** - Data produk dengan stok dan harga
3. **supplier** - Data supplier
4. **tf_beli** - Transaksi pembelian (dengan trigger tambah stok)
5. **tf_jual** - Transaksi penjualan (dengan trigger kurangi stok)

### Fitur Database:
- ✅ Primary Key untuk setiap tabel
- ✅ Foreign Key dengan ON DELETE CASCADE
- ✅ Trigger otomatis untuk update stok
- ✅ Password terenkripsi (hash MySQL)

## 🚀 Cara Install dan Menjalankan

### Prasyarat
1. **XAMPP** atau sejenisnya (untuk MySQL)
2. **Java JDK 8+**
3. **NetBeans IDE** (opsional, untuk development)

### Langkah-langkah

#### 1. Setup Database
1. Jalankan XAMPP, start MySQL
2. Buka phpMyAdmin (`http://localhost/phpmyadmin`)
3. Buat database baru dengan nama `bokkeping`
4. Import file `database/bokkeping.sql` (buat folder ini dan simpan file SQL di sana)

Atau copy-paste script SQL yang ada di repository ini.

#### 2. Jalankan Aplikasi
**Untuk Pengguna:**
```bash
java -jar dist/MiniProject.jar
