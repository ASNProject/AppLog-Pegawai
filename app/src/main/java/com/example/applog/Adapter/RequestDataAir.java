package com.example.applog.Adapter;

import java.io.Serializable;

public class RequestDataAir implements Serializable {
    private String tanggalair, tinggiair, dopagi, domalam, phpagi, phmalam,
    kecerahan, alkalinitas, suhu, cas, mg, no2, no3, nh3, warna;

    public RequestDataAir() {
    }

    public RequestDataAir(String tanggalair, String tinggiair, String dopagi, String domalam, String phpagi, String phmalam, String kecerahan, String alkalinitas, String suhu, String cas, String mg, String no2, String no3, String nh3) {
        this.tanggalair = tanggalair;
        this.tinggiair = tinggiair;
        this.dopagi = dopagi;
        this.domalam = domalam;
        this.phpagi = phpagi;
        this.phmalam = phmalam;
        this.kecerahan = kecerahan;
        this.alkalinitas = alkalinitas;
        this.suhu = suhu;
        this.cas = cas;
        this.mg = mg;
        this.no2 = no2;
        this.no3 = no3;
        this.nh3 = nh3;
        this.warna = warna;
    }

    public String getTanggalair() {
        return tanggalair;
    }

    public void setTanggalair(String tanggalair) {
        this.tanggalair = tanggalair;
    }

    public String getTinggiair() {
        return tinggiair;
    }

    public void setTinggiair(String tinggiair) {
        this.tinggiair = tinggiair;
    }

    public String getDopagi() {
        return dopagi;
    }

    public void setDopagi(String dopagi) {
        this.dopagi = dopagi;
    }

    public String getDomalam() {
        return domalam;
    }

    public void setDomalam(String domalam) {
        this.domalam = domalam;
    }

    public String getPhpagi() {
        return phpagi;
    }

    public void setPhpagi(String phpagi) {
        this.phpagi = phpagi;
    }

    public String getPhmalam() {
        return phmalam;
    }

    public void setPhmalam(String phmalam) {
        this.phmalam = phmalam;
    }

    public String getKecerahan() {
        return kecerahan;
    }

    public void setKecerahan(String kecerahan) {
        this.kecerahan = kecerahan;
    }

    public String getAlkalinitas() {
        return alkalinitas;
    }

    public void setAlkalinitas(String alkalinitas) {
        this.alkalinitas = alkalinitas;
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getMg() {
        return mg;
    }

    public void setMg(String mg) {
        this.mg = mg;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getNo3() {
        return no3;
    }

    public void setNo3(String no3) {
        this.no3 = no3;
    }

    public String getNh3() {
        return nh3;
    }

    public void setNh3(String nh3) {
        this.nh3 = nh3;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    @Override
    public String toString() {
        return "RequestDataAir{" +
                "tanggalair='" + tanggalair + '\'' +
                ", tinggiair='" + tinggiair + '\'' +
                ", dopagi='" + dopagi + '\'' +
                ", domalam='" + domalam + '\'' +
                ", phpagi='" + phpagi + '\'' +
                ", phmalam='" + phmalam + '\'' +
                ", kecerahan='" + kecerahan + '\'' +
                ", alkalinitas='" + alkalinitas + '\'' +
                ", suhu='" + suhu + '\'' +
                ", cas='" + cas + '\'' +
                ", mg='" + mg + '\'' +
                ", no2='" + no2 + '\'' +
                ", no3='" + no3 + '\'' +
                ", nh3='" + nh3 + '\'' +
                ", warna='" + warna + '\'' +
                '}';
    }
}
