package com.example.esa.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.esa.sevice.PharmacyService;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/pharmacy")
public class PharmacyGetAllServlet extends HttpServlet {

    @EJB
    private PharmacyService pharmacyService;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<com.example.esa.entity.Pharmacy> pharmacyList = pharmacyService.getAll();
        request.setAttribute("pharmacies", pharmacyList);
        getServletContext().getRequestDispatcher("/pharmacy.jsp").forward(request, response);
    }

}