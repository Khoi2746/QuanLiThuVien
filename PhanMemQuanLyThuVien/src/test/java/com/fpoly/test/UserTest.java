package com.fpoly.test;

import com.poly.dao.UserDao;
import com.poly.DaoImpl.UserDaoImpl;
import com.fpoly.entity.User;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {

    private UserDao dao;

    @BeforeEach
    void setUp() {
        dao = new UserDaoImpl();
    }

    // TC01: Tìm User theo ID tồn tại
    @Test
    @Order(1)
    void TC01_findUserByValidId() {
        User user = dao.findById(1);
        assertNotNull(user);
    }

    // TC02: Tìm User theo ID không tồn tại
    @Test
    @Order(2)
    void TC02_findUserByInvalidId() {
        User user = dao.findById(-1);
        assertNull(user);
    }

    // TC03: Kiểm tra Username đã tồn tại
    @Test
    @Order(3)
    void TC03_checkUsernameExists() {
        assertTrue(dao.checkUsernameExists("ad"));
    }

    // TC04: Kiểm tra Username chưa tồn tại
    @Test
    @Order(4)
    void TC04_checkUsernameNotExists() {
        assertFalse(dao.checkUsernameExists("newbie"));
    }

    // TC05: Cập nhật mật khẩu thành công
    @Test
    @Order(5)
    void TC05_updatePasswordSuccess() {
        assertTrue(dao.updatePassword("admin", "123"));
    }

    // TC06: Tìm danh sách User theo tên
    @Test
    @Order(6)
    void TC06_findUserByKeyword() {
        List<User> list = dao.findByUsername("a");
        assertFalse(list.isEmpty());
    }

    // TC07: Lấy tất cả người dùng
    @Test
    @Order(7)
    void TC07_findAllUsers() {
        List<User> list = dao.findAll();
        assertTrue(list.size() > 0);
    }
}
