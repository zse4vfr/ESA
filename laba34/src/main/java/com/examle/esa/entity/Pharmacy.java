package com.examle.esa.entity;

import javax.persistence.*;

import com.examle.esa.entity.base.BaseEntity;
import lombok.*;
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