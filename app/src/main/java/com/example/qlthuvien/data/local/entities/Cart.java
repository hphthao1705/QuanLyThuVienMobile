package com.example.qlthuvien.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart")
public class Cart {
    @PrimaryKey(autoGenerate = true) // Define the primary key with auto-increment
    private int id;
    @ColumnInfo(name = "id_tailieu")
    private int id_tailieu;
    @ColumnInfo(name = "id_dg")
    private int id_dg;
    @ColumnInfo(name = "hinh")
    private String hinh;
    @ColumnInfo(name="tensach")
    private String tensach;
    @ColumnInfo(name="tacgia")
    private String tacgia;

    public int getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(int checkbox) {
        this.checkbox = checkbox;
    }

    @ColumnInfo(name="checkbox")
    private int checkbox;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tailieu() {
        return id_tailieu;
    }

    public void setId_tailieu(int id_tailieu) {
        this.id_tailieu = id_tailieu;
    }

    public int getId_dg() {
        return id_dg;
    }

    public void setId_dg(int id_dg) {
        this.id_dg = id_dg;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }


    public Cart(int id_tailieu, int id_dg, String hinh, String tensach, String tacgia, int checkbox) {
        this.id_tailieu = id_tailieu;
        this.id_dg = id_dg;
        this.hinh = hinh;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.checkbox = checkbox;
    }
}
