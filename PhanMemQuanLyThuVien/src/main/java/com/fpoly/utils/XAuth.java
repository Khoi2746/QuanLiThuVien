package com.fpoly.utils;

import com.fpoly.entity.User;
import java.sql.*;

public class XAuth {

    public static User currentUser = null;

    public static boolean login(String username, String password) {
        String sql = """
             SELECT u.UserID, u.Username, u.FullName, u.Email, u.RoleID, u.Password,
                    r.RoleName
             FROM Users u
             JOIN UserRoles r ON u.RoleID = r.RoleID
             WHERE u.Username = ? AND u.Password = ?
             """;

        try {
            try (ResultSet rs = XJDBC.query(sql, username, password)) { 
                if (rs.next()) {
                    
                    int roleID = rs.getInt("RoleID");
                    String roleName = rs.getString("RoleName"); 
   
                    currentUser = new User(
                            rs.getInt("UserID"),
                            rs.getString("Username"),
                            rs.getString("FullName"),
                            rs.getString("Email"),
                            rs.getString("Password"),
                            roleID,
                            roleName 
                    );

                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean isAdmin() {
        return currentUser != null && "Admin".equalsIgnoreCase(currentUser.getRoleName());
    }

    public static boolean isThuThu() {
        return currentUser != null && "Thủ Thư".equalsIgnoreCase(currentUser.getRoleName());
    }

    public static boolean isMember() {
        return currentUser != null && "Member".equalsIgnoreCase(currentUser.getRoleName());
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean isLogin() {
        return currentUser != null;
    }
}