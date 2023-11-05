package com.example.qlthuvien.dto;

public class DtoFavourite {
    public  String Id;
    public  String TenSach;
    public  String TacGia;
    public  Integer HinhSach;
    public Integer Check;

    public DtoFavourite() {
    }

    public DtoFavourite(String id, String tenSach, String tacGia, Integer hinhSach) {
        Id = id;
        TenSach = tenSach;
        TacGia = tacGia;
        HinhSach = hinhSach;
    }
}
