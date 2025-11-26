/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.Dao;

import com.fpoly.utils.XJDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OverdueDAO {

    public List<Object[]> getOverdueList(String keyword) throws SQLException {

        String sql = """
            SELECT 
                b.BorrowID,
                u.UserID,
                u.FullName,
                bo.BookID,
                bo.Title,
                b.DueDate
            FROM Borrow b
            JOIN Users u ON b.UserID = u.UserID
            JOIN Books bo ON b.BookID = bo.BookID
            WHERE b.Status = 'Borrowed'
              AND b.DueDate < GETDATE()
              AND (
                    u.FullName LIKE ? 
                    OR bo.Title LIKE ?
                    OR CAST(u.UserID AS NVARCHAR(50)) LIKE ?
                  )
            ORDER BY b.DueDate ASC
            """;

        String kw = "%" + keyword + "%";

        ResultSet rs = XJDBC.query(sql, kw, kw, kw);

        List<Object[]> list = new ArrayList<>();
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
        rs.getStatement().getConnection().close();
        return list;
    }
}




