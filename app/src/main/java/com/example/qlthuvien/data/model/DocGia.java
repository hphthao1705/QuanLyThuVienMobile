package com.example.qlthuvien.data.model;

import android.util.Patterns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocGia {

    public DocGia()
    {

    }
    public DocGia(int id_dg, int id_sv, String email, String password) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
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
    private String password;


    @SerializedName("response")
    @Expose()
    public SinhVien loginresponse;

    public SinhVien getLoginresponse() {
        return loginresponse;
    }

}
