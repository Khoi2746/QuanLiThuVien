/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.test;

/**
 *
 * @author 
 */
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ ChromeTest.class, EdgeTest.class })
public class AllTestsSuite {
    // Lớp này để trống, nó đóng vai trò là cấu hình để chạy cả 2 class trên
}
