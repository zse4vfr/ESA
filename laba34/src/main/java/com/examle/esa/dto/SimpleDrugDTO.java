package com.examle.esa.dto;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SimpleDrugDTO {

    private long unitCode;
    private long drugId;
    private int quantity;
    private String unitName;
    private String drugName;


}
