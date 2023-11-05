package com.example.qlthuvien.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NhaXuatBan {
    public NhaXuatBan(int id_nxb, String tennxb, String diachi, String sodienthoai) {
        this.id_nxb = id_nxb;
        this.tennxb = tennxb;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
    }

    public int getId_nxb() {
        return id_nxb;
    }

    public void setId_nxb(int id_nxb) {
        this.id_nxb = id_nxb;
    }

    public String getTennxb() {
        return tennxb;
    }

    public void setTennxb(String tennxb) {
        this.tennxb = tennxb;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    @SerializedName("id_nxb")
    @Expose
    private int id_nxb;
    @SerializedName("tennxb")
    @Expose
    private String tennxb;
    @SerializedName("diachi")
    @Expose
    private String diachi;
    @SerializedName("sodienthoai")
    @Expose
    private String sodienthoai;
}
