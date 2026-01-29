/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutomationTest {

    @Test
    void testLoginWeb() throws InterruptedException {
        // 1. Chỉ định đường dẫn tới chromedriver (Tải tại: https://googlechromelabs.github.io/chrome-for-testing/)
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        
        WebDriver driver = new ChromeDriver();
        
        try {
            // 2. Mở trang web kiểm thử (Trang demo chuẩn cho Selenium)
            driver.get("https://the-internet.herokuapp.com/login");
            driver.manage().window().maximize();

            // 3. Tìm các phần tử và nhập liệu
            driver.findElement(By.id("username")).sendKeys("tomsmith");
            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

            // 4. Click nút Login
            driver.findElement(By.cssSelector("button.radius")).click();

            // 5. Kiểm tra kết quả (Assert) xem có thông báo thành công không
            WebElement message = driver.findElement(By.id("flash"));
            assertTrue(message.getText().contains("You logged into a secure area!"));
            
            Thread.sleep(2000); // Đợi 2 giây để quan sát
        } finally {
            // 6. Đóng trình duyệt
            driver.quit();
        }
    }
}
