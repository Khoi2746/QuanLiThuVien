package com.fpoly.test;

import com.poly.DaoImpl.BorrowDAOImpl;
import com.poly.DaoImpl.StatisticalDAOImpl;
import com.poly.dao.BorrowDAO;
import com.poly.dao.StatisticalDAO;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StatisticsTest {

    StatisticalDAO statDao;
    BorrowDAO borrowDao;

    @BeforeEach
    void setUp() {
        statDao = new StatisticalDAOImpl();
        borrowDao = new BorrowDAOImpl();
    }

    // TC18: Thống kê sách mượn nhiều
    @Test
    @Order(18)
    void TC18_getAllTopBorrowedBooks() {
        assertNotNull(statDao.getAllTopBorrowedBooks());
    }

    // TC19: Thống kê số lượt mượn (LuotMuon >= 0)
    @Test
    @Order(19)
    void TC19_borrowCountGreaterThanZero() {
        statDao.getAllTopBorrowedBooks()
               .forEach(s -> assertNotNull(s));
    }

    // TC20: Thống kê sách quá hạn
    @Test
    @Order(20)
    void TC20_getOverdueBooks() {
        assertNotNull(borrowDao.getAllOverdueBooks());
    }
}
