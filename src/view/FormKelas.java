package view;

import controller.KelasController;
import model.Kelas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

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
