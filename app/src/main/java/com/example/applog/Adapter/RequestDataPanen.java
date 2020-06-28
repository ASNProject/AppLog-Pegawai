package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestDataPanen implements Serializable {
    private String tanggalpanen, doc, tonase, abw, size, populasipanen, tonha, totalpopulasi, panentotal, totalsr, totalpakan, fcrtotal;

    public RequestDataPanen() {
    }

    public RequestDataPanen(String tanggalpanen, String doc, String tonase, String abw, String size, String populasipanen, String tonha, String totalpopulasi, String panentotal, String totalsr, String totalpakan, String fcrtotal) {
        this.tanggalpanen = tanggalpanen;
        this.doc = doc;
        this.tonase = tonase;
        this.abw = abw;
        this.size = size;
        this.populasipanen = populasipanen;
        this.tonha = tonha;
        this.totalpopulasi = totalpopulasi;
        this.panentotal = panentotal;
        this.totalsr = totalsr;
        this.totalpakan = totalpakan;
        this.fcrtotal = fcrtotal;
    }

    public String getTanggalpanen() {
        return tanggalpanen;
    }

    public void setTanggalpanen(String tanggalpanen) {
        this.tanggalpanen = tanggalpanen;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getTonase() {
        return tonase;
    }

    public void setTonase(String tonase) {
        this.tonase = tonase;
    }

    public String getAbw() {
        return abw;
    }

    public void setAbw(String abw) {
        this.abw = abw;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPopulasipanen() {
        return populasipanen;
    }

    public void setPopulasipanen(String populasipanen) {
        this.populasipanen = populasipanen;
    }

    public String getTonha() {
        return tonha;
    }

    public void setTonha(String tonha) {
        this.tonha = tonha;
    }

    public String getTotalpopulasi() {
        return totalpopulasi;
    }

    public void setTotalpopulasi(String totalpopulasi) {
        this.totalpopulasi = totalpopulasi;
    }

    public String getPanentotal() {
        return panentotal;
    }

    public void setPanentotal(String panentotal) {
        this.panentotal = panentotal;
    }

    public String getTotalsr() {
        return totalsr;
    }

    public void setTotalsr(String totalsr) {
        this.totalsr = totalsr;
    }

    public String getTotalpakan() {
        return totalpakan;
    }

    public void setTotalpakan(String totalpakan) {
        this.totalpakan = totalpakan;
    }

    public String getFcrtotal() {
        return fcrtotal;
    }

    public void setFcrtotal(String fcrtotal) {
        this.fcrtotal = fcrtotal;
    }

    @Override
    public String toString() {
        return "RequestDataPanen{" +
                "tanggalpanen='" + tanggalpanen + '\'' +
                ", doc='" + doc + '\'' +
                ", tonase='" + tonase + '\'' +
                ", abw='" + abw + '\'' +
                ", size='" + size + '\'' +
                ", populasipanen='" + populasipanen + '\'' +
                ", tonha='" + tonha + '\'' +
                ", totalpopulasi='" + totalpopulasi + '\'' +
                ", panentotal='" + panentotal + '\'' +
                ", totalsr='" + totalsr + '\'' +
                ", totalpakan='" + totalpakan + '\'' +
                ", fcrtotal='" + fcrtotal + '\'' +
                '}';
    }
}
