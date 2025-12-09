package com.fpoly.Dao;

import com.fpoly.entity.Category;
import java.util.List;

/**
 * Interface định nghĩa các phương thức truy cập dữ liệu cho Entity Category.
 */
public interface CategoryDAO {
    
    /**
     * Lấy tất cả các Category có trong database.
     * @return List<Category> danh sách các thể loại.
     */
    List<Category> selectAll(); 

    // Ku em có thể thêm các phương thức CRUD cơ bản khác nếu cần:
    // void insert(Category entity);
    // void update(Category entity);
    // void delete(Integer id);
    // Category selectById(Integer id);
}