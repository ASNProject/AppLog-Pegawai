package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestUpdateKapuraktif implements Serializable {
    private String tanggalkapuraktif, jumlahkapuraktif, waktukapuraktif;

    public RequestUpdateKapuraktif() {
    }

    public RequestUpdateKapuraktif(String tanggalkapuraktif, String jumlahkapuraktif, String waktukapuraktif) {
        this.tanggalkapuraktif = tanggalkapuraktif;
        this.jumlahkapuraktif = jumlahkapuraktif;
        this.waktukapuraktif = waktukapuraktif;
    }

    public String getTanggalkapuraktif() {
        return tanggalkapuraktif;
    }

    public void setTanggalkapuraktif(String tanggalkapuraktif) {
        this.tanggalkapuraktif = tanggalkapuraktif;
    }

    public String getJumlahkapuraktif() {
        return jumlahkapuraktif;
    }

    public void setJumlahkapuraktif(String jumlahkapuraktif) {
        this.jumlahkapuraktif = jumlahkapuraktif;
    }

    public String getWaktukapuraktif() {
        return waktukapuraktif;
    }

    public void setWaktukapuraktif(String waktukapuraktif) {
        this.waktukapuraktif = waktukapuraktif;
    }

    @Override
    public String toString() {
        return "RequestKapuraktif{" +
                "tanggalkapuraktif='" + tanggalkapuraktif + '\'' +
                ", jumlahkapuraktif='" + jumlahkapuraktif + '\'' +
                ", waktukapuraktif='" + waktukapuraktif + '\'' +
                '}';
    }
}
