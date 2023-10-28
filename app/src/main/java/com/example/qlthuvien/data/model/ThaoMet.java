package com.example.qlthuvien.data.model;

import java.util.Comparator;

public class ThaoMet {
    public ThaoMet(int id_tailieu, int solanmuon) {
        this.id_tailieu = id_tailieu;
        this.solanmuon = solanmuon;
    }

    public int getId_tailieu() {
        return id_tailieu;
    }

    public void setId_tailieu(int id_tailieu) {
        this.id_tailieu = id_tailieu;
    }

    public int getSolanmuon() {
        return solanmuon;
    }

    public void setSolanmuon(int solanmuon) {
        this.solanmuon = solanmuon;
    }

    private int id_tailieu;
    private int solanmuon;
}
