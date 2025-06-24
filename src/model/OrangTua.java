package model;

public class OrangTua {
    private int id;
    private String namaAyah;
    private String namaIbu;
    private String alamat;
    private String noHp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaAyah() {
        return namaAyah;
    }

    public void setNamaAyah(String namaAyah) {
        this.namaAyah = namaAyah;
    }

    public String getNamaIbu() {
        return namaIbu;
    }

    public void setNamaIbu(String namaIbu) {
        this.namaIbu = namaIbu;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    // Jika ingin ditampilkan di JComboBox atau tabel
    @Override
    public String toString() {
        return namaAyah + " & " + namaIbu;
    }
}
