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

    // (opsional) kalau kamu ingin tampilkan daftar orang tua:
    // public List<OrangTua> getAll() { ... }
}
