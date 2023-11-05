package com.example.qlthuvien.data.model;

public class Item_Loai {
    public int getId_loai() {
        return id_loai;
    }

    public void setId_loai(int id_loai) {
        this.id_loai = id_loai;
    }

    int id_loai;


    public Item_Loai(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }
    public  Item_Loai()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    String name;
    String icon;
}
