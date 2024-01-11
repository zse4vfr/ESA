package com.examle.esa.service;


import com.examle.esa.dto.PharmacyDTO;
import com.examle.esa.dto.SimpleDrugDTO;
import com.examle.esa.entity.DrugForPharmacy;
import com.examle.esa.jms.utils.EventType;
import lombok.RequiredArgsConstructor;
import com.examle.esa.dto.DrugForPharmacyCreationDTO;
import com.examle.esa.jms.Producer;
import com.examle.esa.entity.Pharmacy;
import com.examle.esa.repository.PharmacyRepo;
import com.examle.esa.repository.DrugForPharmacyRepo;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PharmacyService {

    private final DrugService drugService;
    private final PharmacyRepo pharmacyRepo;
    private final DrugForPharmacyRepo drugForPharmacyRepo;
    private final Producer producer;

    public List<Pharmacy> getAll() {
        return pharmacyRepo.findAllByIsDeletedFalse();
    }

    public Pharmacy getById(Long id) {
        return pharmacyRepo.findOneByUniqueIdAndIsDeletedFalse(id);
    }

    public void deleteById(Long id) {
        var entity = pharmacyRepo.findOneByUniqueId(id);
        entity.setIsDeleted(true);
        pharmacyRepo.save(entity);
        producer.sendMessage(entity, EventType.delete);
    }

    public void setParamsForPharmacy(Model model, int ingCount) {
        model.addAttribute("allDrugs", drugService.getAll());
        model.addAttribute("pharmacy", new Pharmacy());
        DrugForPharmacyCreationDTO dr = new DrugForPharmacyCreationDTO();
        for (int i = 0; i < ingCount; ++i)
            dr.addSimpleDrug(new SimpleDrugDTO());
        model.addAttribute("drugDTO", dr);
    }

    public void create(Pharmacy pharmacy, DrugForPharmacyCreationDTO dto) {
        pharmacyRepo.saveAndFlush(pharmacy);

        Set<DrugForPharmacy> drugForPharmacies = new HashSet<>();
        for (int i = 0; i < dto.getDrugs().size(); ++i){
            DrugForPharmacy drugForPharmacy = new DrugForPharmacy();
            drugForPharmacy.setPharmacyRef(pharmacy.getUniqueId());
            drugForPharmacy.setDrug(
                    drugService.getById(dto.getDrugs().get(i).getDrugId()));
            drugForPharmacy.setQuantity(dto.getDrugs().get(i).getQuantity());
            drugForPharmacyRepo.save(drugForPharmacy);
            drugForPharmacies.add(drugForPharmacy);
        }
        pharmacy.setDrugForPharmacy(drugForPharmacies);
        producer.sendMessage(pharmacy, EventType.create);
    }
    public void create(PharmacyDTO dto) {
        Pharmacy pharmacy = Pharmacy.builder()
                .address(dto.getAddress())
                .head(dto.getHead())
                .district(dto.getDistrict())
                .phone(dto.getPhone())
                .build();
        pharmacyRepo.saveAndFlush(pharmacy);

        Set<DrugForPharmacy> drugForPharmacies = new HashSet<>();
        for (int i = 0; i < dto.getDrugs().size(); ++i){
            DrugForPharmacy drugForPharmacy = new DrugForPharmacy();
            drugForPharmacy.setPharmacyRef(pharmacy.getUniqueId());
            drugForPharmacy.setDrug(
                    drugService.getById(dto.getDrugs().get(i).getDrugId()));
            drugForPharmacy.setQuantity(dto.getDrugs().get(i).getQuantity());
            drugForPharmacyRepo.save(drugForPharmacy);
            drugForPharmacies.add(drugForPharmacy);
        }
        pharmacy.setDrugForPharmacy(drugForPharmacies);
        producer.sendMessage(pharmacy, EventType.create);
    }

}