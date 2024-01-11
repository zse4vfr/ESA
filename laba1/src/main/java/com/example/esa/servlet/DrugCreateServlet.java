package com.example.esa.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.esa.sevice.DrugService;

import java.io.IOException;

@WebServlet(value = "/drug/create")
public class DrugCreateServlet extends HttpServlet {

    @EJB
    private DrugService drugService;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/drug-create.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        drugService.create(request, response);
        response.sendRedirect(request.getContextPath() + "/drug");
    }


}