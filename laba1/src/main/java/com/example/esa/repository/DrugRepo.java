package com.example.esa.repository;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
@Stateless
public class DrugRepo {

    @PersistenceContext(name = "esa")
    private EntityManager em;

    public void save(com.example.esa.entity.Drug o) {
        em.persist(o);
        em.flush();
    }

    public List<com.example.esa.entity.Drug> getAll() {
        return em.createQuery(
                "select o from Drug o where o.isDeleted=false",
                        com.example.esa.entity.Drug.class)
                .getResultList();
    }

    public com.example.esa.entity.Drug getById(Long id) {
        return em.createQuery(
                "select o from Drug o where o.isDeleted=false and o.uniqueId=:id",
                        com.example.esa.entity.Drug.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void deleteById(Long id) {
        em.createQuery(
                "update Drug o set o.isDeleted=true where o.uniqueId=:id",
                        com.example.esa.entity.Drug.class)
                .setParameter("id", id)
                .executeUpdate();
    }

}