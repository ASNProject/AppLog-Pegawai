package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestUpdateKaptan implements Serializable {
    private String tanggalkaptan, jumlahkaptan, waktukaptan;

    public RequestUpdateKaptan() {
    }

    public RequestUpdateKaptan(String tanggalkaptan, String jumlahkaptan, String waktukaptan) {
        this.tanggalkaptan = tanggalkaptan;
        this.jumlahkaptan = jumlahkaptan;
        this.waktukaptan = waktukaptan;
    }

    public String getTanggalkaptan() {
        return tanggalkaptan;
    }

    public void setTanggalkaptan(String tanggalkaptan) {
        this.tanggalkaptan = tanggalkaptan;
    }

    public String getJumlahkaptan() {
        return jumlahkaptan;
    }

    public void setJumlahkaptan(String jumlahkaptan) {
        this.jumlahkaptan = jumlahkaptan;
    }

    public String getWaktukaptan() {
        return waktukaptan;
    }

    public void setWaktukaptan(String waktukaptan) {
        this.waktukaptan = waktukaptan;
    }

    @Override
    public String toString() {
        return "RequestKaptan{" +
                "tanggalkaptan='" + tanggalkaptan + '\'' +
                ", jumlahkaptan='" + jumlahkaptan + '\'' +
                ", waktukaptan='" + waktukaptan + '\'' +
                '}';
    }
}
