package com.example.esa.repository;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Stateless
public class DrugForPharmacyRepo {

    @PersistenceContext(name = "esa")
    private EntityManager em;

    public void save(com.example.esa.entity.DrugForPharmacy o) {
        em.persist(o);
    }

}