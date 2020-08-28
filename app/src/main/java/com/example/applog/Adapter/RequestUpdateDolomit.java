package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestUpdateDolomit implements Serializable {
    private String tanggaldolomit, jumlahdolomit, waktudolomit;

    public RequestUpdateDolomit() {
    }

    public RequestUpdateDolomit(String tanggaldolomit, String jumlahdolomit, String waktudolomit) {
        this.tanggaldolomit = tanggaldolomit;
        this.jumlahdolomit = jumlahdolomit;
        this.waktudolomit = waktudolomit;
    }

    public String getTanggaldolomit() {
        return tanggaldolomit;
    }

    public void setTanggaldolomit(String tanggaldolomit) {
        this.tanggaldolomit = tanggaldolomit;
    }

    public String getJumlahdolomit() {
        return jumlahdolomit;
    }

    public void setJumlahdolomit(String jumlahdolomit) {
        this.jumlahdolomit = jumlahdolomit;
    }

    public String getWaktudolomit() {
        return waktudolomit;
    }

    public void setWaktudolomit(String waktudolomit) {
        this.waktudolomit = waktudolomit;
    }

    @Override
    public String toString() {
        return "RequestDolomit{" +
                "tanggaldolomit='" + tanggaldolomit + '\'' +
                ", jumlahdolomit='" + jumlahdolomit + '\'' +
                ", waktudolomit='" + waktudolomit + '\'' +
                '}';
    }
}
