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
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;

public class ChromeTest {
    @Test
public void testGoogleSearch() throws InterruptedException {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();
    driver.get("https://www.google.com");
    // Đợi 2 giây cho trang load hẳn
    Thread.sleep(2000); 
    driver.findElement(By.name("q")).sendKeys("đề SOF3041", Keys.ENTER);
    Thread.sleep(3000);
    driver.quit();
}
}