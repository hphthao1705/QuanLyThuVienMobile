package com.example.qlthuvien.data.model;

public class Item_Loai {
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
