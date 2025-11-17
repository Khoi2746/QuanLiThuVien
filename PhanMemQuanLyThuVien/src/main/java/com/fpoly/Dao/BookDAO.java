// File: com/fpoly/Dao/BookDAO.java (Code đã sửa hoàn chỉnh)

package com.fpoly.Dao;

import com.fpoly.utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookDAO {

    public void loadBooksToTable(JTable tblBooks) {
        
        // ✅ CÂU LỆNH SQL ĐÃ SỬA:
        // Chúng ta JOIN 3 bảng để lấy tên tác giả và tên thể loại
        String sql = """
                     SELECT 
                         b.BookID, 
                         b.Title, 
                         a.AuthorName, 
                         c.CategoryName, 
                         b.Quantity 
                     FROM Books b
                     JOIN Authors a ON b.AuthorID = a.AuthorID
                     JOIN Categories c ON b.CategoryID = c.CategoryID
                     """;
        
        DefaultTableModel model = (DefaultTableModel) tblBooks.getModel();
        model.setRowCount(0); 

        ResultSet rs = null; // Khai báo ResultSet bên ngoài

        try {
            rs = XJDBC.query(sql); // Thực thi truy vấn

            if (rs == null) {
                 JOptionPane.showMessageDialog(null, "Lỗi: Không thể thực thi truy vấn.");
                 return;
            }

            while (rs.next()) {
                Object[] row = {
                    rs.getString("BookID"),
                    rs.getString("Title"),
                    rs.getString("AuthorName"),   // ✅ Đổi tên cột
                    rs.getString("CategoryName"), // ✅ Đổi tên cột
                    rs.getInt("Quantity")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            JOptionPane.showMessageDialog(null, "Lỗi tải dữ liệu: " + e.getMessage());
        } finally {
            // ✅ Sửa lỗi rò rỉ kết nối
            XJDBC.close(rs); 
        }
    }
    // File: com/fpoly/Dao/BookDAO.java

    // ... (Hàm loadBooksToTable của bạn ở đây) ...

    /**
     * HÀM MỚI: Tìm kiếm sách theo từ khóa (tìm theo Tên Sách hoặc Tên Tác Giả)
     */
    public void searchBooks(JTable tblBooks, String keyword) {
        
        // Câu lệnh SQL JOIN 3 bảng và thêm điều kiện WHERE...LIKE
        String sql = """
                     SELECT 
                         b.BookID, 
                         b.Title, 
                         a.AuthorName, 
                         c.CategoryName, 
                         b.Quantity 
                     FROM Books b
                     JOIN Authors a ON b.AuthorID = a.AuthorID
                     JOIN Categories c ON b.CategoryID = c.CategoryID
                     WHERE b.Title LIKE ? OR a.AuthorName LIKE ?
                     """;
        
        DefaultTableModel model = (DefaultTableModel) tblBooks.getModel();
        model.setRowCount(0); 

        ResultSet rs = null;

        try {
            // Tạo từ khóa tìm kiếm (thêm % để tìm kiếm_bất_kỳ_ở_đâu)
            String searchKeyword = "%" + keyword + "%";
            
            // Thực thi truy vấn, truyền 2 tham số cho 2 dấu ?
            rs = XJDBC.query(sql, searchKeyword, searchKeyword); 

            if (rs == null) {
                 JOptionPane.showMessageDialog(null, "Lỗi: Không thể thực thi truy vấn tìm kiếm.");
                 return;
            }

            while (rs.next()) {
                Object[] row = {
                    rs.getString("BookID"),
                    rs.getString("Title"),
                    rs.getString("AuthorName"),
                    rs.getString("CategoryName"),
                    rs.getInt("Quantity")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            JOptionPane.showMessageDialog(null, "Lỗi tải dữ liệu tìm kiếm: " + e.getMessage());
        } finally {
            // Luôn đóng kết nối
            XJDBC.close(rs); 
        }
    }

}