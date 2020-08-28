package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestUpdateAbusekam implements Serializable {
    private String tanggalabusekam, jumlahabusekam, waktuabusekam;

    public RequestUpdateAbusekam() {
    }

    public RequestUpdateAbusekam(String tanggalabusekam, String jumlahabusekam, String waktuabusekam) {
        this.tanggalabusekam = tanggalabusekam;
        this.jumlahabusekam = jumlahabusekam;
        this.waktuabusekam = waktuabusekam;
    }

    public String getTanggalabusekam() {
        return tanggalabusekam;
    }

    public void setTanggalabusekam(String tanggalabusekam) {
        this.tanggalabusekam = tanggalabusekam;
    }

    public String getJumlahabusekam() {
        return jumlahabusekam;
    }

    public void setJumlahabusekam(String jumlahabusekam) {
        this.jumlahabusekam = jumlahabusekam;
    }

    public String getWaktuabusekam() {
        return waktuabusekam;
    }

    public void setWaktuabusekam(String waktuabusekam) {
        this.waktuabusekam = waktuabusekam;
    }

    @Override
    public String toString() {
        return "RequestAbusekam{" +
                "tanggalabusekam='" + tanggalabusekam + '\'' +
                ", jumlahabusekam='" + jumlahabusekam + '\'' +
                ", waktuabusekam='" + waktuabusekam + '\'' +
                '}';
    }
}
