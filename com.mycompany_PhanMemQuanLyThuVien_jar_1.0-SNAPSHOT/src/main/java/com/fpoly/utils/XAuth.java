package com.fpoly.utils;

import com.fpoly.entity.User;
import java.sql.*;

public class XAuth {

    public static User currentUser = null;

    /**
     * Thực hiện đăng nhập bằng cách tìm kiếm user và mật khẩu trong CSDL.
     * @param username Tên đăng nhập.
     * @param password Mật khẩu thô (Plain Text).
     * @return true nếu đăng nhập thành công, false nếu sai thông tin.
     */
    public static boolean login(String username, String password) {
        // Query chỉ để kiểm tra đăng nhập (vì mật khẩu lưu Plain Text)
        String sql = """
             SELECT u.UserID, u.Username, u.FullName, u.Email, u.RoleID, u.Password,
                    r.RoleName
             FROM Users u
             JOIN UserRoles r ON u.RoleID = r.RoleID
             WHERE u.Username = ? AND u.Password = ?
             """;

        try {
            // Sử dụng XJDBC.query để thực hiện truy vấn
            // Giả định XJDBC.query(sql, args...) trả về ResultSet
            try (ResultSet rs = XJDBC.query(sql, username, password)) { 
                if (rs.next()) {
                    
                    int roleID = rs.getInt("RoleID");
                    String roleName = rs.getString("RoleName"); 
                    
                    // Khởi tạo đối tượng User và gán vào currentUser
                    currentUser = new User(
                            rs.getInt("UserID"),             // 1. UserID
                            rs.getString("Username"),        // 2. Username
                            rs.getString("Password"),        // 3. PASSWORD (ĐÃ SỬA LỖI GÁN NGƯỢC)
                            rs.getString("FullName"),        // 4. FULLNAME (ĐÃ SỬA LỖI GÁN NGƯỢC)
                            rs.getString("Email"),           // 5. Email
                            roleID,                          // 6. RoleID
                            roleName                         // 7. RoleName
                    );

                    return true;
                }
            }
        } catch (Exception e) {
            // In lỗi ra console nếu có vấn đề về CSDL
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Kiểm tra người dùng hiện tại có phải là Admin không.
     * @return true nếu là Admin.
     */
    public static boolean isAdmin() {
        return currentUser != null && "Admin".equalsIgnoreCase(currentUser.getRoleName());
    }

    /**
     * Kiểm tra người dùng hiện tại có phải là Thủ Thư không.
     * @return true nếu là Thủ Thư.
     */
    public static boolean isThuThu() {
        return currentUser != null && "Thủ Thư".equalsIgnoreCase(currentUser.getRoleName());
    }

    /**
     * Kiểm tra người dùng hiện tại có phải là Member không.
     * @return true nếu là Member.
     */
    public static boolean isMember() {
        return currentUser != null && "Member".equalsIgnoreCase(currentUser.getRoleName());
    }

    /**
     * Đặt currentUser về null (Đăng xuất).
     */
    public static void logout() {
        currentUser = null;
    }

    /**
     * Kiểm tra xem có người dùng nào đang đăng nhập không.
     * @return true nếu có.
     */
    public static boolean isLogin() {
        return currentUser != null;
    }
}