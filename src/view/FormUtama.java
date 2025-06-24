package view;

import javax.swing.*;

public class FormUtama extends JFrame {
    public FormUtama() {
        setTitle("Menu Utama");
        setSize(400, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnSiswa = new JButton("Form Siswa");
        btnSiswa.setBounds(100, 30, 200, 30);
        add(btnSiswa);
        btnSiswa.addActionListener(e -> new FormSiswa());

        JButton btnOrtu = new JButton("Form Orang Tua");
        btnOrtu.setBounds(100, 70, 200, 30);
        add(btnOrtu);
        btnOrtu.addActionListener(e -> new FormOrangTua());

        JButton btnKelas = new JButton("Form Kelas");
        btnKelas.setBounds(100, 110, 200, 30);
        add(btnKelas);
        btnKelas.addActionListener(e -> new FormKelas());

        JButton btnPenempatan = new JButton("Penempatan Kelas");
        btnPenempatan.setBounds(100, 150, 200, 30);
        add(btnPenempatan);
        btnPenempatan.addActionListener(e -> new FormPenempatan());

        setVisible(true);
    }
}