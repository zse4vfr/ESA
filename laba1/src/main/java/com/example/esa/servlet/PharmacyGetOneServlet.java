package com.example.esa.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.esa.sevice.PharmacyService;

import java.io.IOException;

@WebServlet(value = "/pharmacy/view")
public class PharmacyGetOneServlet extends HttpServlet {

    @EJB
    private PharmacyService pharmacyService;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        com.example.esa.entity.Pharmacy d = pharmacyService.getById(Long.parseLong(request.getParameter("id")));
        request.setAttribute("o", d);
        getServletContext().getRequestDispatcher("/pharmacy-view.jsp").forward(request, response);
    }

}