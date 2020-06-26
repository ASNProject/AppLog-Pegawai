package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestUpdatePerlakuan implements Serializable {
    private String  ntanggalperlakuan, nperlakuan;

    public RequestUpdatePerlakuan() {
    }

    public RequestUpdatePerlakuan(String ntanggalperlakuan, String nperlakuan) {
        this.ntanggalperlakuan = ntanggalperlakuan;
        this.nperlakuan = nperlakuan;
    }

    public String getNtanggalperlakuan() {
        return ntanggalperlakuan;
    }

    public void setNtanggalperlakuan(String ntanggalperlakuan) {
        this.ntanggalperlakuan = ntanggalperlakuan;
    }

    public String getNperlakuan() {
        return nperlakuan;
    }

    public void setNperlakuan(String nperlakuan) {
        this.nperlakuan = nperlakuan;
    }

    @Override
    public String toString() {
        return "RequestUpdatePerlakuan{" +
                "ntanggalperlakuan='" + ntanggalperlakuan + '\'' +
                ", nperlakuan='" + nperlakuan + '\'' +
                '}';
    }
}
