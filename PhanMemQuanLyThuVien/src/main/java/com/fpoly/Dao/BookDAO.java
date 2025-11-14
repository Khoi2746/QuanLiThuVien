/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.Dao;

import com.fpoly.utils.XJDBC;
import java.sql.ResultSet; // Thêm import
import java.sql.SQLException; // Thêm import
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class BookDAO {
    
    // Không cần biến "conn" hay hàm khởi tạo "BookDAO()" nữa

    public void loadBooksToTable(JTable tblBooks) {
        // Đảm bảo tên bảng và tên cột trong SQL khớp với CSDL
        String sql = "SELECT BookID, Title, Author, Category, Quantity FROM books";
        
        DefaultTableModel model = (DefaultTableModel) tblBooks.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trên bảng

        // Dùng try-with-resources để XJDBC.query() tự đóng kết nối
        try (ResultSet rs = XJDBC.query(sql)) { 
            
            if (rs == null) {
                 JOptionPane.showMessageDialog(null, "Lỗi: Không thể thực thi truy vấn.");
                 return;
            }

            while (rs.next()) {
                Object[] row = {
                    rs.getString("BookID"),
                    rs.getString("Title"),
                    rs.getString("Author"),
                    rs.getString("Category"),
                    rs.getInt("Quantity")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi chi tiết ra console để bạn xem
            JOptionPane.showMessageDialog(null, "Lỗi tải dữ liệu: " + e.getMessage());
        }
    }
}