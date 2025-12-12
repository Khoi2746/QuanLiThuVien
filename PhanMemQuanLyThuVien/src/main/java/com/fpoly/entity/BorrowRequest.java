package com.fpoly.entity;

import java.util.Date;

public class BorrowRequest {

    private int requestID;       // Mã phiếu mượn
    private int userID;          // ID người mượn
    private String hoTen;        // Họ và tên sinh viên
    private String MSSV;         // Mã số sinh viên
    private String email;        // Email sinh viên
    private String phoneNumber;  // Số điện thoại
    private int maSach;          // Mã sách
    private int soLuong;         // Số lượng mượn
    private Date requestDate;    // Ngày mượn
    private String status;       // Trạng thái (Pending, Approved, Rejected)
    private String note;         // Ghi chú

    // ===== GETTERS =====
    public int getRequestID() { return requestID; }
    public int getUserID() { return userID; }
    public String getHoTen() { return hoTen; }
    public String getMSSV() { return MSSV; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getMaSach() { return maSach; }
    public int getSoLuong() { return soLuong; }
    public Date getRequestDate() { return requestDate; }
    public String getStatus() { return status; }
    public String getNote() { return note; }

    // ===== SETTERS =====
    public void setRequestID(int requestID) { this.requestID = requestID; }
    public void setUserID(int userID) { this.userID = userID; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public void setMSSV(String MSSV) { this.MSSV = MSSV; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setMaSach(int maSach) { this.maSach = maSach; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }
    public void setStatus(String status) { this.status = status; }
    public void setNote(String note) { this.note = note; }

    // ===== TOSTRING =====
    @Override
    public String toString() {
        return "BorrowRequest{" +
                "requestID=" + requestID +
                ", userID=" + userID +
                ", hoTen='" + hoTen + '\'' +
                ", MSSV='" + MSSV + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", maSach=" + maSach +
                ", soLuong=" + soLuong +
                ", requestDate=" + requestDate +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}