package model;

public class Kelas {
    private int id;
    private String namaKelas;
    private String waliKelas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getWaliKelas() {
        return waliKelas;
    }

    public void setWaliKelas(String waliKelas) {
        this.waliKelas = waliKelas;
    }

    // Agar bisa ditampilkan di JComboBox (FormPenempatan)
    @Override
    public String toString() {
        return namaKelas;
    }
}
