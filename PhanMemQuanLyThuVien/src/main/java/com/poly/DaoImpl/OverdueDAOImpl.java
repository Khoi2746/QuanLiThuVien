package com.poly.DaoImpl;

import com.fpoly.Dao.OverdueDAO;
import com.fpoly.utils.XJDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OverdueDAOImpl implements OverdueDAO {

    // Trong OverdueDAOImpl.java
// Trong OverdueDAOImpl.java

@Override
public List<Object[]> getOverdueList(String keyword) throws SQLException {

    List<Object[]> list = new ArrayList<>();

    // ********* CHỈNH SỬA CÂU LỆNH SQL *********
    // Chỉ giữ lại điều kiện tìm kiếm theo Mã Sách (s.BookID LIKE ?)
    String sql = """
        SELECT b.BorrowID, u.UserID, u.FullName, s.BookID, s.Title, b.DueDate
        FROM Borrow b
        JOIN Users u ON b.UserID = u.UserID
        JOIN Books s ON b.BookID = s.BookID
        WHERE b.DueDate < GETDATE() AND b.Status = 'Borrowed'
         AND (s.BookID LIKE ?)
        ORDER BY b.DueDate ASC
    """;
    // *****************************************

    ResultSet rs = null;

    try {
        // ********* CHỈNH SỬA DANH SÁCH THAM SỐ *********
        // Chỉ truyền duy nhất 1 tham số cho Mã Sách
        rs = XJDBC.query(sql,
                "%" + keyword + "%" // Tham số cho Mã Sách (BookID)
        );
        // *****************************************

        while (rs.next()) {
            Object[] row = {
                rs.getInt("BorrowID"),
                rs.getInt("UserID"),
                rs.getString("FullName"),
                rs.getInt("BookID"),
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