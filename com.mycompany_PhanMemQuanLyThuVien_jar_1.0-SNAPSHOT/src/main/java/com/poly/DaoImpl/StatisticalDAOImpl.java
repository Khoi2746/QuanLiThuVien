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
        
        // SỬA SQL: JOIN Books, Authors, và Borrow để tính LuotMuon
        String sql = """
            SELECT 
                b.BookID AS MaSach, 
                b.Title AS TenSach, 
                COUNT(br.BorrowID) AS LuotMuon, -- Tính tổng lượt mượn từ bảng Borrow
                a.AuthorName AS TenTacGia,
                b.PublishedYear AS NamXuatBan
            FROM Books b
            JOIN Authors a ON b.AuthorID = a.AuthorID
            LEFT JOIN Borrow br ON b.BookID = br.BookID
            GROUP BY 
                b.BookID, b.Title, a.AuthorName, b.PublishedYear
            ORDER BY 
                LuotMuon DESC;
        """;
            
        List<statistical> list = new ArrayList<>();
        // Sử dụng XJDBC.query để truy vấn dữ liệu
        try (ResultSet rs = XJDBC.query(sql)) { 

            while (rs.next()) {
                // Ánh xạ các cột đã được alias (đặt tên lại) trong SQL
                String ma = rs.getString("MaSach"); // Là BookID
                String ten = rs.getString("TenSach"); // Là Title
                Integer luotMuon = rs.getObject("LuotMuon", Integer.class); // Là COUNT(br.BorrowID)
                String tenTacGia = rs.getString("TenTacGia"); // Là AuthorName
                Integer namXuatBan = rs.getObject("NamXuatBan", Integer.class); // Là PublishedYear

                list.add(new statistical(ma, ten, luotMuon, tenTacGia, namXuatBan));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public List<statistical> getTopBorrowedBooksByFilter(Integer month, Integer year) {
        // Ku em sẽ cần sửa phương thức này tương tự, thêm điều kiện WHERE MONTH(br.BorrowDate) = ? AND YEAR(br.BorrowDate) = ?
        return new ArrayList<>();
    }
}