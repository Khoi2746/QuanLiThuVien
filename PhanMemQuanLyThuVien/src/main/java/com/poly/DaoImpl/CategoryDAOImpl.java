package com.poly.DaoImpl;

import com.poly.dao.CategoryDAO;
import com.fpoly.entity.Category;
import com.fpoly.utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    
    // Lấy tất cả thể loại
    private static final String SELECT_ALL_SQL =
        "SELECT CategoryID, CategoryName FROM Categories ORDER BY CategoryName";

    // Thêm thể loại mới
    private static final String INSERT_SQL =
        "INSERT INTO Categories (CategoryName) VALUES (?)";

    /**
     * Helper: Thực thi truy vấn SQL
     */
    private List<Category> selectBySql(String sql, Object... args) {
        List<Category> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = XJDBC.query(sql, args);
            while (rs.next()) {
                Category cat = new Category(
                    rs.getInt("CategoryID"),
                    rs.getString("CategoryName")
                );
                list.add(cat);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy vấn Category", e);
        } finally {
            XJDBC.close(rs);
        }
        return list;
    }

    @Override
    public List<Category> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    // ===============================
    // 👉 INSERT CATEGORY
    // ===============================
    @Override
    public void insert(Category entity) {
        try {
            XJDBC.update(INSERT_SQL, entity.getCategoryName());
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thêm thể loại!", e);
        }
    }
}
