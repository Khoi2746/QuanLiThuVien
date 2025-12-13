/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fpoly.Dao;
import javax.swing.JTable;

/**
 *
 * @author PC
 */
public interface BookDAO {
  
/**
 * Interface cho các thao tác CRUD và truy vấn Sách.
 * Các phương thức helper sẽ được chuyển sang lớp triển khai.
 */
   
    // Thêm sách mới
    void insertBook(String title, String authorName, String categoryName, int quantity);
    
    // Cập nhật thông tin sách
    void updateBook(String bookID, String title, String authorName, String categoryName, int quantity);
    
    // Xóa sách
    void deleteBook(String bookID);
    
    // Tải danh sách sách lên JTable
    void loadBooksToTable(JTable tblBooks);
    
    // Tìm kiếm sách theo từ khóa
    // Trong com.fpoly.Dao.BookDAO
void searchBooks(JTable tblBooks, String keyword, int categoryID); // PHẢI SỬA THÀNH 3 THAM SỐ
    

}
