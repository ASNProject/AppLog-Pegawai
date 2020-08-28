package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestUpdateAtbak implements Serializable {
    private String tanggalatbak, jumlahatbak, waktuatbak;

    public RequestUpdateAtbak() {
    }

    public RequestUpdateAtbak(String tanggalatbak, String jumlahatbak, String waktuatbak) {
        this.tanggalatbak = tanggalatbak;
        this.jumlahatbak = jumlahatbak;
        this.waktuatbak = waktuatbak;
    }

    public String getTanggalatbak() {
        return tanggalatbak;
    }

    public void setTanggalatbak(String tanggalatbak) {
        this.tanggalatbak = tanggalatbak;
    }

    public String getJumlahatbak() {
        return jumlahatbak;
    }

    public void setJumlahatbak(String jumlahatbak) {
        this.jumlahatbak = jumlahatbak;
    }

    public String getWaktuatbak() {
        return waktuatbak;
    }

    public void setWaktuatbak(String waktuatbak) {
        this.waktuatbak = waktuatbak;
    }

    @Override
    public String toString() {
        return "RequestAtbak{" +
                "tanggalatbak='" + tanggalatbak + '\'' +
                ", jumlahatbak='" + jumlahatbak + '\'' +
                ", waktuatbak='" + waktuatbak + '\'' +
                '}';
    }
}
