package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestUpdateRO2 implements Serializable {
    private String tanggalro2, jumlahro2, wakturo2;

    public RequestUpdateRO2() {
    }

    public RequestUpdateRO2(String tanggalro2, String jumlahro2, String wakturo2) {
        this.tanggalro2 = tanggalro2;
        this.jumlahro2 = jumlahro2;
        this.wakturo2 = wakturo2;
    }

    public String getTanggalro2() {
        return tanggalro2;
    }

    public void setTanggalro2(String tanggalro2) {
        this.tanggalro2 = tanggalro2;
    }

    public String getJumlahro2() {
        return jumlahro2;
    }

    public void setJumlahro2(String jumlahro2) {
        this.jumlahro2 = jumlahro2;
    }

    public String getWakturo2() {
        return wakturo2;
    }

    public void setWakturo2(String wakturo2) {
        this.wakturo2 = wakturo2;
    }

    @Override
    public String toString() {
        return "RequestRO2{" +
                "tanggalro2='" + tanggalro2 + '\'' +
                ", jumlahro2='" + jumlahro2 + '\'' +
                ", wakturo2='" + wakturo2 + '\'' +
                '}';
    }
}
