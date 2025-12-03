package com.fpoly.utils;

import java.sql.*;

public class XJDBC {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=LibraryDB;encrypt=false";
    private static final String USER = "sa";
    private static final String PASS = "GUN205060";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không load được driver SQL Server!", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Kết nối SQL Server thất bại!", e);
        }
    }

    public static ResultSet query(String sql, Object... args) throws SQLException {
        Connection con = getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt.executeQuery();
    }

    public static int executeUpdate(String sql, Object... args) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
            return stmt.executeUpdate();
        }
    }

    public static int update(String sql, Object... args) throws SQLException {
        return executeUpdate(sql, args);
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                Statement stmt = rs.getStatement();
                Connection con = stmt.getConnection();
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
