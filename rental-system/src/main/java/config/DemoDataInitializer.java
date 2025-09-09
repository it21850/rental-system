package com.rentalsystem.config;

import com.rentalsystem.model.Property;
import com.rentalsystem.service.PropertyService;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class DemoDataInitializer {

    private final PropertyService propertyService;

    public DemoDataInitializer(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostConstruct
    public void init() {
        Property p1 = new Property("Cozy Apartment", "2-bedroom apartment downtown", 500.0, true);
        Property p2 = new Property("Luxury Villa", "5-bedroom villa with pool", 2000.0, true);
        Property p3 = new Property("Studio Flat", "Small studio near university", 300.0, false);

        propertyService.createProperty(p1);
        propertyService.createProperty(p2);
        propertyService.createProperty(p3);
    }
}
