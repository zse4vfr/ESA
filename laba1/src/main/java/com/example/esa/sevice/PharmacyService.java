package com.example.esa.sevice;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class PharmacyService {

    @EJB
    private DrugService drugService;

    @EJB
    private com.example.esa.repository.PharmacyRepo pharmacyRepo;

    @EJB
    private com.example.esa.repository.DrugForPharmacyRepo drugForPharmacyRepo;

    public void save(com.example.esa.entity.Pharmacy o) {
        pharmacyRepo.save(o);
    }

    public List<com.example.esa.entity.Pharmacy> getAll() {
        return pharmacyRepo.getAll();
    }

    public com.example.esa.entity.Pharmacy getById(Long id) {
        return pharmacyRepo.getById(id);
    }

    public void setGetRequestForPharmacy(HttpServletRequest request, HttpServletResponse response) {
        List<com.example.esa.entity.Drug> allIng = drugService.getAll();
        request.setAttribute("allDrugs", allIng);
        request.setAttribute("ingCount", request.getParameter("ingCount"));
    }

    public void create(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        com.example.esa.entity.Pharmacy pharmacy = new com.example.esa.entity.Pharmacy();
        Set<com.example.esa.entity.DrugForPharmacy> drugForPharmacyes = new HashSet<>();

        pharmacy.setAddress(request.getParameter("address"));
        pharmacy.setHead(request.getParameter("head"));
        pharmacy.setDistrict(request.getParameter("district"));
        pharmacy.setPhone(request.getParameter("phone"));
        save(pharmacy);

        int i = 0;
        do {
            com.example.esa.entity.DrugForPharmacy drugForPharmacy = new com.example.esa.entity.DrugForPharmacy();
            drugForPharmacy.setPharmacyRef(pharmacy.getUniqueId());
            drugForPharmacy.setDrug(
                    drugService.getById(
                            Long.parseLong(request.getParameter("drug" + i))));
            drugForPharmacy.setQuantity(
                            Integer.parseInt(request.getParameter("drug" + i)));
            drugForPharmacyRepo.save(drugForPharmacy);
            drugForPharmacyes.add(drugForPharmacy);
            i += 1;
        }
        while (request.getParameter("drug" + i) != null);
        pharmacy.setDrugForPharmacy(drugForPharmacyes);
    }

    public void deleteById(Long id) {
       pharmacyRepo.deleteById(id);
    }
}