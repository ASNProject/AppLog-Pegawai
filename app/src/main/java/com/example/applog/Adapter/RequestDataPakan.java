package com.example.applog.Adapter;

import android.widget.EditText;

import java.io.Serializable;

public class RequestDataPakan implements Serializable {
    private String tanggalpakan, kodepakan, jam6, jam10, jam14, jam18, jam22, jumlahharian, jumlahtotal, keteranganpakan,  usia;

    public RequestDataPakan() {
    }

    public RequestDataPakan(String tanggalpakan, String kodepakan, String jam6, String jam10, String jam14, String jam18, String jam22, String jumlahharian, String jumlahtotal, String keteranganpakan, String usia) {
        this.tanggalpakan = tanggalpakan;
        this.kodepakan = kodepakan;
        this.jam6 = jam6;
        this.jam10 = jam10;
        this.jam14 = jam14;
        this.jam18 = jam18;
        this.jam22 = jam22;
        this.jumlahharian = jumlahharian;
        this.jumlahtotal = jumlahtotal;
        this.keteranganpakan = keteranganpakan;
        this.usia = usia;
    }

    public String getTanggalpakan() {
        return tanggalpakan;
    }

    public void setTanggalpakan(String tanggalpakan) {
        this.tanggalpakan = tanggalpakan;
    }

    public String getKodepakan() {
        return kodepakan;
    }

    public void setKodepakan(String kodepakan) {
        this.kodepakan = kodepakan;
    }

    public String getJam6() {
        return jam6;
    }

    public void setJam6(String jam6) {
        this.jam6 = jam6;
    }

    public String getJam10() {
        return jam10;
    }

    public void setJam10(String jam10) {
        this.jam10 = jam10;
    }

    public String getJam14() {
        return jam14;
    }

    public void setJam14(String jam14) {
        this.jam14 = jam14;
    }

    public String getJam18() {
        return jam18;
    }

    public void setJam18(String jam18) {
        this.jam18 = jam18;
    }

    public String getJam22() {
        return jam22;
    }

    public void setJam22(String jam22) {
        this.jam22 = jam22;
    }

    public String getJumlahharian() {
        return jumlahharian;
    }

    public void setJumlahharian(String jumlahharian) {
        this.jumlahharian = jumlahharian;
    }

    public String getJumlahtotal() {
        return jumlahtotal;
    }

    public void setJumlahtotal(String jumlahtotal) {
        this.jumlahtotal = jumlahtotal;
    }

    public String getKeteranganpakan() {
        return keteranganpakan;
    }

    public void setKeteranganpakan(String keteranganpakan) {
        this.keteranganpakan = keteranganpakan;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    @Override
    public String toString() {
        return "RequestDataPakan{" +
                "tanggalpakan='" + tanggalpakan + '\'' +
                ", kodepakan='" + kodepakan + '\'' +
                ", jam6='" + jam6 + '\'' +
                ", jam10='" + jam10 + '\'' +
                ", jam14='" + jam14 + '\'' +
                ", jam18='" + jam18 + '\'' +
                ", jam22='" + jam22 + '\'' +
                ", jumlahharian='" + jumlahharian + '\'' +
                ", jumlahtotal='" + jumlahtotal + '\'' +
                ", keteranganpakan='" + keteranganpakan + '\'' +
                ", usia='" + usia + '\'' +
                '}';
    }
}

