package com.example.qlthuvien.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocGia {
    public DocGia(int id_dg, int id_sv, String email, int password) {
        this.id_dg = id_dg;
        this.id_sv = id_sv;
        this.email = email;
        this.password = password;
    }

    public int getId_dg() {
        return id_dg;
    }

    public void setId_dg(int id_dg) {
        this.id_dg = id_dg;
    }

    public int getId_sv() {
        return id_sv;
    }

    public void setId_sv(int id_sv) {
        this.id_sv = id_sv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @SerializedName("id_dg")
    @Expose
    private int id_dg;
    @SerializedName("id_sv")
    @Expose
    private int id_sv;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private int password;

}
