package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestUpdateArtemia implements Serializable {
    private String tanggalartemia, jumlahartemia, waktuartemia;

    public RequestUpdateArtemia() {
    }

    public RequestUpdateArtemia(String tanggalartemia, String jumlahartemia, String waktuartemia) {
        this.tanggalartemia = tanggalartemia;
        this.jumlahartemia = jumlahartemia;
        this.waktuartemia = waktuartemia;
    }

    public String getTanggalartemia() {
        return tanggalartemia;
    }

    public void setTanggalartemia(String tanggalartemia) {
        this.tanggalartemia = tanggalartemia;
    }

    public String getJumlahartemia() {
        return jumlahartemia;
    }

    public void setJumlahartemia(String jumlahartemia) {
        this.jumlahartemia = jumlahartemia;
    }

    public String getWaktuartemia() {
        return waktuartemia;
    }

    public void setWaktuartemia(String waktuartemia) {
        this.waktuartemia = waktuartemia;
    }

    @Override
    public String toString() {
        return "RequestArtemia{" +
                "tanggalartemia='" + tanggalartemia + '\'' +
                ", jumlahartemia='" + jumlahartemia + '\'' +
                ", waktuartemia='" + waktuartemia + '\'' +
                '}';
    }
}
