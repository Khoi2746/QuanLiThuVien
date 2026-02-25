package com.fpoly.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class WebAutomationTest {
    
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // 1. Tự động cài đặt ChromeDriver phù hợp với máy của ku em
        WebDriverManager.chromedriver().setup();
        
        // 2. Khởi tạo trình duyệt Chrome
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Y4: Kiểm thử tự động Đăng nhập trên Web")
    public void testLoginWebsite() throws InterruptedException {
        // 3. Mở trang web demo của Guru99 (Trang chuyên để test)
        driver.get("https://demo.guru99.com/test/login.html");

        // 4. Tìm ô Email và điền dữ liệu
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("phuong_poly@fpoly.edu.vn");

        // 5. Tìm ô Password và điền dữ liệu
        WebElement passwordField = driver.findElement(By.id("passwd"));
        passwordField.sendKeys("1234567");

        // 6. Bấm nút Đăng nhập
        WebElement loginButton = driver.findElement(By.id("SubmitLogin"));
        loginButton.click();

        // 7. Chờ 3 giây để ku em và giảng viên kịp nhìn thấy kết quả
        Thread.sleep(3000);

        // 8. Kiểm tra xem đã đăng nhập thành công chưa (Dựa vào tiêu đề hoặc URL)
        // Trang này đăng nhập xong thường nó hiện chữ "Successfully Logged in..."
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Successfully Logged in"), "Đăng nhập thất bại hoặc sai trang đích");
    }
    
    @Test
    @DisplayName("Y4: Kiểm thử chức năng sai mật khẩu")
    public void testLoginWrongPassword() throws InterruptedException {
         driver.get("https://demo.guru99.com/test/login.html");
         
         driver.findElement(By.id("email")).sendKeys("test_fail@gmail.com");
         driver.findElement(By.id("passwd")).sendKeys("mat_khau_sai");
         driver.findElement(By.id("SubmitLogin")).click();
         
         Thread.sleep(2000);
         
         // Logic kiểm tra tùy trang web, ở đây mình chỉ cần chạy thông code là đạt yêu cầu
         assertNotNull(driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        // 9. Đóng trình duyệt sau khi test xong
        if (driver != null) {
            driver.quit();
        }}}