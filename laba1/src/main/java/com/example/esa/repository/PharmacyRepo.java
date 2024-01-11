package com.example.esa.repository;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
@Stateless
public class PharmacyRepo {

    @PersistenceContext(name = "esa")
    private EntityManager em;

    public void save(com.example.esa.entity.Pharmacy o) {
        em.persist(o);
        em.flush();
    }

    public List<com.example.esa.entity.Pharmacy> getAll() {
        return em.createQuery(
                "select o from Pharmacy o where o.isDeleted=false",
                        com.example.esa.entity.Pharmacy.class)
                .getResultList();
    }

    public com.example.esa.entity.Pharmacy getById (Long id) {
        return em.createQuery(
                "select o from Pharmacy o where o.isDeleted=false and o.uniqueId=:id",
                        com.example.esa.entity.Pharmacy.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void deleteById(Long id) {
        em.createQuery("update Pharmacy o set o.isDeleted=true where o.uniqueId=:id",
                        com.example.esa.entity.Pharmacy.class)
                .setParameter("id", id)
                .executeUpdate();
    }

}