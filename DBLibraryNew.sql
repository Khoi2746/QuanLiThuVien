---------------------------------------------------------
-- TẠO DATABASE
---------------------------------------------------------
IF DB_ID('LibraryDB') IS NOT NULL
    DROP DATABASE LibraryDB;
GO
CREATE DATABASE LibraryDBB;
GO
USE LibraryDBB;
GO


---------------------------------------------------------
-- 1. ROLE – USERS – PERMISSIONS
---------------------------------------------------------
CREATE TABLE UserRoles (
    RoleID INT IDENTITY(1,1) PRIMARY KEY,
    RoleName NVARCHAR(50) UNIQUE NOT NULL,
    Description NVARCHAR(200)
);

CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(50) UNIQUE NOT NULL,
    Password NVARCHAR(100) NOT NULL,
    FullName NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100),
    RoleID INT NOT NULL,
    CONSTRAINT FK_Users_Role FOREIGN KEY (RoleID)
        REFERENCES UserRoles(RoleID)
);

CREATE TABLE SystemPermissions (
    PermissionID INT IDENTITY(1,1) PRIMARY KEY,
    RoleID INT NOT NULL,
    PermissionName NVARCHAR(200) NOT NULL,
    CONSTRAINT FK_Permissions_Role FOREIGN KEY (RoleID)
        REFERENCES UserRoles(RoleID)
);


---------------------------------------------------------
-- 2. TÁC GIẢ – THỂ LOẠI – SÁCH
---------------------------------------------------------
CREATE TABLE Authors (
    AuthorID INT IDENTITY(1,1) PRIMARY KEY,
    AuthorName NVARCHAR(100) NOT NULL
);

CREATE TABLE Categories (
    CategoryID INT IDENTITY(1,1) PRIMARY KEY,
    CategoryName NVARCHAR(50) NOT NULL
);

CREATE TABLE Books (
    BookID INT IDENTITY(1,1) PRIMARY KEY,
    Title NVARCHAR(200) NOT NULL,
    AuthorID INT NOT NULL,
    CategoryID INT NOT NULL,
    PublishedYear INT CHECK (PublishedYear >= 0),
    Quantity INT NOT NULL CHECK (Quantity >= 0),
    Img NVARCHAR(200),
    CONSTRAINT FK_Book_Author FOREIGN KEY (AuthorID)
        REFERENCES Authors(AuthorID),
    CONSTRAINT FK_Book_Category FOREIGN KEY (CategoryID)
        REFERENCES Categories(CategoryID)
);


