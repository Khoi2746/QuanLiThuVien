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
import java.io.*;
import javax.swing.*;

public class XImage {
    public static final String IMAGE_DIR = "src/main/resources/images/";

    public static ImageIcon read(String fileName) {
        File path = new File(IMAGE_DIR, fileName);
        return new ImageIcon(path.getAbsolutePath());
    }

    public static void save(File src) {
        File dir = new File(IMAGE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dest = new File(dir, src.getName());
        try {
            try (InputStream is = new FileInputStream(src);
                 OutputStream os = new FileOutputStream(dest)) {
                is.transferTo(os);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Image getAppIcon() {
        return new ImageIcon(IMAGE_DIR + "logo.png").getImage();
    }
}
