/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.DaoImpl;

import com.fpoly.Dao.StatisticalDAO;
import com.fpoly.entity.statistical;
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
public class StatisticalDAOImpl implements StatisticalDAO{
   @Override
public List<statistical> getAllTopBorrowedBooks() {
    
    String sql = "SELECT MaSach, TenSach, LuotMuon, TenTacGia, NamXuatBan " +
                 "FROM Book " + 
                 "ORDER BY LuotMuon DESC";
        
        List<statistical> list = new ArrayList<>();
        try (Connection connection = XJDBC.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String ma = rs.getString("MaSach");
                String ten = rs.getString("TenSach");
                Integer luotMuon = rs.getObject("LuotMuon", Integer.class); 
                String tenTacGia = rs.getString("TenTacGia");
                Integer namXuatBan = rs.getObject("NamXuatBan", Integer.class);

                list.add(new statistical(ma, ten, luotMuon, tenTacGia, namXuatBan));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<statistical> getTopBorrowedBooksByFilter(Integer month, Integer year) {
        return new ArrayList<>();
    }
}
