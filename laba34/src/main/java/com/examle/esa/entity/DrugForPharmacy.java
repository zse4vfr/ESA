package com.examle.esa.entity;

import javax.persistence.*;

import com.examle.esa.entity.base.BaseLink;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "link_pharmacy_drug", schema = "public")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DrugForPharmacy extends BaseLink {

    @Column(name = "pharmacy_ref")
    private Long pharmacyRef;

    @OneToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "drug_ref", referencedColumnName = "unique_id")
    private Drug drug;

    @Column(name = "quantity")
    private Integer quantity;
}
