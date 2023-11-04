package com.example.qlthuvien.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MuonTra {
    public MuonTra(int id_muon, int id_dg, Date ngaymuon, int tintrangmuon, int id_nv) {
        this.id_muon = id_muon;
        this.id_dg = id_dg;
        this.ngaymuon = ngaymuon;
        this.tintrangmuon = tintrangmuon;
        this.id_nv = id_nv;
    }

    public int getId_muon() {
        return id_muon;
    }

    public void setId_muon(int id_muon) {
        this.id_muon = id_muon;
    }

    public int getId_dg() {
        return id_dg;
    }

    public void setId_dg(int id_dg) {
        this.id_dg = id_dg;
    }

    public Date getNgaymuon() {
        return ngaymuon;
    }

    public void setNgaymuon(Date ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    public int getTintrangmuon() {
        return tintrangmuon;
    }

    public void setTintrangmuon(int tintrangmuon) {
        this.tintrangmuon = tintrangmuon;
    }

    public int getId_nv() {
        return id_nv;
    }

    public void setId_nv(int id_nv) {
        this.id_nv = id_nv;
    }

    @SerializedName("id_muon")
    @Expose
    private int id_muon;
    @SerializedName("id_dg")
    @Expose
    private int id_dg;
    @SerializedName("ngaymuon")
    @Expose
    private Date ngaymuon;
    @SerializedName("tinhtrangmuon")
    @Expose
    private int tintrangmuon;
    @SerializedName("id_nv")
    @Expose
    private int id_nv;
}
