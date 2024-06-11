/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import entity.personel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import util.PersonelList;
/**
 *
 * @author efe44
 */

@WebServlet(name = "ListServlet", urlPatterns = "/personelListele")
public class ListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("personelList", PersonelList.getList());
        req.setAttribute("personelCount", PersonelList.getPersonelCount());
        req.setAttribute("totalSalary", PersonelList.getTotalSalary());
        getServletContext().getRequestDispatcher("/personelListele.jsp").forward(req, resp);
    }
    
}
