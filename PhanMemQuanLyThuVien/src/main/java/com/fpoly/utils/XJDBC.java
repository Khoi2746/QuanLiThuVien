/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.utils;

/**
 *
 * @author X1 Carbon
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XJDBC {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=LibraryDB; encrypt=false";
    private static String user = "sa";
    private static String pass = "123456";
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Thực thi SELECT
    public static ResultSet query(String sql, Object... args) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement stmt = con.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt.executeQuery();
    }

    // Thực thi INSERT, UPDATE, DELETE
    public static int update(String sql, Object... args) throws SQLException {
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
            return stmt.executeUpdate();
        }
    }

    // Đóng ResultSet và Connection (dùng cho SELECT)
    public static void close(ResultSet rs) {
        try {
            if (rs != null) rs.getStatement().getConnection().close();
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, user, pass);
}

}
