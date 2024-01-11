package com.examle.esa.controller.version1;


import lombok.RequiredArgsConstructor;
import com.examle.esa.dto.DrugForPharmacyCreationDTO;
import com.examle.esa.entity.Pharmacy;
import com.examle.esa.service.PharmacyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/version1/pharmacy")
public class PharmacyController {

    private final PharmacyService PharmacyService;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("pharmacies", PharmacyService.getAll());
        return "pharmacy";
    }

    @GetMapping("/create")
    public String getCreate(@RequestParam int ingCount, Model model) {
        PharmacyService.setParamsForPharmacy(model, ingCount);
        return "pharmacy-create";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute Pharmacy Pharmacy, @ModelAttribute DrugForPharmacyCreationDTO dto) {
        PharmacyService.create(Pharmacy, dto);
        return "redirect:/pharmacy";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        PharmacyService.deleteById(id);
        return "redirect:/pharmacy";
    }

    @GetMapping("/view/{id}")
    public String getOne(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pharmacy", PharmacyService.getById(id));
        return "pharmacy-view";
    }

}