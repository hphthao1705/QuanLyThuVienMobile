package com.example.qlthuvien.data.model;

import android.util.Patterns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SinhVien {
    public SinhVien(){

    }
    public SinhVien(int id_sv,  String tensv, String mssv, int gioitinh, String ngaysinh) {
        this.id_sv = id_sv;
        this.tensv = tensv;
        this.mssv = mssv;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }


    public int getId_sv() {
        return id_sv;
    }

    public void setId_sv(int id_sv) {
        this.id_sv = id_sv;
    }
    @SerializedName("id_sv")
    @Expose
    private int id_sv;
    @SerializedName("tensv")
    @Expose
    private String tensv;
    @SerializedName("mssv")
    @Expose
    private String mssv;
    @SerializedName("gioitinh")
    @Expose
    private int gioitinh;
    @SerializedName("ngaysinh")
    @Expose
    private String ngaysinh;

}