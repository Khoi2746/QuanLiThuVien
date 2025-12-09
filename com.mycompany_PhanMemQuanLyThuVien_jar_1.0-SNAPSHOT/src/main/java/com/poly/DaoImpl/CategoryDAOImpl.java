package com.poly.DaoImpl;

import com.fpoly.Dao.CategoryDAO;
import com.fpoly.entity.Category; // Thay thế com.fpoly.model.Category bằng com.fpoly.entity.Category theo code mới nhất của ku em
import com.fpoly.utils.XJDBC; // Lớp hỗ trợ JDBC
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    
    // Câu lệnh SQL để lấy tất cả Thể loại
    private static final String SELECT_ALL_SQL = 
        "SELECT CategoryID, CategoryName FROM Categories ORDER BY CategoryName";
    
    /**
     * Helper: Thực thi truy vấn SQL và ánh xạ kết quả sang List<Category>
     */
    private List<Category> selectBySql(String sql, Object... args) {
        List<Category> list = new ArrayList<>();
        ResultSet rs = null; 
        try {
            // Sử dụng XJDBC để thực thi truy vấn
            rs = XJDBC.query(sql, args);
            
            while (rs.next()) {
                // Tạo đối tượng Category từ dữ liệu trong ResultSet
                Category cat = new Category(
                    rs.getInt("CategoryID"),
                    rs.getString("CategoryName")
                );
                list.add(cat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Ném RuntimeException để Form xử lý và hiển thị thông báo lỗi
            throw new RuntimeException("Lỗi truy vấn database Category: " + e.getMessage(), e);
        } finally {
            XJDBC.close(rs); // Đảm bảo đóng ResultSet và Statement
        }
        return list;
    }

    /**
     * Triển khai phương thức từ CategoryDAO Interface.
     * Lấy tất cả các Category có trong database.
     * @return List<Category> danh sách các thể loại.
     */
    @Override
    public List<Category> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }
}