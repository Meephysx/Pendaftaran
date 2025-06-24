package model;

public class Siswa {
    private int id;
    private String nama;
    private String tglLahir;
    private String jenisKelamin;
    private int idOrangTua;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public int getIdOrangTua() {
        return idOrangTua;
    }

    public void setIdOrangTua(int idOrangTua) {
        this.idOrangTua = idOrangTua;
    }

    @Override
    public String toString() {
        return nama;
    }
}
