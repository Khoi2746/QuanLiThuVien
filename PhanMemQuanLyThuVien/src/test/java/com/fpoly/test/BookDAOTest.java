package com.fpoly.test;

import com.poly.dao.BookDAO;
import com.poly.DaoImpl.BookDAOImpl;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDAOTest {

    private BookDAO dao;

    @BeforeEach
    void setUp() {
        dao = new BookDAOImpl();
    }

    // TC08: Thêm sách mới hợp lệ
    @Test
    @Order(8)
    void TC08_insertValidBook() {
        assertDoesNotThrow(() ->
            dao.insertBook("Java 1", "Tác giả A", "CNTT", 5)
        );
    }

    // TC09: Thêm sách thiếu tên tác giả
    @Test
    @Order(9)
    void TC09_insertBookMissingAuthor() {
        assertDoesNotThrow(() ->
            dao.insertBook("Java 2", "", "CNTT", 5)
        );
    }

    // TC10: Thêm sách số lượng âm
    @Test
    @Order(10)
    void TC10_insertNegativeQuantity() {
        assertDoesNotThrow(() ->
            dao.insertBook("Java 3", "Tác giả B", "CNTT", -5)
        );
    }

    // TC11: Cập nhật sách theo ID
    @Test
    @Order(11)
    void TC11_updateBookById() {
        assertDoesNotThrow(() ->
            dao.updateBook("B01", "New", "Tác giả A", "CNTT", 10)
        );
    }

    // TC12: Xóa sách đang tồn tại
    @Test
    @Order(12)
    void TC12_deleteExistingBook() {
        assertDoesNotThrow(() ->
            dao.deleteBook("B01")
        );
    }

    // TC13: Xóa sách không tồn tại
    @Test
    @Order(13)
    void TC13_deleteNonExistingBook() {
        assertDoesNotThrow(() ->
            dao.deleteBook("999")
        );
    }

    // TC14: Tìm sách theo từ khóa
    @Test
    @Order(14)
    void TC14_searchBookByKeyword() {
        assertDoesNotThrow(() ->
            dao.searchBooks(new javax.swing.JTable(), "Harry", 0)
        );
    }
    
}
