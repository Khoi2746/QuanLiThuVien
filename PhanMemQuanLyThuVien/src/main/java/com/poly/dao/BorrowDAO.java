/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.dao;
import com.fpoly.entity.Borrow;
import java.util.List;
/**
 *
 * @author LENOVO
 */
public interface BorrowDAO {
    List<Borrow> getAllOverdueBooks();
    List<Borrow> getOverdueBooksByFilter(Integer month, Integer year);
    List<Borrow> searchOverdueByBookName(String keyword);
}
