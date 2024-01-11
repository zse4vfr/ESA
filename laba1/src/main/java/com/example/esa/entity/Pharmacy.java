package com.example.esa.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.esa.entity.base.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import java.util.Set;

@Entity
@Table(name = "pharmacy", schema = "public")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Pharmacy extends BaseEntity {

    @Column(name = "address")
    private String address;

    @Column(name = "head")
    private String head;

    @Column(name = "district")
    private String district;

    @Column(name = "phone")
    private String phone;

    @OneToMany
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "pharmacy_ref")
    private Set<DrugForPharmacy> drugForPharmacy;

}