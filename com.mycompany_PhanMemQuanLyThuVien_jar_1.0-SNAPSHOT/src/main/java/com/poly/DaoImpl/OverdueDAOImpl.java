package com.poly.DaoImpl;

import com.fpoly.Dao.OverdueDAO;
import com.fpoly.utils.XJDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OverdueDAOImpl implements OverdueDAO {

    @Override
    public List<Object[]> getOverdueList(String keyword) throws SQLException {

        List<Object[]> list = new ArrayList<>();

        String sql = """
            SELECT b.BorrowID, u.UserID, u.FullName, s.BookID, s.Title, b.DueDate
            FROM Borrow b
            JOIN Users u ON b.UserID = u.UserID
            JOIN Books s ON b.BookID = s.BookID
            WHERE b.DueDate < GETDATE() AND b.Status = 'Borrowed'
              AND (u.UserID LIKE ? OR u.FullName LIKE ? OR s.BookID LIKE ? OR s.Title LIKE ?)
            ORDER BY b.DueDate ASC
        """;
        // Bổ sung thêm b.Status = 'Borrowed' để chỉ lấy các phiếu chưa trả và quá hạn.

        ResultSet rs = null;

        try {
            rs = XJDBC.query(sql,
                    "%" + keyword + "%", "%" + keyword + "%", // UserID and FullName
                    "%" + keyword + "%", "%" + keyword + "%" // BookID and Title
            );

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("BorrowID"),
                    rs.getInt("UserID"), // ĐÃ SỬA: Đổi StudentID thành UserID
                    rs.getString("FullName"),
                    rs.getInt("BookID"), // Dùng getInt nếu BookID là INT
                    rs.getString("Title"),
                    rs.getDate("DueDate")
                };
                list.add(row);
            }

        } finally {
            XJDBC.close(rs);
        }

        return list;
    }

    @Override
    public void insertNotification(int borrowID, int userID) throws SQLException {
        String sql = "INSERT INTO OverdueNotifications (BorrowID, UserID) VALUES (?, ?)";
        XJDBC.update(sql, borrowID, userID);
    }
}