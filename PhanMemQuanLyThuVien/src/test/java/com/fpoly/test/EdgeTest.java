/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.test;

/**
 *
 * @author LENOVO
 */
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class EdgeTest {
   @Test
public void testYoutubeSearch() throws InterruptedException {
    System.setProperty("webdriver.edge.driver", "C:\\msedgedriver.exe");
    WebDriver driver = new org.openqa.selenium.edge.EdgeDriver();
    
    try {
        driver.get("https://www.youtube.com");
        driver.manage().window().maximize();

        // Thay vì dùng Thread.sleep, hãy dùng WebDriverWait (đợi đến khi thấy thì thôi)
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        
        // YouTube đôi khi dùng name="search_query" sẽ chuẩn hơn id="search" trên một số trình duyệt
        WebElement searchBox = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(By.name("search_query")));

        searchBox.clear(); // Xóa trắng cho chắc
        searchBox.sendKeys("giải đề SOF3041");
        searchBox.sendKeys(Keys.ENTER);

        Thread.sleep(5000); // Đợi để xem kết quả tìm kiếm
    } catch (Exception e) {
        System.out.println("Vẫn không tìm thấy ô search: " + e.getMessage());
    } finally {
        driver.quit();
    }
}
}
    

