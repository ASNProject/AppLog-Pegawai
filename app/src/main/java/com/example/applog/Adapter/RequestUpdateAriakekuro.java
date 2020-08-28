package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestUpdateAriakekuro implements Serializable {
    private String tanggalariakekuro, jumlahariakekuro, waktuariakekuro;

    public RequestUpdateAriakekuro() {
    }

    public RequestUpdateAriakekuro(String tanggalariakekuro, String jumlahariakekuro, String waktuariakekuro) {
        this.tanggalariakekuro = tanggalariakekuro;
        this.jumlahariakekuro = jumlahariakekuro;
        this.waktuariakekuro = waktuariakekuro;
    }

    public String getTanggalariakekuro() {
        return tanggalariakekuro;
    }

    public void setTanggalariakekuro(String tanggalariakekuro) {
        this.tanggalariakekuro = tanggalariakekuro;
    }

    public String getJumlahariakekuro() {
        return jumlahariakekuro;
    }

    public void setJumlahariakekuro(String jumlahariakekuro) {
        this.jumlahariakekuro = jumlahariakekuro;
    }

    public String getWaktuariakekuro() {
        return waktuariakekuro;
    }

    public void setWaktuariakekuro(String waktuariakekuro) {
        this.waktuariakekuro = waktuariakekuro;
    }

    @Override
    public String toString() {
        return "RequestAriakekuro{" +
                "tanggalariakekuro='" + tanggalariakekuro + '\'' +
                ", jumlahariakekuro='" + jumlahariakekuro + '\'' +
                ", waktuariakekuro='" + waktuariakekuro + '\'' +
                '}';
    }
}
