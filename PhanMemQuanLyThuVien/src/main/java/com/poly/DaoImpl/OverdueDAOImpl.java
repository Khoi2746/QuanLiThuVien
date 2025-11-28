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
            SELECT b.BorrowID, sv.StudentID, sv.FullName, s.BookID, s.Title, b.DueDate
            FROM Borrow b
            JOIN Students sv ON b.StudentID = sv.StudentID
            JOIN Books s ON b.BookID = s.BookID
            WHERE b.DueDate < GETDATE()
              AND (sv.StudentID LIKE ? OR sv.FullName LIKE ? OR s.BookID LIKE ? OR s.Title LIKE ?)
            ORDER BY b.DueDate ASC
        """;

        ResultSet rs = null;

        try {
            rs = XJDBC.query(sql,
                    "%" + keyword + "%", "%" + keyword + "%",
                    "%" + keyword + "%", "%" + keyword + "%"
            );

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("BorrowID"),
                    rs.getInt("StudentID"),
                    rs.getString("FullName"),
                    rs.getString("BookID"),
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
