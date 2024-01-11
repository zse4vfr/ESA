package com.example.esa.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.esa.sevice.DrugService;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/drug")
public class DrugGetAllServlet extends HttpServlet {

    @EJB
    private DrugService drugService;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<com.example.esa.entity.Drug> drugList = drugService.getAll();
        request.setAttribute("drugs", drugList);
        getServletContext().getRequestDispatcher("/drug.jsp").forward(request, response);
    }

}