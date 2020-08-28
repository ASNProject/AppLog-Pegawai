package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestUpdatemonodon implements Serializable {
    private String tanggalmonodon, jumlahmonodon, waktumonodon;

    public RequestUpdatemonodon() {
    }

    public RequestUpdatemonodon(String tanggalmonodon, String jumlahmonodon, String waktumonodon) {
        this.tanggalmonodon = tanggalmonodon;
        this.jumlahmonodon = jumlahmonodon;
        this.waktumonodon = waktumonodon;
    }

    public String getTanggalmonodon() {
        return tanggalmonodon;
    }

    public void setTanggalmonodon(String tanggalmonodon) {
        this.tanggalmonodon = tanggalmonodon;
    }

    public String getJumlahmonodon() {
        return jumlahmonodon;
    }

    public void setJumlahmonodon(String jumlahmonodon) {
        this.jumlahmonodon = jumlahmonodon;
    }

    public String getWaktumonodon() {
        return waktumonodon;
    }

    public void setWaktumonodon(String waktumonodon) {
        this.waktumonodon = waktumonodon;
    }

    @Override
    public String toString() {
        return "RequestMonodon{" +
                "tanggalmonodon='" + tanggalmonodon + '\'' +
                ", jumlahmonodon='" + jumlahmonodon + '\'' +
                ", waktumonodon='" + waktumonodon + '\'' +
                '}';
    }
}
