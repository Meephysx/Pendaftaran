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