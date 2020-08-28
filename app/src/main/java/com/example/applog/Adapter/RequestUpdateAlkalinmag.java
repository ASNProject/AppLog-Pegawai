package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestUpdateAlkalinmag implements Serializable {
    private String tanggalalkalinmag, jumlahalkalinmag, waktualkalinmag;

    public RequestUpdateAlkalinmag() {
    }

    public RequestUpdateAlkalinmag(String tanggalalkalinmag, String jumlahalkalinmag, String waktualkalinmag) {
        this.tanggalalkalinmag = tanggalalkalinmag;
        this.jumlahalkalinmag = jumlahalkalinmag;
        this.waktualkalinmag = waktualkalinmag;
    }

    public String getTanggalalkalinmag() {
        return tanggalalkalinmag;
    }

    public void setTanggalalkalinmag(String tanggalalkalinmag) {
        this.tanggalalkalinmag = tanggalalkalinmag;
    }

    public String getJumlahalkalinmag() {
        return jumlahalkalinmag;
    }

    public void setJumlahalkalinmag(String jumlahalkalinmag) {
        this.jumlahalkalinmag = jumlahalkalinmag;
    }

    public String getWaktualkalinmag() {
        return waktualkalinmag;
    }

    public void setWaktualkalinmag(String waktualkalinmag) {
        this.waktualkalinmag = waktualkalinmag;
    }

    @Override
    public String toString() {
        return "RequestAlkalinmag{" +
                "tanggalalkalinmag='" + tanggalalkalinmag + '\'' +
                ", jumlahalkalinmag='" + jumlahalkalinmag + '\'' +
                ", waktualkalinmag='" + waktualkalinmag + '\'' +
                '}';
    }
}
