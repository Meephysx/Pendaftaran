package view;

import controller.SiswaController;
import model.Siswa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

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
