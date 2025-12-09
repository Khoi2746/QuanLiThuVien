package com.fpoly.entity;



/**
 * Entity đại diện cho bảng Categories trong cơ sở dữ liệu.
 */
public class Category {
    private int categoryID;
    private String categoryName;

    // 1. Constructor mặc định (cần thiết cho một số thao tác JDBC)
    public Category() {
    }

    // 2. Constructor đầy đủ
    public Category(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    // 3. Constructor chỉ có tên (khi thêm mới category)
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    // 4. Getters và Setters
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // 5. Ghi đè toString()
    // Phương thức này RẤT QUAN TRỌNG khi sử dụng Category trong JComboBox.
    // JComboBox sẽ gọi toString() để hiển thị tên thể loại cho người dùng.
    @Override
    public String toString() {
        return categoryName;
    }
}