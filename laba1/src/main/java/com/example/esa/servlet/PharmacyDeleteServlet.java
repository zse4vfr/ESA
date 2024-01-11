package com.example.esa.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.esa.sevice.PharmacyService;

import java.io.IOException;

@WebServlet(value = "/pharmacy/delete")
public class PharmacyDeleteServlet extends HttpServlet {

    @EJB
    private PharmacyService pharmacyService;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        pharmacyService.deleteById(Long.parseLong(request.getParameter("id")));
        response.sendRedirect(request.getContextPath() + "/pharmacy");
    }

}