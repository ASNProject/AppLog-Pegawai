package com.example.applog.Adapter;

import java.io.Serializable;

public class Request_Data_Kolam implements Serializable {
    private String tanggaltebar, namakolam, namapetani,
                    luasarea, jumlahtebarekor, jumlahtebarsampling, kepadatankolam,
                    feedkolam, keterangankolam, kepadatanaktual;

    public Request_Data_Kolam() {
    }

    public Request_Data_Kolam(String tanggaltebar, String namakolam, String namapetani, String luasarea, String jumlahtebarekor, String jumlahtebarsampling, String kepadatankolam, String feedkolam, String keterangankolam, String kepadatanaktual) {
        this.tanggaltebar = tanggaltebar;
        this.namakolam = namakolam;
        this.namapetani = namapetani;
        this.luasarea = luasarea;
        this.jumlahtebarekor = jumlahtebarekor;
        this.jumlahtebarsampling = jumlahtebarsampling;
        this.kepadatankolam = kepadatankolam;
        this.feedkolam = feedkolam;
        this.keterangankolam = keterangankolam;
        this.kepadatanaktual = kepadatanaktual;
    }

    public String getTanggaltebar() {
        return tanggaltebar;
    }

    public void setTanggaltebar(String tanggaltebar) {
        this.tanggaltebar = tanggaltebar;
    }

    public String getNamakolam() {
        return namakolam;
    }

    public void setNamakolam(String namakolam) {
        this.namakolam = namakolam;
    }

    public String getNamapetani() {
        return namapetani;
    }

    public void setNamapetani(String namapetani) {
        this.namapetani = namapetani;
    }

    public String getLuasarea() {
        return luasarea;
    }

    public void setLuasarea(String luasarea) {
        this.luasarea = luasarea;
    }

    public String getJumlahtebarekor() {
        return jumlahtebarekor;
    }

    public void setJumlahtebarekor(String jumlahtebarekor) {
        this.jumlahtebarekor = jumlahtebarekor;
    }

    public String getJumlahtebarsampling() {
        return jumlahtebarsampling;
    }

    public void setJumlahtebarsampling(String jumlahtebarsampling) {
        this.jumlahtebarsampling = jumlahtebarsampling;
    }

    public String getKepadatankolam() {
        return kepadatankolam;
    }

    public void setKepadatankolam(String kepadatankolam) {
        this.kepadatankolam = kepadatankolam;
    }

    public String getFeedkolam() {
        return feedkolam;
    }

    public void setFeedkolam(String feedkolam) {
        this.feedkolam = feedkolam;
    }

    public String getKeterangankolam() {
        return keterangankolam;
    }

    public void setKeterangankolam(String keterangankolam) {
        this.keterangankolam = keterangankolam;
    }

    public String getKepadatanaktual() {
        return kepadatanaktual;
    }

    public void setKepadatanaktual(String kepadatanaktual) {
        this.kepadatanaktual = kepadatanaktual;
    }

    @Override
    public String toString() {
        return "Request_Data_Kolam{" +
                "tanggaltebar='" + tanggaltebar + '\'' +
                ", namakolam='" + namakolam + '\'' +
                ", namapetani='" + namapetani + '\'' +
                ", luasarea='" + luasarea + '\'' +
                ", jumlahtebarekor='" + jumlahtebarekor + '\'' +
                ", jumlahtebarsampling='" + jumlahtebarsampling + '\'' +
                ", kepadatankolam='" + kepadatankolam + '\'' +
                ", feedkolam='" + feedkolam + '\'' +
                ", keterangankolam='" + keterangankolam + '\'' +
                ", kepadatanaktual='" + kepadatanaktual + '\'' +
                '}';
    }
}

