/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.utils;

/**
 *
 * @author X1 Carbon
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public class ShareHelper {
    public static Image APP_ICON;
    static {
        APP_ICON = new ImageIcon("src/main/resources/images/logo.png").getImage();
    }
}
