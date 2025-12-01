package com.poly.DaoImpl;

import com.fpoly.Dao.BorrowDAO;
// Giả định lớp com.fpoly.entity.Borrow đã được cập nhật hoặc tạo để chứa đủ thông tin
// Nếu lớp Borrow cũ chỉ có các cột cũ, ku em cần cập nhật lớp entity đó hoặc tạo lớp DTO mới.
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
    
    // Lưu ý: Class Borrow entity cần phải chứa các trường:
    // BorrowID, UserID, FullName, Title (TenSach), DueDate (NgayTra), DaysLate (SoNgayTre)
    
    @Override
    public List<Borrow> getAllOverdueBooks() {
        
        // SỬA SQL: Dùng bảng Borrow, Users, Books và tên cột mới (BorrowID, UserID, DueDate, Title)
        String sql = """
            SELECT 
                b.BorrowID, u.UserID, u.FullName, s.Title, b.DueDate, 
                DATEDIFF(day, b.DueDate, GETDATE()) AS DaysLate
            FROM Borrow b
            JOIN Users u ON b.UserID = u.UserID
            JOIN Books s ON b.BookID = s.BookID
            WHERE 
                b.Status = 'Borrowed' -- Đảm bảo sách chưa được trả
                AND b.DueDate < GETDATE() -- Ngày dự kiến trả đã qua
            ORDER BY b.DueDate ASC
        """;
            
        List<Borrow> list = new ArrayList<>();
        // Sử dụng XJDBC.query để truy vấn dữ liệu 
        try (ResultSet rs = XJDBC.query(sql)) { 
            while (rs.next()) {
                // Ánh xạ các cột mới vào đối tượng Borrow (giả định entity Borrow đã được cập nhật)
                Integer borrowID = rs.getInt("BorrowID");
                Integer userID = rs.getInt("UserID");
                String tenSV = rs.getString("FullName");
                String tenSach = rs.getString("Title");
                String ngayTra = rs.getString("DueDate"); // Ngày dự kiến trả
                Integer soNgayTre = rs.getInt("DaysLate");  

                // Lưu ý: Constructor của lớp Borrow entity cần được cập nhật
                // để chấp nhận các kiểu dữ liệu và số lượng tham số mới này.
                // Tôi dùng tạm String cho NgayTra và SoNgayTre theo cấu trúc cũ của em,
                // nhưng khuyến nghị dùng java.util.Date/LocalDate và Integer.
                list.add(new Borrow(
                    String.valueOf(borrowID), 
                    String.valueOf(userID), 
                    tenSV, 
                    tenSach, 
                    ngayTra, 
                    String.valueOf(soNgayTre)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Borrow> getOverdueBooksByFilter(Integer month, Integer year) {      
        // Cần triển khai phương thức này dựa trên cấu trúc SQL đã sửa ở trên
        return new ArrayList<>();
    }
}