/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.test;


import com.poly.DaoImpl.BookDAOImpl;
import com.poly.dao.BookDAO;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDAOTest {
    private BookDAO dao;

    @BeforeEach
    void setUp() {
        dao = new BookDAOImpl();
    }

    @Test @Order(11)
    @DisplayName("TC11: Thêm sách mới thành công")
    void testInsertBookSuccess() {
        // Lưu ý: Đổi "Tác giả thật", "Thể loại thật" theo dữ liệu SQL của bạn để tránh JOptionPane
        assertDoesNotThrow(() -> dao.insertBook("Java Testing", "J.K. Rowling", "Văn học", 10));
    }

    @Test @Order(12)
    @DisplayName("TC12: Thêm sách với số lượng âm")
    void testInsertNegativeQuantity() {
        assertDoesNotThrow(() -> dao.insertBook("Negative Book", "J.K. Rowling", "Văn học", -1));
    }

    @Test @Order(13)
    @DisplayName("TC13: Cập nhật thông tin sách")
    void testUpdateBook() {
        assertDoesNotThrow(() -> dao.updateBook("1", "Updated Title", "J.K. Rowling", "Văn học", 15));
    }

    @Test @Order(14)
    @DisplayName("TC14: Xóa sách không tồn tại")
    void testDeleteInvalidBook() {
        assertDoesNotThrow(() -> dao.deleteBook("999999"));
    }

    @Test @Order(15)
    @DisplayName("TC15: Thêm sách với Tác giả không tồn tại")
    void testInsertInvalidAuthor() {
        assertDoesNotThrow(() -> dao.insertBook("Fail Book", "Unknown Author", "Văn học", 5));
    }

    @Test @Order(16)
    @DisplayName("TC16: Thêm sách với Thể loại không tồn tại")
    void testInsertInvalidCategory() {
        assertDoesNotThrow(() -> dao.insertBook("Fail Book 2", "J.K. Rowling", "Unknown Category", 5));
    }

    @Test @Order(17)
    @DisplayName("TC17: Cập nhật sách với mã ID trống")
    void testUpdateEmptyId() {
        assertDoesNotThrow(() -> dao.updateBook("", "Title", "J.K. Rowling", "Văn học", 10));
    }

    @Test @Order(18)
    @DisplayName("TC18: Xóa sách với mã ID trống")
    void testDeleteEmptyId() {
        assertDoesNotThrow(() -> dao.deleteBook(""));
    }

    @Test @Order(19)
    @DisplayName("TC19: Kiểm tra tải dữ liệu lên Table")
    void testLoadTable() {
        assertDoesNotThrow(() -> dao.loadBooksToTable(new javax.swing.JTable()));
    }

    @Test @Order(20)
    @DisplayName("TC20: Kiểm tra chức năng tìm kiếm sách")
    void testSearchBook() {
        assertDoesNotThrow(() -> dao.searchBooks(new javax.swing.JTable(), "Java", 0));
    }
}