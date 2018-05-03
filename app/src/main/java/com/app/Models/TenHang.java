package com.app.Models;

/**
 * Created by ThanhCong on 23/4/2018.
 */

public class TenHang {
    private String ma,ten,hinhanh,chitiet,dongia,donvi;

    public TenHang() {
    }

    public TenHang(String ma, String ten, String hinhanh, String chitiet, String dongia, String donvi) {
        this.ma = ma;
        this.ten = ten;
        this.hinhanh = hinhanh;
        this.chitiet = chitiet;
        this.dongia = dongia;
        this.donvi = donvi;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }
}
