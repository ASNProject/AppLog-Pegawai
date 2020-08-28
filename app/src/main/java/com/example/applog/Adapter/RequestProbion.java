package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestProbion implements Serializable {
    private String tanggalprobion, jumlahprobion, waktuprobion;

    public RequestProbion() {
    }

    public RequestProbion(String tanggalprobion, String jumlahprobion, String waktuprobion) {
        this.tanggalprobion = tanggalprobion;
        this.jumlahprobion = jumlahprobion;
        this.waktuprobion = waktuprobion;
    }

    public String getTanggalprobion() {
        return tanggalprobion;
    }

    public void setTanggalprobion(String tanggalprobion) {
        this.tanggalprobion = tanggalprobion;
    }

    public String getJumlahprobion() {
        return jumlahprobion;
    }

    public void setJumlahprobion(String jumlahprobion) {
        this.jumlahprobion = jumlahprobion;
    }

    public String getWaktuprobion() {
        return waktuprobion;
    }

    public void setWaktuprobion(String waktuprobion) {
        this.waktuprobion = waktuprobion;
    }

    @Override
    public String toString() {
        return "RequestProbion{" +
                "tanggalprobion='" + tanggalprobion + '\'' +
                ", jumlahprobion='" + jumlahprobion + '\'' +
                ", waktuprobion='" + waktuprobion + '\'' +
                '}';
    }
}
