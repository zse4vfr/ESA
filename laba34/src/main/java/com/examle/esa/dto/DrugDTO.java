package com.examle.esa.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import com.examle.esa.entity.Drug;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class DrugDTO {

    private String name;
    private String category;
    private Integer price;
    private String conditions;

    private long uniqueId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private String createdTimestamp;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private String modifiedTimestamp;

    public static DrugDTO toDTO(Drug obj){
        return DrugDTO.builder()
                .uniqueId(obj.getUniqueId())
                .modifiedTimestamp(obj.getModifiedTimestamp().toString())
                .createdTimestamp(obj.getCreatedTimestamp().toString())
                .name(obj.getName())
                .category(obj.getCategory())
                .price(obj.getPrice())
                .conditions(obj.getConditions())
                .build();
    }
}