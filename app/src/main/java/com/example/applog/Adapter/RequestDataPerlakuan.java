package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestDataPerlakuan implements Serializable {
    private String tanggalperlakuan, perlakuan;

    public RequestDataPerlakuan() {
    }

    public RequestDataPerlakuan(String tanggalperlakuan, String perlakuan) {
        this.tanggalperlakuan = tanggalperlakuan;
        this.perlakuan = perlakuan;
    }

    public String getTanggalperlakuan() {
        return tanggalperlakuan;
    }

    public void setTanggalperlakuan(String tanggalperlakuan) {
        this.tanggalperlakuan = tanggalperlakuan;
    }

    public String getPerlakuan() {
        return perlakuan;
    }

    public void setPerlakuan(String perlakuan) {
        this.perlakuan = perlakuan;
    }

    @Override
    public String toString() {
        return "RequestDataPerlakuan{" +
                "tanggalperlakuan='" + tanggalperlakuan + '\'' +
                ", perlakuan='" + perlakuan + '\'' +
                '}';
    }
}
