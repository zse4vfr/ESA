package com.examle.esa.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import com.examle.esa.entity.Pharmacy;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class PharmacyDTO {

    private String address;
    private String head;
    private String district;
    private String phone;
    private List<SimpleDrugDTO> drugs;

    private long uniqueId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private String createdTimestamp;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private String modifiedTimestamp;

    public static PharmacyDTO toDTO(Pharmacy obj){

        var drugs = obj.getDrugForPharmacy()
                .stream()
                .map(dr -> SimpleDrugDTO.builder()
                        .drugId(dr.getDrug().getUniqueId())
                        .drugName(dr.getDrug().getName())
                        .quantity(dr.getQuantity() == null ? 0 : dr.getQuantity())
                        .build())
                .collect(Collectors.toList());

        return PharmacyDTO.builder()
                .uniqueId(obj.getUniqueId())
                .modifiedTimestamp(obj.getModifiedTimestamp().toString())
                .createdTimestamp(obj.getCreatedTimestamp().toString())
                .address(obj.getAddress())
                .head(obj.getHead())
                .district(obj.getDistrict())
                .phone(obj.getPhone())
                .drugs(drugs)
                .build();
    }
}