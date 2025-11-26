/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.entity;

/**
 *
 * @author LENOVO
 */
public class SoLuotMuon {
    private String Date;
    private String soluotMuon;
    private String Note;

    public SoLuotMuon() {
    }

    public String getDate() {
        return Date;
    }

    public String getSoluotMuon() {
        return soluotMuon;
    }

    public String getNote() {
        return Note;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setSoluotMuon(String soluotMuon) {
        this.soluotMuon = soluotMuon;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public SoLuotMuon(String Date, String soluotMuon, String Note) {
        this.Date = Date;
        this.soluotMuon = soluotMuon;
        this.Note = Note;
    }
    
    
}
