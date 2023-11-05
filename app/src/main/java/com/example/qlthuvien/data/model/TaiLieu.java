package com.example.qlthuvien.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaiLieu {
    public TaiLieu(int id_tailieu, int id_nxb, int id_loai, String tentailieu, int soluong, int namsanxuat, String tacgia, String hinh, String mota) {
        this.id_tailieu = id_tailieu;
        this.id_nxb = id_nxb;
        this.id_loai = id_loai;
        this.tentailieu = tentailieu;
        this.soluong = soluong;
        this.namsanxuat = namsanxuat;
        this.tacgia = tacgia;
        this.hinh = hinh;
        this.mota = mota;
    }

    public int getId_tailieu() {
        return id_tailieu;
    }

    public void setId_tailieu(int id_tailieu) {
        this.id_tailieu = id_tailieu;
    }

    public int getId_nxb() {
        return id_nxb;
    }

    public void setId_nxb(int id_nxb) {
        this.id_nxb = id_nxb;
    }

    public int getId_loai() {
        return id_loai;
    }

    public void setId_loai(int id_loai) {
        this.id_loai = id_loai;
    }

    public String getTentailieu() {
        return tentailieu;
    }

    public void setTentailieu(String tentailieu) {
        this.tentailieu = tentailieu;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getNamsanxuat() {
        return namsanxuat;
    }

    public void setNamsanxuat(int namsanxuat) {
        this.namsanxuat = namsanxuat;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    @SerializedName("id_tailieu")
    @Expose
    private int id_tailieu;
    @SerializedName("id_nxb")
    @Expose
    private int id_nxb;
    @SerializedName("id_loai")
    @Expose
    private int id_loai;
    @SerializedName("tentailieu")
    @Expose
    private String tentailieu;
    @SerializedName("soluong")
    @Expose
    private int soluong;
    @SerializedName("namsanxuat")
    @Expose
    private int namsanxuat;
    @SerializedName("tacgia")
    @Expose
    private String tacgia;
    @SerializedName("hinh")
    @Expose
    private String hinh;
    @SerializedName("mota")
    @Expose
    private String mota;

    public String getNgonngu() {
        return ngonngu;
    }

    public void setNgonngu(String ngonngu) {
        this.ngonngu = ngonngu;
    }

    public int getSotrang() {
        return sotrang;
    }

    public void setSotrang(int sotrang) {
        this.sotrang = sotrang;
    }

    @SerializedName("ngonngu")
    @Expose
    private String ngonngu;
    @SerializedName("sotrang")
    @Expose
    private int sotrang;
}
