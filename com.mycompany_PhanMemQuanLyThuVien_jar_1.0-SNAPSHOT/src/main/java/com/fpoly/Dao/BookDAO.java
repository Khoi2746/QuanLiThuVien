package com.fpoly.Dao;
import javax.swing.JTable;

/**
 * Interface cho các thao tác CRUD và truy vấn Sách.
 */
public interface BookDAO {
    
    // Thêm sách mới
    void insertBook(String title, String authorName, String categoryName, int quantity);
    
    // Cập nhật thông tin sách
    void updateBook(String bookID, String title, String authorName, String categoryName, int quantity);
    
    // Xóa sách
    void deleteBook(String bookID);
    
    // Tải danh sách sách lên JTable (Tải tất cả)
    void loadBooksToTable(JTable tblBooks);
    
    // Tìm kiếm sách theo từ khóa (Tên sách hoặc Tác giả)
    void searchBooks(JTable tblBooks, String keyword);
    
    // BỔ SUNG PHƯƠNG THỨC LỌC THEO THỂ LOẠI
    void filterBooksByCategory(JTable tblBooks, int categoryID);

}