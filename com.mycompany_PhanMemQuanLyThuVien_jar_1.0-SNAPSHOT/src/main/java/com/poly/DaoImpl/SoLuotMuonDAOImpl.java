/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.DaoImpl;

import com.fpoly.Dao.SoLuotMuonDAO;
import com.fpoly.entity.SoLuotMuon;
import com.fpoly.utils.XJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class SoLuotMuonDAOImpl implements SoLuotMuonDAO{
    @Override
    public List<SoLuotMuon> getAllBorrowCounts() {
        String sql = "SELECT ngayThang, SoLuotMuon, Ghichu FROM SoLuotMuon";
        
        List<SoLuotMuon> list = new ArrayList<>();
        try (Connection connection = XJDBC.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String ngay = rs.getString("ngayThang");
                String soluotMuon = rs.getString("SoLuotMuon");
                String Note = rs.getString("Ghichu");

                list.add(new SoLuotMuon(ngay, soluotMuon, Note));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<SoLuotMuon> getBorrowCountsByFilter(Integer month, Integer year) {
        return new ArrayList<>();
    }
}
