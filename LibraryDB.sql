-- 1. Tạo Database
CREATE DATABASE LibraryDB;
GO

USE LibraryDB;
GO

-- 2. Tạo bảng Users với ràng buộc Role
CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(50) NOT NULL UNIQUE,
    Password NVARCHAR(50) NOT NULL,
    FullName NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NULL,
    Role NVARCHAR(20) NOT NULL DEFAULT 'Member',
    CONSTRAINT CHK_Role CHECK (Role IN ('Admin','Member','Thủ Thư'))
);

-- 3. Tạo bảng Authors
CREATE TABLE Authors (
    AuthorID INT IDENTITY(1,1) PRIMARY KEY,
    AuthorName NVARCHAR(100) NOT NULL
);

-- 4. Tạo bảng Categories
CREATE TABLE Categories (
    CategoryID INT IDENTITY(1,1) PRIMARY KEY,
    CategoryName NVARCHAR(50) NOT NULL
);

-- 5. Tạo bảng Books (thêm cột Img)
CREATE TABLE Books (
    BookID INT IDENTITY(1,1) PRIMARY KEY,
    Title NVARCHAR(200) NOT NULL,
    AuthorID INT NOT NULL,
    CategoryID INT NOT NULL,
    PublishedYear INT NULL,
    Quantity INT NOT NULL DEFAULT 0,
    Img NVARCHAR(200) NULL,  -- Lưu đường dẫn hoặc tên ảnh
    CONSTRAINT FK_Books_Authors FOREIGN KEY (AuthorID)
        REFERENCES Authors(AuthorID),
    CONSTRAINT FK_Books_Categories FOREIGN KEY (CategoryID)
        REFERENCES Categories(CategoryID)
);

-- 6. Tạo bảng Borrow (Quản lý mượn sách)
CREATE TABLE Borrow (
    BorrowID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    BookID INT NOT NULL,
    BorrowDate DATE NOT NULL DEFAULT GETDATE(),
    ReturnDate DATE NULL,
    Status NVARCHAR(20) NOT NULL DEFAULT 'Borrowed', -- Borrowed, Returned
    CONSTRAINT FK_Borrow_User FOREIGN KEY (UserID)
        REFERENCES Users(UserID),
    CONSTRAINT FK_Borrow_Book FOREIGN KEY (BookID)
        REFERENCES Books(BookID)
);

-- 7. Thêm dữ liệu mẫu
-- Users
INSERT INTO Users (Username, Password, FullName, Email, Role)
VALUES 
('admin', '123456', 'Quản Trị Viên', 'admin@library.com', 'Admin'),
('ngoc', '123456', 'Phạm Ngọc', 'ngoc@example.com', 'Member'),
('khoi', '123456', 'Phạm Thành Khôi', 'khoi@example.com', 'Member'),
('thu', '123456', 'Nguyễn Thư', 'thu@example.com', 'Thủ Thư');

-- Authors
INSERT INTO Authors (AuthorName)
VALUES ('J.K. Rowling'), ('George Orwell'), ('Dan Brown');

-- Categories
INSERT INTO Categories (CategoryName)
VALUES ('Fantasy'), ('Science Fiction'), ('Thriller');

-- Books (có thêm ảnh)
INSERT INTO Books (Title, AuthorID, CategoryID, PublishedYear, Quantity, Img)
VALUES 
('Harry Potter và Hòn Đá Phù Thủy', 1, 1, 1997, 10, 'images/harrypotter.jpg'),
('1984', 2, 2, 1949, 5, 'images/1984.jpg'),
('Mật Mã Da Vinci', 3, 3, 2003, 7, 'images/davinci.jpg'),
('Tuổi Thơ Dữ Dội', 1, 1, 1988, 8, 'images/tuoithoduoi.jpg'),
('Dế Mèn Phiêu Lưu Ký', 2, 1, 1941, 12, 'images/demenphieuluu.jpg');

-- Borrow (mượn sách)
INSERT INTO Borrow (UserID, BookID)
VALUES (2, 1), (3, 3);

-- 8. Kiểm tra dữ liệu
SELECT * FROM Users;
SELECT * FROM Authors;
SELECT * FROM Categories;
SELECT * FROM Books;
SELECT * FROM Borrow;
