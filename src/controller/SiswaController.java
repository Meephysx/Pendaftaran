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
