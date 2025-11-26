/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.entity;

import java.time.LocalDate;

/**
 *
 * @author LENOVO
 */
public class Borrow {
    private String maPhieu;
    private String maSV;
    private String tenSV;
    private String tenSach;
    private String ngayTra;
    private String soNgaytre;

    public Borrow() {
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public String getTenSach() {
        return tenSach;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public String getSoNgaytre() {
        return soNgaytre;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public void setSoNgaytre(String soNgaytre) {
        this.soNgaytre = soNgaytre;
    }

    public Borrow(String maPhieu, String maSV, String tenSV, String tenSach, String ngayTra, String soNgaytre) {
        this.maPhieu = maPhieu;
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.tenSach = tenSach;
        this.ngayTra = ngayTra;
        this.soNgaytre = soNgaytre;
    }
    
    public String getSoNgayTre() {
    return soNgaytre; // trả về giá trị đã lưu
}

    
    
    
}
