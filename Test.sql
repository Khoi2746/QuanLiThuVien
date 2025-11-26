create database Test;
go

use Test;
go

CREATE TABLE Book (
    MaSach        VARCHAR(20) PRIMARY KEY,
    TenSach       NVARCHAR(255) NOT NULL,
    LuotMuon      INT DEFAULT 0,
    TenTacGia     NVARCHAR(255),
    NamXuatBan    INT
);
CREATE TABLE Borrow (
    MaPhieu  VARCHAR(20) PRIMARY KEY,
    MaSV     VARCHAR(20) NOT NULL,
    TenSV    NVARCHAR(255) NOT NULL,
    TenSach  NVARCHAR(255) NOT NULL,
    NgayTra  DATE NOT NULL,
    SoNgayTre INT
);
CREATE TABLE SoLuotMuon (
    id INT IDENTITY(1,1) PRIMARY KEY,   -- Khóa chính tự tăng
    NgayThang DATE NOT NULL,             -- Ngày tháng
    SoLuotMuon INT NOT NULL,             -- Số lượt mượn
    GhiChu NVARCHAR(255) NULL            -- Ghi chú, có thể để trống
);

-- Chèn dữ liệu mẫu vào bảng SoLuotMuon
INSERT INTO SoLuotMuon (NgayThang, SoLuotMuon, GhiChu)
VALUES 
('2025-11-01', 15, N'Ngày đầu tháng'), -- Đã thêm N
('2025-11-02', 20, N'Ngày cao điểm mượn sách'),
('2025-11-03', 12, NULL),
('2025-11-04', 18, N'Thư viện đông học sinh'), -- Đã thêm N
('2025-11-05', 10, N'Ngày nghỉ học');

-- Kiểm tra dữ liệu đã chèn
SELECT * FROM SoLuotMuon;


INSERT INTO Borrow (MaPhieu, MaSV, TenSV, TenSach, NgayTra)
VALUES
('P001', 'PS44141', N'Nguyễn Văn A', N'Lập trình Java cơ bản', '2025-11-01'),
('P002', 'PS44142', N'Trần B', N'C# và .NET nâng cao', '2025-10-20'),
('P003', 'PS44143', N'Lê Hoàng', N'Python từ Zero đến Hero', '2025-11-10'),
('P004', 'PS44144', N'Phạm Minh', N'Cấu trúc dữ liệu & thuật toán', '2025-10-30'),
('P005', 'PS44145', N'Nguyễn Văn C', N'Quản trị cơ sở dữ liệu SQL Server', '2025-11-05');
INSERT INTO Book (MaSach, TenSach, LuotMuon, TenTacGia, NamXuatBan) VALUES
('S001', N'Lập trình Java cơ bản', 120, N'Nguyễn Văn A', 2020),
('S002', N'C# và .NET nâng cao', 85, N'Trần B', 2019),
('S003', N'Python từ Zero đến Hero', 150, N'Lê Hoàng', 2021),
('S004', N'Cấu trúc dữ liệu & thuật toán', 95, N'Phạm Minh', 2018),
('S005', N'Quản trị cơ sở dữ liệu SQL Server', 60, N'Nguyễn Văn C', 2020),
('S006', N'Lập trình Web với JavaScript', 130, N'Dương Hữu', 2022),
('S007', N'HTML & CSS toàn tập', 72, N'Vũ Thanh', 2017),
('S008', N'Phân tích & Thiết kế hệ thống', 40, N'Trần Hải', 2018),
('S009', N'Công nghệ phần mềm', 55, N'Hoàng Anh', 2019),
('S010', N'Nhập môn trí tuệ nhân tạo', 98, N'Đỗ Minh', 2021);

TRUNCATE TABLE SoLuotMuon;