package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestJamu implements Serializable {
    private String tanggaljamu, jumlahjamu, waktujamu;

    public RequestJamu() {
    }

    public RequestJamu(String tanggaljamu, String jumlahjamu, String waktujamu) {
        this.tanggaljamu = tanggaljamu;
        this.jumlahjamu = jumlahjamu;
        this.waktujamu = waktujamu;
    }

    public String getTanggaljamu() {
        return tanggaljamu;
    }

    public void setTanggaljamu(String tanggaljamu) {
        this.tanggaljamu = tanggaljamu;
    }

    public String getJumlahjamu() {
        return jumlahjamu;
    }

    public void setJumlahjamu(String jumlahjamu) {
        this.jumlahjamu = jumlahjamu;
    }

    public String getWaktujamu() {
        return waktujamu;
    }

    public void setWaktujamu(String waktujamu) {
        this.waktujamu = waktujamu;
    }

    @Override
    public String toString() {
        return "RequestJamu{" +
                "tanggaljamu='" + tanggaljamu + '\'' +
                ", jumlahjamu='" + jumlahjamu + '\'' +
                ", waktujamu='" + waktujamu + '\'' +
                '}';
    }
}
