package com.examle.esa.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SimpleDrugDTO {

    private long unitCode;
    private long drugId;
    private int quantity;

}
