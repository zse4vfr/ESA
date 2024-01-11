package com.examle.esa.repository;


import com.examle.esa.entity.Drug;
import com.examle.esa.repository.base.BaseEntityRepo;
import org.springframework.stereotype.Repository;


@Repository
public interface DrugRepo extends BaseEntityRepo<Drug, Long> {
}