/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.dao;
import com.fpoly.entity.statistical;
import java.util.List;
/**
 *
 * @author LENOVO
 */
public interface StatisticalDAO {
    List<statistical> getAllTopBorrowedBooks();
    List<statistical> getTopBorrowedBooksByFilter(Integer month, Integer year);
    List<statistical> searchTopBorrowedByBookName(String keyword);
}
