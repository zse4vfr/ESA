package com.example.esa.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.esa.sevice.PharmacyService;

import java.io.IOException;

@WebServlet(value = "/pharmacy/create")
public class PharmacyCreateServlet extends HttpServlet {

    @EJB
    private PharmacyService pharmacyService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        pharmacyService.setGetRequestForPharmacy(request, response);
        getServletContext().getRequestDispatcher("/pharmacy-create.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        pharmacyService.create(request, response);
        response.sendRedirect(request.getContextPath() + "/pharmacy");
    }

}