package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestDaunkates implements Serializable {
    private String tanggaldaunkates, jumlahdaunkates, waktudaunkates;

    public RequestDaunkates() {
    }

    public RequestDaunkates(String tanggaldaunkates, String jumlahdaunkates, String waktudaunkates) {
        this.tanggaldaunkates = tanggaldaunkates;
        this.jumlahdaunkates = jumlahdaunkates;
        this.waktudaunkates = waktudaunkates;
    }

    public String getTanggaldaunkates() {
        return tanggaldaunkates;
    }

    public void setTanggaldaunkates(String tanggaldaunkates) {
        this.tanggaldaunkates = tanggaldaunkates;
    }

    public String getJumlahdaunkates() {
        return jumlahdaunkates;
    }

    public void setJumlahdaunkates(String jumlahdaunkates) {
        this.jumlahdaunkates = jumlahdaunkates;
    }

    public String getWaktudaunkates() {
        return waktudaunkates;
    }

    public void setWaktudaunkates(String waktudaunkates) {
        this.waktudaunkates = waktudaunkates;
    }

    @Override
    public String toString() {
        return "RequestDaunkates{" +
                "tanggaldaunkates='" + tanggaldaunkates + '\'' +
                ", jumlahdaunkates='" + jumlahdaunkates + '\'' +
                ", waktudaunkates='" + waktudaunkates + '\'' +
                '}';
    }
}
