package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestKCL implements Serializable {
    private String tanggalkcl, jumlahkcl, waktukcl;

    public RequestKCL() {
    }

    public RequestKCL(String tanggalkcl, String jumlahkcl, String waktukcl) {
        this.tanggalkcl = tanggalkcl;
        this.jumlahkcl = jumlahkcl;
        this.waktukcl = waktukcl;
    }

    public String getTanggalkcl() {
        return tanggalkcl;
    }

    public void setTanggalkcl(String tanggalkcl) {
        this.tanggalkcl = tanggalkcl;
    }

    public String getJumlahkcl() {
        return jumlahkcl;
    }

    public void setJumlahkcl(String jumlahkcl) {
        this.jumlahkcl = jumlahkcl;
    }

    public String getWaktukcl() {
        return waktukcl;
    }

    public void setWaktukcl(String waktukcl) {
        this.waktukcl = waktukcl;
    }

    @Override
    public String toString() {
        return "RequestKCL{" +
                "tanggalkcl='" + tanggalkcl + '\'' +
                ", jumlahkcl='" + jumlahkcl + '\'' +
                ", waktukcl='" + waktukcl + '\'' +
                '}';
    }
}
