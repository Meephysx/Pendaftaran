**Nama : M.Zulfan.M.A**

**NIM  : 20230040152**

**Kelas: TI23A**

---

## 📘 Aplikasi Pendaftaran Peserta Didik

Selamat datang di repositori proyek **Aplikasi Pendaftaran Peserta Didik**, sebuah aplikasi desktop berbasis Java yang dikembangkan sebagai bagian dari **tugas akhir mata kuliah Pemrograman Berorientasi Objek (PBO)**.

Proyek ini dirancang untuk mendigitalisasi proses pendaftaran peserta didik pada sebuah institusi pendidikan, menggunakan paradigma **Object-Oriented Programming (OOP)**, antarmuka pengguna berbasis **Java Swing**, dan penyimpanan data melalui **MySQL Database**.

---

### 🎯 Fitur Utama

* Input data peserta didik
* Input data orang tua atau wali
* Pengelolaan kelas dan wali kelas
* Penempatan siswa ke kelas
* Antarmuka grafis sederhana dan interaktif

---

### 🏗️ Struktur Folder Proyek

```
.
├── src/                        # Kode sumber utama
│   ├── App.java               # Entry point aplikasi
│   ├── db/                    # Koneksi ke database
│   │   └── Koneksi.java
│   ├── model/                 # Kelas entitas: Siswa, OrangTua, Kelas, Penempatan
│   ├── controller/            # Logika bisnis (CRUD)
│   └── view/                  # Tampilan GUI (Swing)
├── lib/                       # File .jar eksternal (MySQL Connector/J)
├── bin/                       # Hasil kompilasi file .class
└── README.md                  # Dokumentasi proyek ini
```
---

### 📦 Teknologi yang Digunakan

* **Java** – bahasa pemrograman utama
* **Java Swing** – membangun antarmuka grafis (GUI)
* **MySQL** – menyimpan dan mengelola data
* **JDBC (MySQL Connector/J)** – koneksi antara Java dan MySQL
* **Visual Studio Code** – sebagai lingkungan pengembangan (IDE)
* **XAMPP** – untuk menjalankan database MySQL secara lokal

---

### ⚙️ Cara Instalasi dan Menjalankan Aplikasi

---

#### ✅ 1. Clone Repositori

```bash
git clone https://github.com/Meephysx/Pendaftaran.git
cd pendaftaran
```
---

#### ✅ 2. Persiapkan Database

1. Buka **XAMPP** dan aktifkan **MySQL**.
2. Buka **phpMyAdmin**, lalu jalankan query berikut untuk membuat database dan tabel:

```sql
CREATE DATABASE pendaftaran;
USE pendaftaran;

CREATE TABLE orang_tua (
    id_orang_tua INT AUTO_INCREMENT PRIMARY KEY,
    nama_ayah VARCHAR(100),
    nama_ibu VARCHAR(100),
    alamat TEXT,
    no_hp VARCHAR(15)
);

CREATE TABLE siswa (
    id_siswa INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100),
    tgl_lahir DATE,
    jenis_kelamin VARCHAR(10),
    id_orang_tua INT,
    FOREIGN KEY (id_orang_tua) REFERENCES orang_tua(id_orang_tua)
);

CREATE TABLE kelas (
    id_kelas INT AUTO_INCREMENT PRIMARY KEY,
    nama_kelas VARCHAR(10),
    wali_kelas VARCHAR(100)
);

CREATE TABLE penempatan_kelas (
    id_penempatan INT AUTO_INCREMENT PRIMARY KEY,
    id_siswa INT,
    id_kelas INT,
    FOREIGN KEY (id_siswa) REFERENCES siswa(id_siswa),
    FOREIGN KEY (id_kelas) REFERENCES kelas(id_kelas)
);
```

---

#### ✅ 3. Siapkan MySQL Connector

