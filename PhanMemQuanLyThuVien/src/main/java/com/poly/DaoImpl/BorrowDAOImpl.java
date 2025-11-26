/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.DaoImpl;

import com.fpoly.Dao.BorrowDAO;
import com.fpoly.entity.Borrow;
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
public class BorrowDAOImpl implements BorrowDAO{
    @Override
public List<Borrow> getAllOverdueBooks() {
    String sql = "SELECT MaPhieu, MaSV, TenSV, TenSach, NgayTra, " +
                 "DATEDIFF(day, NgayTra, GETDATE()) AS SoNgayTre " +
                 "FROM Borrow " +              
                 "WHERE NgayTra < GETDATE()";
           

    List<Borrow> list = new ArrayList<>();
    try (Connection connection = XJDBC.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String maPhieu = rs.getString("MaPhieu");
            String maSV = rs.getString("MaSV");
            String tenSV = rs.getString("TenSV");
            String tenSach = rs.getString("TenSach");
            String ngayTra = rs.getString("NgayTra");  // Đây là ngày dự kiến trả
            String soNgayTre = rs.getString("SoNgayTre"); 

            list.add(new Borrow(maPhieu, maSV, tenSV, tenSach, ngayTra, soNgayTre));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
    }

    @Override
    public List<Borrow> getOverdueBooksByFilter(Integer month, Integer year) {     
        return new ArrayList<>();
    }
}
