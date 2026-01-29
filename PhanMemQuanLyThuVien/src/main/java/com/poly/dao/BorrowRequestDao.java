package com.poly.dao;

import com.fpoly.entity.BorrowRequest;
import java.util.List;

public interface BorrowRequestDao {
    
    // Thêm mới phiếu mượn
    void insert(BorrowRequest br);
    
    // Cập nhật phiếu mượn
    void update(BorrowRequest br);
    
    // Xóa phiếu mượn theo ID
    void delete(int requestID);
    
    // Lấy tất cả phiếu mượn
    List<BorrowRequest> selectAll();
    
    // Lấy phiếu mượn theo ID
    BorrowRequest selectById(int requestID);
    
    // Lấy phiếu mượn theo UserID (ví dụ để hiển thị lịch sử mượn của sinh viên)
    List<BorrowRequest> selectByUserId(int userID);
    
    // Lấy phiếu mượn theo trạng thái
    List<BorrowRequest> selectByStatus(String status);
}
