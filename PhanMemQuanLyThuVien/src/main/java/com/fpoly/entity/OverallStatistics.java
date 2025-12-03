/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.entity;

/**
 *
 * @author LENOVO
 */
public class OverallStatistics {
    private int tongSachDauVao;
    private int sachDaMuonChuaTra;
    private int sachHienCon;

    public OverallStatistics(int tongSachDauVao, int sachDaMuonChuaTra, int sachHienCon) {
        this.tongSachDauVao = tongSachDauVao;
        this.sachDaMuonChuaTra = sachDaMuonChuaTra;
        this.sachHienCon = sachHienCon;
    }

    // Getters
    public int getTongSachDauVao() { return tongSachDauVao; }
    public int getSachDaMuonChuaTra() { return sachDaMuonChuaTra; }
    public int getSachHienCon() { return sachHienCon; }

    // Setters (nếu cần)
    // ...
}
