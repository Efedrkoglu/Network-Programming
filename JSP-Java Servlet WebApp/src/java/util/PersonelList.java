/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import entity.personel;
import java.util.ArrayList;

/**
 *
 * @author efe44
 */
public class PersonelList {
    private static ArrayList<personel> list = new ArrayList<personel>();
    
    public static ArrayList<personel> getList() {
        return list;
    }
    
    public static void setList(ArrayList<personel> personelList) {
        list = personelList;
    }
    
    public static int getTotalSalary() {
        int total = 0;
        
        for(personel p : list) {
            total += p.getSalary();
        }
        
        return total;
    }
    
    public static int getPersonelCount() {
        return list.size();
    }
}
