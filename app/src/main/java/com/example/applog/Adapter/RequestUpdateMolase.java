package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestUpdateMolase implements Serializable {
    private String tanggalmolase, jumlahmolase, waktumolase;

    public RequestUpdateMolase() {
    }

    public RequestUpdateMolase(String tanggalmolase, String jumlahmolase, String waktumolase) {
        this.tanggalmolase = tanggalmolase;
        this.jumlahmolase = jumlahmolase;
        this.waktumolase = waktumolase;
    }

    public String getTanggalmolase() {
        return tanggalmolase;
    }

    public void setTanggalmolase(String tanggalmolase) {
        this.tanggalmolase = tanggalmolase;
    }

    public String getJumlahmolase() {
        return jumlahmolase;
    }

    public void setJumlahmolase(String jumlahmolase) {
        this.jumlahmolase = jumlahmolase;
    }

    public String getWaktumolase() {
        return waktumolase;
    }

    public void setWaktumolase(String waktumolase) {
        this.waktumolase = waktumolase;
    }

    @Override
    public String toString() {
        return "RequestMolase{" +
                "tanggalmolase='" + tanggalmolase + '\'' +
                ", jumlahmolase='" + jumlahmolase + '\'' +
                ", waktumolase='" + waktumolase + '\'' +
                '}';
    }
}
