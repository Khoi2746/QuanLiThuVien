/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.DaoImpl;

/**
 *
 * @author LENOVO
 */
import com.fpoly.Dao.OverallStatisticsDAO;
import com.fpoly.entity.OverallStatistics;

import com.fpoly.utils.XJDBC; // Giả định đây là lớp tiện ích JDBC của bạn
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OverallStatisticsDAOImpl implements OverallStatisticsDAO {

    @Override
    public OverallStatistics getOverallStatistics() {
        int tongSachDauVao = 0;
        int sachDaMuonChuaTra = 0;
        int sachHienCon = 0;

        // Truy vấn 1: Tổng Số Lượng Sách Đầu Vào (tổng SoLuong từ bảng Book)
        String sqlTongSachDauVao = "SELECT SUM(SoLuong) FROM Book";
        try (Connection con = XJDBC.getConnection(); // Sử dụng getConnection() đã sửa
             PreparedStatement ps = con.prepareStatement(sqlTongSachDauVao);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                tongSachDauVao = rs.getInt(1); // Lấy giá trị của cột đầu tiên
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Truy vấn 2: Tổng Số Lượng Đã Mượn Chưa Trả
        // (Đếm số MaPhieu từ bảng Borrow với điều kiện phù hợp)
        // **LƯU Ý:** Thiết kế bảng Borrow của bạn KHÔNG có cột NgayTraThucTe
        // hoặc trạng thái để xác định "chưa trả".
        // Giả định: Sách "đã mượn chưa trả" là những phiếu có NgayTra (dự kiến) >= GETDATE()
        // Hoặc bạn cần thêm một cột 'TrangThai' vào bảng Borrow (ví dụ: 'Đã mượn', 'Đã trả')
        String sqlSachDaMuonChuaTra = "SELECT COUNT(*) FROM Borrow WHERE NgayTra >= GETDATE()"; 
        try (Connection con = XJDBC.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlSachDaMuonChuaTra);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                sachDaMuonChuaTra = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Truy vấn 3: Tổng Số Lượng Hiện Còn
        // (Tổng sách đầu vào - Tổng sách đã mượn chưa trả)
        sachHienCon = tongSachDauVao - sachDaMuonChuaTra; 
        
        return new OverallStatistics(tongSachDauVao, sachDaMuonChuaTra, sachHienCon);
    }
}
