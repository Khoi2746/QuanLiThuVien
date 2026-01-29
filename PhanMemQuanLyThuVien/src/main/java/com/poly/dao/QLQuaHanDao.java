package com.poly.dao;

import com.fpoly.entity.QLQuaHan;
import java.util.List;

public interface QLQuaHanDao {
    List<QLQuaHan> getAll();
    void insert(QLQuaHan q);
    void update(QLQuaHan q);
    void delete(String maPhieu);
    void guiThongBao(String maPhieu);
    void guiThongBaoVaLuuLichSu(String maPhieu); // chỉ cần maPhieu
}
