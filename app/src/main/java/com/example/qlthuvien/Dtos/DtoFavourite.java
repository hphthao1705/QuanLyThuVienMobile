package com.example.qlthuvien.Dtos;

public class DtoFavourite {
    public  String Id;
    public  String TenSach;
    public  String TacGia;
    public  Integer HinhSach;

    public DtoFavourite() {
    }

    public DtoFavourite(String id, String tenSach, String tacGia, Integer hinhSach) {
        Id = id;
        TenSach = tenSach;
        TacGia = tacGia;
        HinhSach = hinhSach;
    }
}
