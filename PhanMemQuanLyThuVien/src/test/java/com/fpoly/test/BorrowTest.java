package com.fpoly.test;

import com.poly.dao.BorrowDAO;
import com.poly.DaoImpl.BorrowDAOImpl;
import com.fpoly.entity.Borrow;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BorrowTest {

    BorrowDAO dao;

    @BeforeEach
    void setUp() {
        dao = new BorrowDAOImpl();
    }

    // TC15: Thêm phiếu mượn (chưa hỗ trợ)
    @Test
    @Order(15)
    void TC15_insertBorrow_NotSupported() {
        assertThrows(UnsupportedOperationException.class, () ->
            dao.insertBorrow(1, 1)
        );
    }

    // TC16: Mượn sách khi hết số lượng
    @Test
    @Order(16)
    void TC16_borrowOutOfStock_NotSupported() {
        assertThrows(UnsupportedOperationException.class, () ->
            dao.insertBorrow(1, 999)
        );
    }

    // TC17: Trả sách đúng hạn
    @Test
    @Order(17)
    void TC17_returnBook_NotSupported() {
        assertThrows(UnsupportedOperationException.class, () ->
            dao.returnBook(1)
        );
    }
}

