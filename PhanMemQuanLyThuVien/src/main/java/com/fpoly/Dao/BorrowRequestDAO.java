package com.fpoly.Dao;

import com.fpoly.entity.BorrowRequest;
import java.util.List;

public interface BorrowRequestDAO {
    void insert(BorrowRequest request);
    List<BorrowRequest> selectAll();
    BorrowRequest selectById(int id);
}
