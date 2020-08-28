package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestAzomit implements Serializable {
    private String tanggalazomit, jumlahazomit, waktuazomit;

    public RequestAzomit() {
    }

    public RequestAzomit(String tanggalazomit, String jumlahazomit, String waktuazomit) {
        this.tanggalazomit = tanggalazomit;
        this.jumlahazomit = jumlahazomit;
        this.waktuazomit = waktuazomit;
    }

    public String getTanggalazomit() {
        return tanggalazomit;
    }

    public void setTanggalazomit(String tanggalazomit) {
        this.tanggalazomit = tanggalazomit;
    }

    public String getJumlahazomit() {
        return jumlahazomit;
    }

    public void setJumlahazomit(String jumlahazomit) {
        this.jumlahazomit = jumlahazomit;
    }

    public String getWaktuazomit() {
        return waktuazomit;
    }

    public void setWaktuazomit(String waktuazomit) {
        this.waktuazomit = waktuazomit;
    }

    @Override
    public String toString() {
        return "RequestAzomit{" +
                "tanggalazomit='" + tanggalazomit + '\'' +
                ", jumlahazomit='" + jumlahazomit + '\'' +
                ", waktuazomit='" + waktuazomit + '\'' +
                '}';
    }
}