---------------------------------------------------------
-- 3. MƯỢN – TRẢ – TRỄ
---------------------------------------------------------
CREATE TABLE BorrowRequest (
    RequestID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    BookID INT NOT NULL,
    RequestDate DATETIME NOT NULL DEFAULT GETDATE(),
    Status NVARCHAR(20) DEFAULT 'Pending',
    Note NVARCHAR(200),
    CONSTRAINT FK_Request_User FOREIGN KEY (UserID) REFERENCES Users(UserID),
    CONSTRAINT FK_Request_Book FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

CREATE TABLE Borrow (
    BorrowID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    BookID INT NOT NULL,
    BorrowDate DATE NOT NULL DEFAULT GETDATE(),
    ReturnDate DATE NULL,
    Status NVARCHAR(20) NOT NULL DEFAULT 'Borrowed',
    CONSTRAINT FK_Borrow_User FOREIGN KEY (UserID) REFERENCES Users(UserID),
    CONSTRAINT FK_Borrow_Book FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

CREATE TABLE ReturnLate (
    LateID INT IDENTITY(1,1) PRIMARY KEY,
    BorrowID INT NOT NULL,
    DaysLate INT NOT NULL CHECK (DaysLate > 0),
    FineAmount DECIMAL(10,2) NOT NULL CHECK (FineAmount >= 0),
    CreatedAt DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_Late_Borrow FOREIGN KEY (BorrowID) REFERENCES Borrow(BorrowID)
);


---------------------------------------------------------
-- 4. TRẠNG THÁI – THỐNG KÊ – BACKUP – LOG
---------------------------------------------------------
CREATE TABLE BookStatusHistory (
    StatusID INT IDENTITY(1,1) PRIMARY KEY,
    BookID INT NOT NULL,
    Status NVARCHAR(50) NOT NULL,
    UpdatedBy INT NOT NULL,
    UpdatedAt DATETIME DEFAULT GETDATE(),
    Notes NVARCHAR(200),
    CONSTRAINT FK_Status_Book FOREIGN KEY (BookID) REFERENCES Books(BookID),
    CONSTRAINT FK_Status_User FOREIGN KEY (UpdatedBy) REFERENCES Users(UserID)
);

CREATE TABLE Reports (
    ReportID INT IDENTITY(1,1) PRIMARY KEY,
    ReportType NVARCHAR(50) NOT NULL,
    CreatedAt DATETIME DEFAULT GETDATE(),
    CreatedBy INT NOT NULL,
    Data NVARCHAR(MAX),
    CONSTRAINT FK_Report_User FOREIGN KEY (CreatedBy) REFERENCES Users(UserID)
);

CREATE TABLE SystemBackup (
    BackupID INT IDENTITY(1,1) PRIMARY KEY,
    FilePath NVARCHAR(300) NOT NULL,
    BackupDate DATETIME DEFAULT GETDATE(),
    PerformedBy INT NOT NULL,
    CONSTRAINT FK_Backup_User FOREIGN KEY (PerformedBy) REFERENCES Users(UserID)
);

CREATE TABLE AuditLogs (
    LogID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    Action NVARCHAR(200),
    LogTime DATETIME DEFAULT GETDATE(),
    Details NVARCHAR(MAX),
    CONSTRAINT FK_Audit_User FOREIGN KEY (UserID) REFERENCES Users(UserID)
);


---------------------------------------------------------
-- 5. LOGIN HISTORY – QUÊN MẬT KHẨU – NOTI TRỄ
---------------------------------------------------------
CREATE TABLE LoginHistory (
    LogID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    LoginTime DATETIME DEFAULT GETDATE(),
    IP NVARCHAR(50),
    Device NVARCHAR(100),
    CONSTRAINT FK_Login_User FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE PasswordReset (
    ResetID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    Token NVARCHAR(200) NOT NULL,
    ExpiredAt DATETIME NOT NULL,
    Used BIT NOT NULL DEFAULT 0,
    CONSTRAINT FK_Reset_User FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE OverdueNotifications (
    NotificationID INT IDENTITY(1,1) PRIMARY KEY,
    BorrowID INT NOT NULL,
    UserID INT NOT NULL,
    SendDate DATETIME DEFAULT GETDATE(),
    IsRead BIT DEFAULT 0,
    CONSTRAINT FK_Noti_Borrow FOREIGN KEY (BorrowID) REFERENCES Borrow(BorrowID),
    CONSTRAINT FK_Noti_User FOREIGN KEY (UserID) REFERENCES Users(UserID)
);


---------------------------------------------------------
-- DỮ LIỆU MẪU
---------------------------------------------------------

-- ROLES
INSERT INTO UserRoles (RoleName, Description) VALUES
('Admin', 'Quản trị hệ thống'),
('Thủ Thư', 'Quản lý mượn trả'),
('Member', 'Sinh viên mượn sách');

-- USERS
INSERT INTO Users (Username, Password, FullName, Email, RoleID) VALUES
('admin', '123456', 'Quản Trị Viên', 'admin@lib.com', 1),
('ngoc', '123456', 'Phạm Ngọc', 'ngoc@example.com', 3),
('khoi', '123456', 'Phạm Thành Khôi', 'khoi@example.com', 3),
('thuthu', '123456', 'Nguyễn Thư', 'thu@example.com', 2);

-- PERMISSIONS
INSERT INTO SystemPermissions (RoleID, PermissionName) VALUES
(1, 'Quản trị hệ thống'),
(1, 'Xem báo cáo'),
(1, 'Sao lưu dữ liệu'),
(2, 'Duyệt yêu cầu mượn'),
(2, 'Cập nhật tình trạng sách'),
(3, 'Mượn sách'),
(3, 'Trả sách');

-- AUTHORS
INSERT INTO Authors (AuthorName) VALUES
('J.K. Rowling'),
('George Orwell'),
('Dan Brown'),
('Tô Hoài'),
('Nguyên Hồng');

-- CATEGORIES
INSERT INTO Categories (CategoryName) VALUES
('Fantasy'),
('Science Fiction'),
('Thriller'),
('Văn học Việt Nam');

-- BOOKS
INSERT INTO Books (Title, AuthorID, CategoryID, PublishedYear, Quantity, Img) VALUES
('Harry Potter và Hòn Đá Phù Thủy', 1, 1, 1997, 10, 'images/harry.jpg'),
('1984', 2, 2, 1949, 5, 'images/1984.jpg'),
('Mật Mã Da Vinci', 3, 3, 2003, 7, 'images/davinci.jpg'),
('Dế Mèn Phiêu Lưu Ký', 4, 4, 1941, 12, 'images/demen.jpg'),
('Bỉ Vỏ', 5, 4, 1937, 6, 'images/bivo.jpg'),
('Harry Potter và Phòng Chứa Bí Mật', 1, 1, 1998, 8, 'images/hp2.jpg'),
('Harry Potter và Tên Tù Nhân Ngục Azkaban', 1, 1, 1999, 7, 'images/hp3.jpg'),
('Harry Potter và Chiếc Cốc Lửa', 1, 1, 2000, 10, 'images/hp4.jpg'),
('Harry Potter và Hội Phượng Hoàng', 1, 1, 2003, 12, 'images/hp5.jpg'),
('Harry Potter và Hoàng Tử Lai', 1, 1, 2005, 9, 'images/hp6.jpg'),
('Harry Potter và Bảo Bối Tử Thần', 1, 1, 2007, 11, 'images/hp7.jpg'),

('Trại Súc Vật', 2, 2, 1945, 10, 'images/traisucvat.jpg'),
('Chúa Tể Những Chiếc Nhẫn: Hiệp Hội Nhẫn', 2, 1, 1954, 6, 'images/lotr1.jpg'),
('Chúa Tể Những Chiếc Nhẫn: Hai Tòa Tháp', 2, 1, 1954, 6, 'images/lotr2.jpg'),
('Chúa Tể Những Chiếc Nhẫn: Nhà Vua Trở Lại', 2, 1, 1955, 5, 'images/lotr3.jpg'),

('Biểu Tượng Thất Truyền', 3, 3, 2009, 7, 'images/bieutuong.jpg'),
('Pháo Đài Số', 3, 3, 1998, 6, 'images/phaodaiso.jpg'),
('Thiên Thần Và Ác Quỷ', 3, 3, 2000, 9, 'images/angelsdemons.jpg'),

('Vợ Nhặt', 5, 4, 1954, 10, 'images/vonhat.jpg'),
('Lão Hạc', 5, 4, 1943, 9, 'images/laohac.jpg');

-- BORROW REQUEST
INSERT INTO BorrowRequest (UserID, BookID, Status) VALUES
(2, 1, 'Approved'),
(3, 3, 'Pending');

-- BORROW
INSERT INTO Borrow (UserID, BookID, BorrowDate, Status) VALUES
(2, 1, '2024-11-01', 'Borrowed'),
(3, 3, '2024-10-20', 'Returned');

-- LATE RETURN
INSERT INTO ReturnLate (BorrowID, DaysLate, FineAmount) VALUES
(1, 5, 25000);

-- STATUS HISTORY
INSERT INTO BookStatusHistory (BookID, Status, UpdatedBy, Notes) VALUES
(1, 'Tốt', 4, 'Kiểm tra đầu kỳ'),
(3, 'Rách nhẹ', 4, 'Cần bọc lại');

-- REPORTS
INSERT INTO Reports (ReportType, CreatedBy, Data) VALUES
('TopBorrow', 1, '{"Harry Potter":10,"1984":7}');

-- BACKUP
INSERT INTO SystemBackup (FilePath, PerformedBy)
VALUES ('backup/2024_11_10.bak', 1);

-- AUDIT LOGS
INSERT INTO AuditLogs (UserID, Action, Details) VALUES
(1, 'Thêm sách mới', 'Admin thêm Mật Mã Da Vinci');

-- LOGIN HISTORY
INSERT INTO LoginHistory (UserID, IP, Device) VALUES
(2, '192.168.1.5', 'Laptop Windows'),
(3, '192.168.1.10', 'Android Phone');

-- PASSWORD RESET
INSERT INTO PasswordReset (UserID, Token, ExpiredAt) VALUES
(2, 'abc123', '2024-12-01 23:59');

-- OVERDUE NOTIFICATION
INSERT INTO OverdueNotifications (BorrowID, UserID)
VALUES (1, 2);
