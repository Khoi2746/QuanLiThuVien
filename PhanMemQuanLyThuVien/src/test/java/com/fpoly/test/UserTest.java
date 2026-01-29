package com.fpoly.test;



import com.poly.dao.UserDao;
import com.fpoly.entity.User;
import com.poly.DaoImpl.UserDaoImpl;
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

    @Test @Order(1)
    @DisplayName("TC01: Kiểm tra thông tin Admin hiện có")
    void testAdminInfo() {
        User user = dao.findById(1); 
        assertNotNull(user, "Tài khoản Admin phải tồn tại");
        assertEquals("admin", user.getUsername());
    }

    @Test @Order(2)
    @DisplayName("TC02: Xác nhận Admin tồn tại (CheckUsernameExists)")
    void testAdminExists() {
        assertTrue(dao.checkUsernameExists("admin"));
    }

    @Test @Order(3)
    @DisplayName("TC03: Tạo thêm một Admin phụ")
    void testCreateSecondaryAdmin() {
        User newUser = new User(); 
        newUser.setUsername("admin_backup");
        newUser.setPassword("admin@2026");
        newUser.setFullName("Quản Trị Viên Dự Phòng");
        newUser.setEmail("backup@lib.com");
        newUser.setRoleID(1);
        dao.create(newUser);
        assertTrue(dao.checkUsernameExists("admin_backup"));
    }

    @Test @Order(4)
    @DisplayName("TC04: Cập nhật mật khẩu cho Admin phụ")
    void testUpdateAdminPassword() {
        assertTrue(dao.updatePassword("admin_backup", "new_secure_pass"));
    }

    @Test @Order(5)
    @DisplayName("TC05: Tìm kiếm Admin theo từ khóa")
    void testFindAdminByKeyword() {
        List<User> list = dao.findByUsername("admin");
        assertFalse(list.isEmpty());
    }

    @Test @Order(6)
    @DisplayName("TC06: Lấy tất cả danh sách người dùng")
    void testFindAllUsers() {
        assertNotNull(dao.findAll());
    }

    @Test @Order(7)
    @DisplayName("TC07: Tìm User bằng ID không tồn tại")
    void testFindInvalidId() {
        assertNull(dao.findById(-999));
    }

    @Test @Order(8)
    @DisplayName("TC08: Kiểm tra Username không tồn tại")
    void testUsernameNotExists() {
        assertFalse(dao.checkUsernameExists("user_fake_123"));
    }

    @Test @Order(9)
    @DisplayName("TC09: Đổi mật khẩu cho User không tồn tại")
    void testUpdatePassInvalidUser() {
        assertFalse(dao.updatePassword("non_existent_user", "pass123"));
    }

    @Test @Order(10)
    @DisplayName("TC10: Xóa Admin phụ sau khi test")
    void testCleanupAdmin() {
        List<User> list = dao.findByUsername("admin_backup");
        if (!list.isEmpty()) {
            dao.delete(list.get(0).getUserID());
            assertNull(dao.findById(list.get(0).getUserID()));
        }
    }
}
