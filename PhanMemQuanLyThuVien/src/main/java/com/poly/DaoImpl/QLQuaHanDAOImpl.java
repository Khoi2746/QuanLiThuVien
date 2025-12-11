package com.poly.DaoImpl;

import com.fpoly.Dao.QLQuaHanDao;
import com.fpoly.entity.QLQuaHan;
import com.fpoly.utils.XJDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QLQuaHanDAOImpl implements QLQuaHanDao {

    @Override
    public List<QLQuaHan> getAll() {
        String sql = "SELECT * FROM QLQuaHan";
        List<QLQuaHan> list = new ArrayList<>();

        try {
            ResultSet rs = XJDBC.query(sql);
            while (rs.next()) {
                QLQuaHan q = new QLQuaHan(
                        rs.getString("MaPhieu"),
                        rs.getString("KhachHang"),
                        rs.getString("TenSach"),
                        rs.getString("HanTra"),
                        rs.getString("TrangThai"),
                        rs.getString("GhiChu")
                );
                list.add(q);
            }
            XJDBC.close(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void insert(QLQuaHan q) {
        String sql = """
                INSERT INTO QLQuaHan 
                (MaPhieu, KhachHang, TenSach, HanTra, TrangThai, GhiChu)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try {
            XJDBC.update(sql,
                q.getMaPhieu(),
                q.getKhachHang(),
                q.getTenSach(),
                q.getHanTra(),
                q.getTrangThai(),
                q.getGhiChu()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(QLQuaHan q) {
        String sql = """
                UPDATE QLQuaHan SET 
                    KhachHang = ?, 
                    TenSach = ?, 
                    HanTra = ?, 
                    TrangThai = ?, 
                    GhiChu = ?
                WHERE MaPhieu = ?
                """;

        try {
            XJDBC.update(sql,
                q.getKhachHang(),
                q.getTenSach(),
                q.getHanTra(),
                q.getTrangThai(),
                q.getGhiChu(),
                q.getMaPhieu()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String maPhieu) {
        String sql = "DELETE FROM QLQuaHan WHERE MaPhieu = ?";

        try {
            XJDBC.update(sql, maPhieu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override

public void guiThongBao(String maPhieu) {
    String sql = "UPDATE QLQuaHan SET TrangThai = N'Đã gửi thông báo' WHERE MaPhieu = ?";
    try {
        XJDBC.update(sql, maPhieu); // phải gọi executeUpdate() bên trong XJDBC
    } catch (Exception e) {
        e.printStackTrace();
    }
}
@Override
public void guiThongBaoVaLuuLichSu(String maPhieu) {
    try {
        // 1. Cập nhật trạng thái trong QLQuaHan
        guiThongBao(maPhieu);

        // 2. Lấy đối tượng QLQuaHan theo maPhieu
        QLQuaHan q = null;
        for (QLQuaHan item : getAll()) {
            if (item.getMaPhieu().equals(maPhieu)) {
                q = item;
                break;
            }
        }

        if (q == null) {
            System.out.println("Mã phiếu không tồn tại!");
            return;
        }

        // 3. Thêm vào LichSuThongBao (theo cấu trúc mới)
        String sql = "INSERT INTO LichSuThongBao(maPhieu, khachHang, tenSach, ghiChu) VALUES(?, ?, ?, ?)";
        XJDBC.update(sql, q.getMaPhieu(), q.getKhachHang(), q.getTenSach(), q.getGhiChu());

        System.out.println("Thông báo đã gửi và lưu lịch sử!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
