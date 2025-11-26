/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.entity;

/**
 *
 * @author LENOVO
 */
public class statistical {

    private String Ma;
    private String ten;
    private Integer luotmuon;
    private String tenTacgia;
    private Integer NamXuatban;

    public statistical() {
    }

    public String getMa() {
        return Ma;
    }

    public String getTen() {
        return ten;
    }

    public Integer getLuotmuon() {
        return luotmuon;
    }

    public String getTenTacgia() {
        return tenTacgia;
    }

    public Integer getNamXuatban() {
        return NamXuatban;
    }

    public void setMa(String Ma) {
        this.Ma = Ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setLuotmuon(Integer luotmuon) {
        this.luotmuon = luotmuon;
    }

    public void setTenTacgia(String tenTacgia) {
        this.tenTacgia = tenTacgia;
    }

    public void setNamXuatban(Integer NamXuatban) {
        this.NamXuatban = NamXuatban;
    }

    public statistical(String Ma, String ten, Integer luotmuon, String tenTacgia, Integer NamXuatban) {
        this.Ma = Ma;
        this.ten = ten;
        this.luotmuon = luotmuon;
        this.tenTacgia = tenTacgia;
        this.NamXuatban = NamXuatban;
    }
    
    
    
    
}
