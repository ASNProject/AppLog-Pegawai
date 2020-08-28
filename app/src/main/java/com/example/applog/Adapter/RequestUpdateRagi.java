package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestUpdateRagi implements Serializable {
    private String tanggalragi,  jumlahragi, wakturagi;

    public RequestUpdateRagi() {
    }

    public RequestUpdateRagi(String tanggalragi, String jumlahragi, String wakturagi) {
        this.tanggalragi = tanggalragi;
        this.jumlahragi = jumlahragi;
        this.wakturagi = wakturagi;
    }

    public String getTanggalragi() {
        return tanggalragi;
    }

    public void setTanggalragi(String tanggalragi) {
        this.tanggalragi = tanggalragi;
    }

    public String getJumlahragi() {
        return jumlahragi;
    }

    public void setJumlahragi(String jumlahragi) {
        this.jumlahragi = jumlahragi;
    }

    public String getWakturagi() {
        return wakturagi;
    }

    public void setWakturagi(String wakturagi) {
        this.wakturagi = wakturagi;
    }

    @Override
    public String toString() {
        return "RequestRagi{" +
                "tanggalragi='" + tanggalragi + '\'' +
                ", jumlahragi='" + jumlahragi + '\'' +
                ", wakturagi='" + wakturagi + '\'' +
                '}';
    }
}
