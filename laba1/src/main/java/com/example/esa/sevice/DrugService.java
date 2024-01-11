package com.example.esa.sevice;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Stateless
public class DrugService {

    @EJB
    private com.example.esa.repository.DrugRepo ingredientRepo;

    public void save(com.example.esa.entity.Drug o) {
        ingredientRepo.save(o);
    }

    public List<com.example.esa.entity.Drug> getAll() {
        return ingredientRepo.getAll();
    }

    public void create(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        com.example.esa.entity.Drug o = new com.example.esa.entity.Drug();
        o.setName(request.getParameter("name"));
        save(o);
    }

    public com.example.esa.entity.Drug getById(Long id) {
        return ingredientRepo.getById(id);
    }

    public void deleteById(Long id) {
        ingredientRepo.deleteById(id);
    }
}