/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fpoly.Dao;

import com.fpoly.entity.SoLuotMuon;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface SoLuotMuonDAO {
    List<SoLuotMuon> getAllBorrowCounts();
    List<SoLuotMuon> getBorrowCountsByFilter(Integer month, Integer year);
    List<SoLuotMuon> searchBorrowCountsByDate(String dateKeyword);

}
