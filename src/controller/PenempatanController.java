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
