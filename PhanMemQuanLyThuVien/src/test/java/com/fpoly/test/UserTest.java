package com.fpoly.test;

import com.poly.DaoImpl.UserDaoImpl;
import com.fpoly.dao.UserDao;
import com.fpoly.entity.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit Test tập trung vào tài khoản Admin - Assignment SOF3041
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {

    private UserDao dao;

    @BeforeEach
    void setUp() {
        dao = new UserDaoImpl();
    }

    @Test
    @Order(1)
    @DisplayName("TC1: Kiểm tra thông tin Admin hiện có")
    void testAdminInfo() {
        // Lấy admin từ database (ID = 1 theo hình image_8b0ed9.png)
        User user = dao.findById(1); 
        assertNotNull(user, "Tài khoản Admin phải tồn tại");
        assertEquals("admin", user.getUsername(), "Username phải là admin");
        // Khớp mật khẩu 1234567 từ database thực tế
        assertEquals("1234567", user.getPassword(), "Mật khẩu Admin phải khớp với DB");
    }

    @Test
    @Order(2)
    @DisplayName("TC18: Xác nhận Admin tồn tại (CheckUsernameExists)")
    void testAdminExists() {
        boolean exists = dao.checkUsernameExists("admin");
        assertTrue(exists, "Hệ thống phải tìm thấy username 'admin'");
    }

    @Test
    @Order(3)
    @DisplayName("TC15: Tạo thêm một Admin phụ (Sửa lỗi NULL Username)")
    void testCreateSecondaryAdmin() {
        // Lưu ý: Ku em phải đảm bảo các trường không được để trống
        User newUser = new User(); 
        newUser.setUsername("admin_backup"); // Gán username rõ ràng để tránh lỗi NULL
        newUser.setPassword("admin@2026");
        newUser.setFullName("Quản Trị Viên Dự Phòng");
        newUser.setEmail("backup@lib.com");
        newUser.setRoleID(1); // Quyền quản trị

        dao.create(newUser);
        
        boolean exists = dao.checkUsernameExists("admin_backup");
        assertTrue(exists, "Phải thêm được Admin phụ vào database");
    }

    @Test
    @Order(4)
    @DisplayName("TC19: Cập nhật mật khẩu cho Admin phụ")
    void testUpdateAdminPassword() {
        // Cập nhật pass mới cho tài khoản vừa tạo ở trên
        boolean success = dao.updatePassword("admin_backup", "new_secure_pass");
        assertTrue(success, "Cập nhật mật khẩu cho Admin phụ phải thành công");
    }

    @Test
    @Order(5)
    @DisplayName("TC20: Xóa Admin phụ sau khi test xong")
    void testCleanupAdmin() {
        // Tìm ID của admin_backup để xóa
        List<User> list = dao.findByUsername("admin_backup");
        if (!list.isEmpty()) {
            dao.delete(list.get(0).getUserID());
            
            User deleted = dao.findById(list.get(0).getUserID());
            assertNull(deleted, "Admin phụ phải bị xóa hoàn toàn khỏi DB");
        }
    }

    @Test
    @DisplayName("TC4: Kiểm tra tìm kiếm Admin theo từ khóa")
    void testFindAdminByKeyword() {
        List<User> list = dao.findByUsername("admin");
        assertFalse(list.isEmpty(), "Phải tìm thấy ít nhất 1 kết quả chứa từ 'admin'");
    }
}