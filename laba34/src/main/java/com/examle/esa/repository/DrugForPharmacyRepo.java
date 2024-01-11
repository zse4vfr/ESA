package com.examle.esa.repository;


import com.examle.esa.repository.base.BaseLinkRepo;
import com.examle.esa.entity.DrugForPharmacy;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugForPharmacyRepo extends BaseLinkRepo<DrugForPharmacy, Long> {
}