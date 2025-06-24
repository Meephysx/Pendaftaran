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
