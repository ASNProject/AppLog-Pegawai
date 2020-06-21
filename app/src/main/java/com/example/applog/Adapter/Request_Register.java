package com.example.applog.Adapter;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class Request_Register implements Serializable {
    private String email, username, password, alamat, notelp;

    public Request_Register() {
    }

    public Request_Register(String email, String username, String password, String alamat, String notelp) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.notelp = notelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    @Override
    public String toString() {
        return "Request_Register{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", alamat='" + alamat + '\'' +
                ", notelp='" + notelp + '\'' +
                '}';
    }
}

