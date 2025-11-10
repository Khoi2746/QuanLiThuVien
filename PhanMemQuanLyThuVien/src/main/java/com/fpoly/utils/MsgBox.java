/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.utils;
/**
 *
 * @author X1 Carbon
 */
import javax.swing.*;

public class MsgBox {
    public static void alert(java.awt.Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(java.awt.Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Xác nhận", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    public static String prompt(java.awt.Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message);
    }
}
