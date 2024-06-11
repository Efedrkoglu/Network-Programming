package servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import entity.personel;
import util.PersonelList;

/**
 *
 * @author efe44
 */

@WebServlet(name = "AddServlet", urlPatterns = "/personelEkle")
public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String sicilNo = req.getParameter("sicilNo");
        String department = req.getParameter("department");
        String phone = req.getParameter("phone");
        String dateOfStart = req.getParameter("dateOfStart");
        String salary = req.getParameter("salary");
        String isActive = req.getParameter("isActive");
        
        String message = "";
        
        if(name == null || name.isBlank() || surname == null || surname.isBlank() || dateOfStart == null 
                || dateOfStart.isBlank() || salary == null || salary.isBlank()) {
            message = "Lutfen zorunlu alanları doldurunuz. (Ad, Soyad, Ise Baslama Tarihi, Maas)";
        }
        else {
            if(isActive != null) {
                PersonelList.getList().add(new personel(name, surname, sicilNo, department, phone, dateOfStart, Integer.parseInt(salary), true));
            }
            else {
                PersonelList.getList().add(new personel(name, surname, sicilNo, department, phone, dateOfStart, Integer.parseInt(salary), false));
            }
            message = "Personel eklendi.";
        }
        
        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/personelEkle.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddServlet doGet metodu");
    }
    
}
