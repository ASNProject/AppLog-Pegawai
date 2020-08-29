package com.example.applog.Adapter;

public class Upload {
    private String mName;
    private String mTanggal;
    private String mDeskripsi;
    private String mImageUrl;

    public Upload() {
    }

    public Upload(String name, String imageUrl, String tanggal, String deskripsi){
        if (name.trim().equals("")){
            name = "Tidak ada nama";
        }
        if (deskripsi.trim().equals("")){
            deskripsi = "Tidak ada deskripsi";
        }
        mName = name;
        mTanggal = tanggal;
        mDeskripsi = deskripsi;
        mImageUrl = imageUrl;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmTanggal() {
        return mTanggal;
    }

    public void setmTanggal(String mTanggal) {
        this.mTanggal = mTanggal;
    }

    public String getmDeskripsi() {
        return mDeskripsi;
    }

    public void setmDeskripsi(String mDeskripsi) {
        this.mDeskripsi = mDeskripsi;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
