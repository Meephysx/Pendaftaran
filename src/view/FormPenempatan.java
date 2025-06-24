package view;

import controller.KelasController;
import controller.PenempatanController;
import controller.SiswaController;
import model.Kelas;
import model.Siswa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

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
