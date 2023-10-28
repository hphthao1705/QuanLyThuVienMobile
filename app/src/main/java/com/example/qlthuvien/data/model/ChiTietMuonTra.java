package com.example.qlthuvien.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ChiTietMuonTra {
    public ChiTietMuonTra(int id_muon, int id_tailieu, Date ngaytra, int tinhtrangtra) {
        this.id_muon = id_muon;
        this.id_tailieu = id_tailieu;
        this.ngaytra = ngaytra;
        this.tinhtrangtra = tinhtrangtra;
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

    public Date getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(Date ngaytra) {
        this.ngaytra = ngaytra;
    }

    public int getTinhtrangtra() {
        return tinhtrangtra;
    }

    public void setTinhtrangtra(int tinhtrangtra) {
        this.tinhtrangtra = tinhtrangtra;
    }

    @SerializedName("id_muon")
    @Expose
    private int id_muon;
    @SerializedName("tailieu")
    @Expose
    private int id_tailieu;
    @SerializedName("ngaytra")
    @Expose
    private Date ngaytra;
    @SerializedName("tinhtrangtra")
    @Expose
    private int tinhtrangtra;
}
