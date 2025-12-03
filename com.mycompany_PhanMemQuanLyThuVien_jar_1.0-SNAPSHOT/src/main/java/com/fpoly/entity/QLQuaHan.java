/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.entity;

/**
 *
 * @author hạ
 */
public class QLQuaHan {
    private String maPhieu;
    private String khachHang;
    private String tenSach;
    private String hanTra;
    private String trangThai;
    private String ghiChu;

    public QLQuaHan(String maPhieu, String khachHang, String tenSach, String hanTra, String trangThai, String ghiChu) {
        this.maPhieu = maPhieu;
        this.khachHang = khachHang;
        this.tenSach = tenSach;
        this.hanTra = hanTra;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    // Getter & Setter
    public String getMaPhieu() { return maPhieu; }
    public void setMaPhieu(String maPhieu) { this.maPhieu = maPhieu; }
    public String getKhachHang() { return khachHang; }
    public void setKhachHang(String khachHang) { this.khachHang = khachHang; }
    public String getTenSach() { return tenSach; }
    public void setTenSach(String tenSach) { this.tenSach = tenSach; }
    public String getHanTra() { return hanTra; }
    public void setHanTra(String hanTra) { this.hanTra = hanTra; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}