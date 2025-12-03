/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.DaoImpl;

import com.fpoly.Dao.QLQuaHanDao;
import com.fpoly.entity.QLQuaHan;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hạ
 */
public class QLQuaHanDAOImpl implements QLQuaHanDao {

    private final List<QLQuaHan> data = new ArrayList<>();

    public QLQuaHanDAOImpl() {
        // Dữ liệu mẫu
        data.add(new QLQuaHan("PH001", "Nguyen Van A", "Java Cơ Bản", "2025-12-01", "Quá hạn", "Liên hệ KH"));
        data.add(new QLQuaHan("PH002", "Tran Thi B", "Cấu Trúc Dữ Liệu", "2025-11-28", "Quá hạn", ""));
        data.add(new QLQuaHan("PH003", "Ho Nhat Huy", "Lập trình C", "2025-11-20", "Quá hạn", "Thông báo KH"));
        data.add(new QLQuaHan("PH004", "Tran Thi Hoa", "Kết nối dữ liệu Database", "2025-12-28", "Quá hạn", ""));
    }

    @Override
    public List<QLQuaHan> getAll() {
        return data;
    }

    @Override
    public void insert(QLQuaHan q) {
        data.add(q);
    }

    @Override
    public void update(QLQuaHan q) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getMaPhieu().equals(q.getMaPhieu())) {
                data.set(i, q);
                return;
            }
        }
    }

    @Override
    public void delete(String maPhieu) {
        data.removeIf(q -> q.getMaPhieu().equals(maPhieu));
    }
}