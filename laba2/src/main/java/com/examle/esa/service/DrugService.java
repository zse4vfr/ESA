package com.examle.esa.service;


import com.examle.esa.entity.Drug;
import com.examle.esa.repository.DrugRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrugService {

    private final DrugRepo drugRepo;

    public List<Drug> getAll() {
        return drugRepo.findAllByIsDeletedFalse();
    }

    public Drug getById(Long id) {
        return drugRepo.findOneByUniqueIdAndIsDeletedFalse(id);
    }

    public void deleteById(Long id) {
        drugRepo.deleteById(id);
    }

    public void create(Drug drug) {
        drugRepo.save(drug);
    }

}