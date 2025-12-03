
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.utils;

/**
 *
 * @author X1 Carbon
 */

import java.text.*;
import java.util.*;

public class XDate {
    static SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

    public static Date toDate(String date, String... pattern) {
        try {
            if (pattern.length > 0) {
                formater.applyPattern(pattern[0]);
            }
            return formater.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toString(Date date, String... pattern) {
        if (pattern.length > 0) {
            formater.applyPattern(pattern[0]);
        }
        return formater.format(date);
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date now() {
        return new Date();
    }
}
