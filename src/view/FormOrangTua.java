package view;

import controller.OrangTuaController;
import model.OrangTua;

import javax.swing.*;

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
