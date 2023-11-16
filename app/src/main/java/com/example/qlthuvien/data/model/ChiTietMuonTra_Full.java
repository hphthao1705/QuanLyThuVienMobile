package com.example.qlthuvien.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChiTietMuonTra_Full {

    public int getId_ctmuon() {
        return id_ctmuon;
    }

    public void setId_ctmuon(int id_ctmuon) {
        this.id_ctmuon = id_ctmuon;
    }

    public int getId_muon() {
        return id_muon;
    }

    public void setId_muon(int id_muon) {
        this.id_muon = id_muon;
    }

    public int getId_tailieu() {
        return id_tailieu;
    }

    public void setId_tailieu(int id_tailieu) {
        this.id_tailieu = id_tailieu;
    }

    public String getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
    }

    public int getTinhtrangtra() {
        return tinhtrangtra;
    }

    public void setTinhtrangtra(int tinhtrangtra) {
        this.tinhtrangtra = tinhtrangtra;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTentailieu() {
        return tentailieu;
    }

    public void setTentailieu(String tentailieu) {
        this.tentailieu = tentailieu;
    }

    public ChiTietMuonTra_Full(int id_ctmuon, int id_muon, int id_tailieu, String ngaytra, int tinhtrangtra, String hinh, String tentailieu) {
        this.id_ctmuon = id_ctmuon;
        this.id_muon = id_muon;
        this.id_tailieu = id_tailieu;
        this.ngaytra = ngaytra;
        this.tinhtrangtra = tinhtrangtra;
        this.hinh = hinh;
        this.tentailieu = tentailieu;
    }

    @SerializedName("id_ctmuon")
    @Expose
    private int id_ctmuon;
    @SerializedName("id_muon")
    @Expose
    private int id_muon;
    @SerializedName("id_tailieu")
    @Expose
    private int id_tailieu;
    @SerializedName("ngaytra")
    @Expose
    private String ngaytra;
    @SerializedName("tinhtrangtra")
    @Expose
    private int tinhtrangtra;
    @SerializedName("hinh")
    @Expose
    private String hinh;
    @SerializedName("tentailieu")
    @Expose
    private String tentailieu;
}
