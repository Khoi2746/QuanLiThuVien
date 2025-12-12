package com.fpoly.utils;

import java.sql.*;

public class XJDBC {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=LibraryDB;encrypt=false";
    private static final String USER = "sa";
    private static final String PASS = "123456";

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

    // ===============================
    // ⭐ PHẦN THÊM MỚI BÊN DƯỚI
    // ===============================

    /**
     * Lấy 1 giá trị duy nhất (COUNT, MAX, MIN, SCOPE_IDENTITY, ...)
     */
    public static Object executeScalar(String sql, Object... args) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getObject(1);
            }
            return null;
        }
    }

    /**
     * Chuẩn bị PreparedStatement (phục vụ các xử lý đặc biệt)
     */
    public static PreparedStatement prepareStatement(String sql, Object... args) throws SQLException {
        Connection con = getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }

    /**
     * Transaction: chạy nhiều SQL trong 1 lần commit/rollback
     */
    public static void executeTransaction(SQLConsumer<Connection> action) {
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);

            action.accept(con);

            con.commit();
        } catch (Exception e) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Transaction failed!", e);
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Functional interface để truyền vào transaction
     */
    @FunctionalInterface
    public interface SQLConsumer<T> {
        void accept(T t) throws Exception;
    }
}