/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fpoly.Dao;

import com.fpoly.entity.QLQuaHan;
import java.util.List;
/**
 *
 * @author hạ
 */
public interface QLQuaHanDao {
   List<QLQuaHan> getAll();
    void insert(QLQuaHan q);
    void update(QLQuaHan q);
    void delete(String maPhieu);
}
