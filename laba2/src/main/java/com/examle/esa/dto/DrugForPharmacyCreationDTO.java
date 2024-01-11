package com.examle.esa.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class DrugForPharmacyCreationDTO {

    private List<SimpleDrugDTO> drugs = new ArrayList<>();

    public void addSimpleIngredient(SimpleDrugDTO dto) {
        this.drugs.add(dto);
    }

}
