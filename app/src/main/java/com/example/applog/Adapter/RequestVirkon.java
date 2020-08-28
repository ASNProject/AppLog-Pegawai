package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestVirkon implements Serializable {
    private String tanggalvirkon, jumlahvirkon, waktuvirkon;

    public RequestVirkon() {
    }

    public RequestVirkon(String tanggalvirkon, String jumlahvirkon, String waktuvirkon) {
        this.tanggalvirkon = tanggalvirkon;
        this.jumlahvirkon = jumlahvirkon;
        this.waktuvirkon = waktuvirkon;
    }

    public String getTanggalvirkon() {
        return tanggalvirkon;
    }

    public void setTanggalvirkon(String tanggalvirkon) {
        this.tanggalvirkon = tanggalvirkon;
    }

    public String getJumlahvirkon() {
        return jumlahvirkon;
    }

    public void setJumlahvirkon(String jumlahvirkon) {
        this.jumlahvirkon = jumlahvirkon;
    }

    public String getWaktuvirkon() {
        return waktuvirkon;
    }

    public void setWaktuvirkon(String waktuvirkon) {
        this.waktuvirkon = waktuvirkon;
    }

    @Override
    public String toString() {
        return "RequestVirkon{" +
                "tanggalvirkon='" + tanggalvirkon + '\'' +
                ", jumlahvirkon='" + jumlahvirkon + '\'' +
                ", waktuvirkon='" + waktuvirkon + '\'' +
                '}';
    }
}
