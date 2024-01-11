package com.example.esa.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.esa.sevice.DrugService;

import java.io.IOException;

@WebServlet(value = "/drug/delete")
public class DrugDeleteServlet extends HttpServlet {

    @EJB
    private DrugService drugService;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        drugService.deleteById(Long.parseLong(request.getParameter("id")));
        response.sendRedirect(request.getContextPath() + "/drug");
    }

}