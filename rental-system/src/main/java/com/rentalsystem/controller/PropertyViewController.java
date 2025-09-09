package com.rentalsystem.controller;

import com.rentalsystem.model.Property;
import com.rentalsystem.service.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/properties")
public class PropertyViewController {

    private final PropertyService propertyService;

    public PropertyViewController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("properties", propertyService.getAllProperties());
        return "properties";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("property", new Property());
        model.addAttribute("formAction", "/properties");   // POST /properties
        model.addAttribute("formTitle", "Add New Property");
        return "property_form";
    }

    @PostMapping
    public String create(@ModelAttribute("property") Property property) {
        propertyService.createProperty(property);
        return "redirect:/properties";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return propertyService.getPropertyById(id)
                .map(p -> {
                    model.addAttribute("property", p);
                    model.addAttribute("formAction", "/properties/edit/" + id); // POST /properties/edit/{id}
                    model.addAttribute("formTitle", "Edit Property");
                    return "property_form";
                })
                .orElse("redirect:/properties");
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("property") Property property) {
        propertyService.updateProperty(id, property);
        return "redirect:/properties";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return "redirect:/properties";
    }
}