1. Download file **MySQL Connector/J** (format `.jar`) dari:
   [https://dev.mysql.com/downloads/connector/j/](https://dev.mysql.com/downloads/connector/j/)
2. Simpan file `.jar` tersebut ke dalam folder `lib/`

---

#### ✅ 4. Kompilasi Program

Buka terminal di folder proyek, lalu jalankan:

```bash
javac -d bin -cp lib/mysql-connector-j-9.3.0 src/**/*.java
```
---

#### ✅ 5. Jalankan Program

```bash
java -cp "bin;lib/mysql-connector-j-9.3.0.jar" App
```

> Gunakan titik dua `:` jika kamu menggunakan Linux/macOS:
>
> ```bash
> java -cp "bin:lib/mmysql-connector-j-9.3.0" App
> ```

---

## 📂 Penjelasan tiap package yang digunakan


## 📂 Package `controller/`

---

### 🧾 `KelasController.java`

```java
package controller;

import db.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Kelas;

public class KelasController {

    public boolean tambah(Kelas k) {
        String sql = "INSERT INTO kelas (nama_kelas, wali_kelas) VALUES (?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getNamaKelas());
            ps.setString(2, k.getWaliKelas());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Gagal tambah kelas: " + e.getMessage());
            return false;
        }
    }

    public List<Kelas> getAll() {
        List<Kelas> list = new ArrayList<>();
        String sql = "SELECT * FROM kelas";
        try (Connection conn = Koneksi.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Kelas k = new Kelas();
                k.setId(rs.getInt("id_kelas"));
                k.setNamaKelas(rs.getString("nama_kelas"));
                k.setWaliKelas(rs.getString("wali_kelas"));
                list.add(k);
            }
        } catch (Exception e) {
            System.out.println("Gagal ambil kelas: " + e.getMessage());
        }
        return list;
    }
}
```

**Penjelasan:**

* Kelas ini menangani semua interaksi dengan tabel `kelas` di database.
* Method `tambah(Kelas k)` digunakan untuk menyisipkan data kelas baru ke database.
* Method `getAll()` mengambil seluruh data dari tabel `kelas` dan mengembalikannya dalam bentuk list objek `Kelas`.

---

### 🧾 `OrangTuaController.java`

```java
package controller;

import db.Koneksi;
import java.sql.*;
import model.OrangTua;

public class OrangTuaController {

    public boolean tambah(OrangTua o) {
        String sql = "INSERT INTO orang_tua (nama_ayah, nama_ibu, alamat, no_hp) VALUES (?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, o.getNamaAyah());
            ps.setString(2, o.getNamaIbu());
            ps.setString(3, o.getAlamat());
            ps.setString(4, o.getNoHp());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Gagal tambah orang tua: " + e.getMessage());
            return false;
        }
    }
}
```

**Penjelasan:**

* Kelas ini menangani proses penyimpanan data orang tua ke dalam database.
* Method `tambah(OrangTua o)` menyiapkan pernyataan SQL menggunakan `PreparedStatement` dan mengeksekusinya untuk menambahkan data orang tua.
* Tidak ada method `getAll()` karena form ini hanya berfungsi untuk input data.


---

### 🧾 `SiswaController.java`

```java
package controller;

import db.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Siswa;

public class SiswaController {

    public boolean tambah(Siswa s) {
        String sql = "INSERT INTO siswa (nama, tgl_lahir, jenis_kelamin, id_orang_tua) VALUES (?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getNama());
            ps.setString(2, s.getTglLahir());
            ps.setString(3, s.getJenisKelamin());
            ps.setInt(4, s.getIdOrangTua());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Gagal tambah siswa: " + e.getMessage());
            return false;
        }
    }

    public List<Siswa> getAll() {
        List<Siswa> list = new ArrayList<>();
        String sql = "SELECT * FROM siswa";
        try (Connection conn = Koneksi.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Siswa s = new Siswa();
                s.setId(rs.getInt("id_siswa"));
                s.setNama(rs.getString("nama"));
                s.setTglLahir(rs.getString("tgl_lahir"));
                s.setJenisKelamin(rs.getString("jenis_kelamin"));
                s.setIdOrangTua(rs.getInt("id_orang_tua"));
                list.add(s);
            }

        } catch (Exception e) {
            System.out.println("Gagal ambil siswa: " + e.getMessage());
        }

        return list;
    }

    public boolean hapus(int id) {
        String sql = "DELETE FROM siswa WHERE id_siswa = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Gagal hapus siswa: " + e.getMessage());
            return false;
        }
    }
}
```

**Penjelasan:**

* `tambah(...)`: Menyimpan data siswa baru ke database.
* `getAll()`: Mengambil seluruh data siswa dari database dan dikembalikan dalam bentuk list objek `Siswa`.
* `hapus(...)`: Menghapus data siswa berdasarkan `id_siswa`.

---

### 🧾 `PenempatanController.java`

```java
package controller;

import db.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Penempatan;

public class PenempatanController {

    public boolean tempatkan(int idSiswa, int idKelas) {
        String sql = "INSERT INTO penempatan_kelas (id_siswa, id_kelas) VALUES (?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSiswa);
            ps.setInt(2, idKelas);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Gagal tempatkan siswa: " + e.getMessage());
            return false;
        }
    }

    public List<Penempatan> getAll() {
        List<Penempatan> list = new ArrayList<>();
        String sql = "SELECT p.id_penempatan, s.nama AS nama_siswa, k.nama_kelas " +
                     "FROM penempatan_kelas p " +
                     "JOIN siswa s ON p.id_siswa = s.id_siswa " +
                     "JOIN kelas k ON p.id_kelas = k.id_kelas";

        try (Connection conn = Koneksi.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Penempatan p = new Penempatan();
                p.setId(rs.getInt("id_penempatan"));
                p.setNamaSiswa(rs.getString("nama_siswa"));
                p.setNamaKelas(rs.getString("nama_kelas"));
                list.add(p);
            }

        } catch (Exception e) {
            System.out.println("Gagal ambil data penempatan: " + e.getMessage());
        }

        return list;
    }
}
```

**Penjelasan:**

* `tempatkan(...)`: Menyisipkan hubungan antara siswa dan kelas ke dalam tabel `penempatan_kelas`.
* `getAll()`: Mengambil seluruh data hasil penempatan siswa ke kelas, termasuk join dengan tabel `siswa` dan `kelas`, lalu disajikan sebagai list objek `Penempatan`.

---

## 📂 Package `view/`


### 🧾 `FormKelas.java`

```java
package view;

import controller.KelasController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Kelas;

public class FormKelas extends JFrame {
    public FormKelas() {
        setTitle("Form Kelas");
        setSize(450, 400);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblNama = new JLabel("Nama Kelas:");
        lblNama.setBounds(20, 20, 100, 25);
        add(lblNama);
        JTextField txtNama = new JTextField();
        txtNama.setBounds(130, 20, 200, 25);
        add(txtNama);

        JLabel lblWali = new JLabel("Wali Kelas:");
        lblWali.setBounds(20, 60, 100, 25);
        add(lblWali);
        JTextField txtWali = new JTextField();
        txtWali.setBounds(130, 60, 200, 25);
        add(txtWali);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(130, 100, 100, 30);
        add(btnSimpan);

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nama Kelas", "Wali Kelas"}, 0);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 150, 400, 200);
        add(scroll);

        btnSimpan.addActionListener(e -> {
            Kelas k = new Kelas();
            k.setNamaKelas(txtNama.getText());
            k.setWaliKelas(txtWali.getText());
            if (new KelasController().tambah(k)) {
                JOptionPane.showMessageDialog(this, "Berhasil");
                model.setRowCount(0);
                for (Kelas item : new KelasController().getAll()) {
                    model.addRow(new Object[]{item.getId(), item.getNamaKelas(), item.getWaliKelas()});
                }
            } else {
                JOptionPane.showMessageDialog(this, "Gagal");
            }
        });

        for (Kelas item : new KelasController().getAll()) {
            model.addRow(new Object[]{item.getId(), item.getNamaKelas(), item.getWaliKelas()});
        }

        setVisible(true);
    }
}
```

**Penjelasan:**

* Form ini digunakan untuk menambahkan dan menampilkan daftar kelas.
* Komponen utama: `JLabel`, `JTextField`, `JButton`, dan `JTable`.
* Data baru dikirim ke `KelasController` dan ditampilkan ulang secara otomatis di tabel.

---

### 🧾 `FormOrangTua.java`

```java
package view;

import controller.OrangTuaController;
import javax.swing.*;
import model.OrangTua;

public class FormOrangTua extends JFrame {
    public FormOrangTua() {
        setTitle("Form Orang Tua");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblAyah = new JLabel("Nama Ayah:");
        lblAyah.setBounds(20, 20, 100, 25);
        add(lblAyah);
        JTextField txtAyah = new JTextField();
        txtAyah.setBounds(140, 20, 200, 25);
        add(txtAyah);

        JLabel lblIbu = new JLabel("Nama Ibu:");
        lblIbu.setBounds(20, 60, 100, 25);
        add(lblIbu);
        JTextField txtIbu = new JTextField();
        txtIbu.setBounds(140, 60, 200, 25);
        add(txtIbu);

        JLabel lblAlamat = new JLabel("Alamat:");
        lblAlamat.setBounds(20, 100, 100, 25);
        add(lblAlamat);
        JTextField txtAlamat = new JTextField();
        txtAlamat.setBounds(140, 100, 200, 25);
        add(txtAlamat);

        JLabel lblHp = new JLabel("No HP:");
        lblHp.setBounds(20, 140, 100, 25);
        add(lblHp);
        JTextField txtHp = new JTextField();
        txtHp.setBounds(140, 140, 200, 25);
        add(txtHp);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(140, 180, 100, 30);
        add(btnSimpan);

        btnSimpan.addActionListener(e -> {
            OrangTua o = new OrangTua();
            o.setNamaAyah(txtAyah.getText());
            o.setNamaIbu(txtIbu.getText());
            o.setAlamat(txtAlamat.getText());
            o.setNoHp(txtHp.getText());
            if (new OrangTuaController().tambah(o)) {
                JOptionPane.showMessageDialog(this, "Sukses");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal");
            }
        });

        setVisible(true);
    }
}
```

**Penjelasan:**

* Form ini memungkinkan pengguna mengisi data orang tua (ayah, ibu, alamat, no HP).
* Saat tombol “Simpan” ditekan, data dikirim ke `OrangTuaController`.
* Tidak menampilkan data dalam tabel karena hanya berfungsi untuk input.

---

### 🧾 `FormSiswa.java`

```java
package view;

import controller.SiswaController;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Siswa;

public class FormSiswa extends JFrame {
    private JTextField txtNama, txtTglLahir, txtJK, txtIdOrtu;
    private JButton btnSimpan, btnHapus;
    private JTable table;
    private DefaultTableModel model;

    public FormSiswa() {
        setTitle("Form Siswa");
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lbl1 = new JLabel("Nama:");
        lbl1.setBounds(20, 20, 100, 25);
        add(lbl1);
        txtNama = new JTextField();
        txtNama.setBounds(150, 20, 300, 25);
        add(txtNama);

        JLabel lbl2 = new JLabel("Tanggal Lahir:");
        lbl2.setBounds(20, 60, 100, 25);
        add(lbl2);
        txtTglLahir = new JTextField();
        txtTglLahir.setBounds(150, 60, 300, 25);
        add(txtTglLahir);

        JLabel lbl3 = new JLabel("Jenis Kelamin:");
        lbl3.setBounds(20, 100, 100, 25);
        add(lbl3);
        txtJK = new JTextField();
        txtJK.setBounds(150, 100, 300, 25);
        add(txtJK);

        JLabel lbl4 = new JLabel("ID Orang Tua:");
        lbl4.setBounds(20, 140, 100, 25);
        add(lbl4);
        txtIdOrtu = new JTextField();
        txtIdOrtu.setBounds(150, 140, 300, 25);
        add(txtIdOrtu);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(150, 180, 100, 30);
        add(btnSimpan);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(260, 180, 100, 30);
        add(btnHapus);

        model = new DefaultTableModel(new String[]{"ID", "Nama", "Tgl Lahir", "JK", "ID Ortu"}, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 230, 440, 200);
        add(sp);

        btnSimpan.addActionListener(e -> {
            Siswa s = new Siswa();
            s.setNama(txtNama.getText());
            s.setTglLahir(txtTglLahir.getText());
            s.setJenisKelamin(txtJK.getText());
            s.setIdOrangTua(Integer.parseInt(txtIdOrtu.getText()));

            if (new SiswaController().tambah(s)) {
                JOptionPane.showMessageDialog(this, "Sukses");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal");
            }
        });

        btnHapus.addActionListener(e -> {
            int baris = table.getSelectedRow();
            if (baris != -1) {
                int id = Integer.parseInt(model.getValueAt(baris, 0).toString());
                new SiswaController().hapus(id);
                loadTable();
            }
        });

        loadTable();
        setVisible(true);
    }

    private void loadTable() {
        model.setRowCount(0);
        List<Siswa> list = new SiswaController().getAll();
        for (Siswa s : list) {
            model.addRow(new Object[]{s.getId(), s.getNama(), s.getTglLahir(), s.getJenisKelamin(), s.getIdOrangTua()});
        }
    }
}
```

**Penjelasan:**

* Form ini digunakan untuk menambahkan, menampilkan, dan menghapus data siswa.
* Terdiri dari empat input: nama, tanggal lahir, jenis kelamin, dan ID orang tua.
* Tombol “Simpan” menyimpan data ke database via `SiswaController`.
* Tombol “Hapus” akan menghapus data berdasarkan ID baris yang dipilih di tabel.
* Data ditampilkan secara dinamis menggunakan `JTable`.

---

### 🧾 `FormPenempatan.java`

```java
package view;

import controller.KelasController;
import controller.PenempatanController;
import controller.SiswaController;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Kelas;
import model.Siswa;

public class FormPenempatan extends JFrame {
    public FormPenempatan() {
        setTitle("Penempatan Siswa ke Kelas");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JComboBox<Siswa> cmbSiswa = new JComboBox<>();
        JComboBox<Kelas> cmbKelas = new JComboBox<>();

        JLabel lblSiswa = new JLabel("Pilih Siswa:");
        lblSiswa.setBounds(20, 20, 100, 25);
        add(lblSiswa);
        cmbSiswa.setBounds(130, 20, 300, 25);
        add(cmbSiswa);

        JLabel lblKelas = new JLabel("Pilih Kelas:");
        lblKelas.setBounds(20, 60, 100, 25);
        add(lblKelas);
        cmbKelas.setBounds(130, 60, 300, 25);
        add(cmbKelas);

        JButton btnTempatkan = new JButton("Tempatkan");
        btnTempatkan.setBounds(130, 100, 120, 30);
        add(btnTempatkan);

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Siswa", "Kelas"}, 0);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 150, 440, 180);
        add(scroll);

        List<Siswa> siswaList = new SiswaController().getAll();
        for (Siswa s : siswaList) {
            cmbSiswa.addItem(s);
        }

        List<Kelas> kelasList = new KelasController().getAll();
        for (Kelas k : kelasList) {
            cmbKelas.addItem(k);
        }

        btnTempatkan.addActionListener(e -> {
            Siswa siswa = (Siswa) cmbSiswa.getSelectedItem();
            Kelas kelas = (Kelas) cmbKelas.getSelectedItem();
            if (siswa != null && kelas != null) {
                boolean sukses = new PenempatanController().tempatkan(siswa.getId(), kelas.getId());
                if (sukses) {
                    JOptionPane.showMessageDialog(this, "Berhasil");
                    model.setRowCount(0);
                    new PenempatanController().getAll().forEach(p -> {
                        model.addRow(new Object[]{p.getId(), p.getNamaSiswa(), p.getNamaKelas()});
                    });
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menyimpan");
                }
            }
        });

        new PenempatanController().getAll().forEach(p -> {
            model.addRow(new Object[]{p.getId(), p.getNamaSiswa(), p.getNamaKelas()});
        });

        setVisible(true);
    }
}
```

**Penjelasan:**

* Form ini memungkinkan admin menempatkan siswa ke dalam kelas tertentu.
* Data siswa dan kelas ditampilkan dalam dropdown `JComboBox`.
* Tombol “Tempatkan” menghubungkan siswa dan kelas menggunakan `PenempatanController`.
* Hasil penempatan ditampilkan secara dinamis di `JTable`.

---

### 🧾 `db/Koneksi.java`

```java
package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/pendaftaran_sd";
            String user = "root";
            String pass = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Gagal koneksi ke database: " + e.getMessage());
            return null;
        }
    }
}
```

**Penjelasan:**

* Kelas ini bertugas membuat koneksi ke **database MySQL** dengan nama `pendaftaran_sd`.
* Digunakan di seluruh controller untuk mendapatkan `Connection` melalui `Koneksi.getConnection()`.
* Jika koneksi gagal, akan muncul pesan kesalahan di terminal.
* Konfigurasi menggunakan:

  * Host: `localhost`
  * Port: `3306`
  * User: `root`
  * Password: *(kosong)*

---

### 🧾 `App.java`

```java
import view.FormUtama;

public class App {
    public static void main(String[] args) {
        new FormUtama();
    }
}
```

**Penjelasan:**

* Merupakan titik masuk utama aplikasi (`main method`).
* Langsung memanggil dan menampilkan **FormUtama**, yaitu menu utama antarmuka pengguna.
* Tidak memerlukan konfigurasi tambahan karena seluruh logika dijalankan di dalam form-form yang dipanggil dari sini.

---

## 🔗 Hubungan Antar Komponen (Arsitektur Aplikasi)

Aplikasi ini menggunakan pendekatan **Model-View-Controller (MVC)**, di mana setiap komponen memiliki tanggung jawab tersendiri dan saling terhubung untuk membentuk sistem yang modular dan mudah dipelihara.

---

### 1. **View (Antarmuka Pengguna)**

📁 Package: `view/`

* Semua form (`FormSiswa`, `FormOrangTua`, `FormKelas`, `FormPenempatan`, dan `FormUtama`) berada di sini.
* Bertugas **mengambil input dari pengguna** (melalui form) dan **menampilkan hasil ke layar** (via tabel dan komponen Swing lainnya).
* Tidak berhubungan langsung dengan database, tapi mengandalkan `controller/` untuk logika penyimpanan dan pengambilan data.

🔗 **Terhubung ke:**
→ `controller/` melalui pemanggilan seperti:

```java
new SiswaController().tambah(siswa);
new KelasController().getAll();
```

---

### 2. **Controller (Logika Aplikasi)**

📁 Package: `controller/`

* Menjadi **jembatan antara view dan model/database**.
* Menerima data dari form, lalu mengolahnya menjadi perintah SQL untuk disimpan atau ditampilkan.
* Menggunakan class dari `model/` sebagai struktur data dan menggunakan `Koneksi` untuk koneksi DB.

🔗 **Terhubung ke:**

* `view/`: dipanggil dari form untuk operasi CRUD.
* `model/`: membuat objek seperti `Siswa`, `Kelas`, `OrangTua`, `Penempatan`.
* `db/Koneksi`: untuk koneksi ke database menggunakan `JDBC`.

---

### 3. **Model (Struktur Data / Entitas)**

📁 Package: `model/`

* Berisi representasi objek nyata di dunia pendidikan: `Siswa`, `OrangTua`, `Kelas`, dan `Penempatan`.
* Hanya berisi **atribut dan getter-setter**, tidak memiliki logika database.
* Digunakan oleh controller untuk menampung data sebelum/selesai diakses dari database.

🔗 **Digunakan oleh:**

* `controller/` untuk mengirim dan menerima data.

---

### 4. **Database (MySQL)**

🗄️ Melalui: `db/Koneksi.java` dan MySQL lokal (`XAMPP`)

* Database `pendaftaran_sd` menyimpan data dalam empat tabel: `siswa`, `orang_tua`, `kelas`, `penempatan_kelas`.
* Koneksi dibuat satu kali dalam class `Koneksi` lalu dipanggil berulang oleh semua controller.
* Operasi database dilakukan dengan **prepared statements** dan SQL join (untuk penempatan siswa).

---

### 🔄 Alur Umum Interaksi

```plaintext
User (GUI - view/)
    ↓ input data siswa
Controller (tambah())
    ↓ buat query + objek model
Model (Siswa)
    ↓ kirim ke database
Database (via db/Koneksi)
    ↓ jika berhasil, controller kirim balik ke view
View (GUI tampilkan data)
```


