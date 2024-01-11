package com.example.esa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import com.example.esa.entity.base.BaseEntity;

@Entity
@Table(name = "drug", schema = "public")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Drug extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Integer price;

    @Column(name = "conditionals")
    private String conditionals;

}
