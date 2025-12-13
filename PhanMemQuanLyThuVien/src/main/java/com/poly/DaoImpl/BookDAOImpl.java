package com.poly.DaoImpl; // Đổi package thành com.poly.DaoImpl

import com.fpoly.Dao.BookDAO; // Import Interface
import com.fpoly.utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// Lớp triển khai Interface BookDAO
public class BookDAOImpl implements BookDAO {

    /**
     * Helper: Lấy AuthorID từ tên Tác giả
     */
    private int getAuthorID(String authorName) throws SQLException {
        String sql = "SELECT AuthorID FROM Authors WHERE AuthorName = ?";
        try (ResultSet rs = XJDBC.query(sql, authorName)) {
            if (rs.next()) {
                return rs.getInt("AuthorID");
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

    /**
     * Helper: Lấy CategoryID từ tên Thể loại
     */
    private int getCategoryID(String categoryName) throws SQLException {
        String sql = "SELECT CategoryID FROM Categories WHERE CategoryName = ?";
        try (ResultSet rs = XJDBC.query(sql, categoryName)) {
            if (rs.next()) {
                return rs.getInt("CategoryID");
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

    /**
     * THÊM SÁCH MỚI
     */
    @Override
    public void insertBook(String title, String authorName, String categoryName, int quantity) {
        try {
            int authorID = getAuthorID(authorName);
            if (authorID == -1) {
                JOptionPane.showMessageDialog(null, "Lỗi: Không tìm thấy Tác giả '" + authorName + "'. Vui lòng thêm Tác giả trước.");
                return;
            }

            int categoryID = getCategoryID(categoryName);
            if (categoryID == -1) {
                JOptionPane.showMessageDialog(null, "Lỗi: Không tìm thấy Thể loại '" + categoryName + "'. Vui lòng thêm Thể loại trước.");
                return;
            }

            String sql = "INSERT INTO Books (Title, AuthorID, CategoryID, Quantity) VALUES (?, ?, ?, ?)";
            if (XJDBC.update(sql, title, authorID, categoryID, quantity) > 0) {
                JOptionPane.showMessageDialog(null, "Thêm sách thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm sách: " + e.getMessage());
        }
    }

    /**
     * CẬP NHẬT THÔNG TIN SÁCH
     */
    @Override
    public void updateBook(String bookID, String title, String authorName, String categoryName, int quantity) {
        try {
            int authorID = getAuthorID(authorName);
            if (authorID == -1) {
                JOptionPane.showMessageDialog(null, "Lỗi: Không tìm thấy Tác giả '" + authorName + "'. Cập nhật thất bại.");
                return;
            }

            int categoryID = getCategoryID(categoryName);
            if (categoryID == -1) {
                JOptionPane.showMessageDialog(null, "Lỗi: Không tìm thấy Thể loại '" + categoryName + "'. Cập nhật thất bại.");
                return;
            }

            String sql = "UPDATE Books SET Title=?, AuthorID=?, CategoryID=?, Quantity=? WHERE BookID=?";
            if (XJDBC.update(sql, title, authorID, categoryID, quantity, bookID) > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật sách thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật thất bại. (Có thể Mã sách không tồn tại)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật sách: " + e.getMessage());
        }
    }

    /**
     * XÓA SÁCH
     */
    @Override
    public void deleteBook(String bookID) {
        try {
            String sql = "DELETE FROM Books WHERE BookID=?";
            if (XJDBC.update(sql, bookID) > 0) {
                JOptionPane.showMessageDialog(null, "Xóa sách thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại. Mã sách không tồn tại.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi xóa sách: " + e.getMessage());
        }
    }

    @Override
    public void loadBooksToTable(JTable tblBooks) {
        
        // CÂU LỆNH SQL:
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

        ResultSet rs = null; 

        try {
            rs = XJDBC.query(sql); 

            if (rs == null) {
                 JOptionPane.showMessageDialog(null, "Lỗi: Không thể thực thi truy vấn.");
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
            JOptionPane.showMessageDialog(null, "Lỗi tải dữ liệu: " + e.getMessage());
        } finally {
            XJDBC.close(rs); 
        }
    }
@Override
public void searchBooks(JTable tblBooks, String keyword, int categoryID) { // Cần 3 tham số này!

    // CÂU LỆNH SQL:
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
                 WHERE (b.BookID LIKE ? OR b.Title LIKE ? OR a.AuthorName LIKE ?)
                 AND (b.CategoryID = ? OR ? = 0)
                 """;

    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblBooks.getModel();
    model.setRowCount(0);

    java.sql.ResultSet rs = null;
    try {
        String searchKeyword = "%" + keyword + "%";

        // Thực thi truy vấn với 5 tham số:
        rs = com.fpoly.utils.XJDBC.query(sql, 
                searchKeyword, 
                searchKeyword, 
                searchKeyword, 
                categoryID, 
                categoryID
        );
        
        // Logic đổ dữ liệu vào bảng
        if (rs != null) {
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
        }
    } catch (java.sql.SQLException e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(null, "Lỗi tải dữ liệu tìm kiếm: " + e.getMessage());
    } finally {
        // Đóng ResultSet
        com.fpoly.utils.XJDBC.close(rs); 
    }
}


}