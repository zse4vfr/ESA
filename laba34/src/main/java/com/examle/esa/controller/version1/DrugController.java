package com.examle.esa.controller.version1;


import com.examle.esa.entity.Drug;
import lombok.RequiredArgsConstructor;
import com.examle.esa.service.DrugService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/version1/drug")
public class DrugController {

    private final DrugService drugService;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("drugs", drugService.getAll());
        return "drug";
    }

    @GetMapping("/create")
    public String getCreate(Model model) {
        model.addAttribute("drug", new Drug());
        return "drug-create";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute Drug drug) {
        drugService.create(drug);
        return "redirect:/drug";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        drugService.deleteById(id);
        return "redirect:/drug";
    }

}