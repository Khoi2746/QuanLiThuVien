package com.fpoly.utils;

import com.fpoly.entity.User;
import java.sql.*;

public class XAuth {

    public static User currentUser = null; // Lưu người dùng đang đăng nhập

    // Đăng nhập
    public static boolean login(String username, String password) {
        String sql = "SELECT * FROM Users WHERE Username=? AND Password=?";
        try (ResultSet rs = XJDBC.query(sql, username, password)) {
            if (rs.next()) {
                currentUser = new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Role")
                );
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra role
    public static boolean isAdmin() {
        return currentUser != null && "Admin".equals(currentUser.getRole());
    }

    public static boolean isThuThu() {
        return currentUser != null && "Thủ Thư".equals(currentUser.getRole());
    }

    public static boolean isMember() {
        return currentUser != null && "Member".equals(currentUser.getRole());
    }

    // Đăng xuất
    public static void logout() {
        currentUser = null;
    }
}
