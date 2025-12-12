package com.fpoly.entity;

import java.sql.Date;

public class BorrowRequest {
    private int id;
    private int maSach;
    private String tenSach;
    private String tacGia;
    private String theLoai;
    private int soLuong;

    private String studentName;
    private String studentId;
    private String email;
    private String phone;

    private Date borrowDate;

    public BorrowRequest() {
    }

    // Constructor tiện ích (tuỳ ý)
    public BorrowRequest(int maSach, String tenSach, String tacGia, String theLoai, int soLuong,
                         String studentName, String studentId, String email, String phone, Date borrowDate) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
        this.studentName = studentName;
        this.studentId = studentId;
        this.email = email;
        this.phone = phone;
        this.borrowDate = borrowDate;
    }

    // getters / setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMaSach() { return maSach; }
    public void setMaSach(int maSach) { this.maSach = maSach; }

    public String getTenSach() { return tenSach; }
    public void setTenSach(String tenSach) { this.tenSach = tenSach; }

    public String getTacGia() { return tacGia; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }

    public String getTheLoai() { return theLoai; }
    public void setTheLoai(String theLoai) { this.theLoai = theLoai; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Date getBorrowDate() { return borrowDate; }
    public void setBorrowDate(Date borrowDate) { this.borrowDate = borrowDate; }
}
