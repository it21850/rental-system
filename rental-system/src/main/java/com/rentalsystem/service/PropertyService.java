package com.rentalsystem.service;

import com.rentalsystem.model.Property;
import com.rentalsystem.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public List<Property> getAllAvailableProperties() {
        return propertyRepository.findAll()
                .stream()
                .filter(Property::isAvailable)
                .toList();
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    /** Ενημέρωση με βάση id. Επιστρέφει Optional με το updated entity. */
    public Optional<Property> updateProperty(Long id, Property incoming) {
        return propertyRepository.findById(id).map(existing -> {
            existing.setTitle(incoming.getTitle());
            existing.setDescription(incoming.getDescription());
            existing.setPrice(incoming.getPrice());
            existing.setAvailable(incoming.isAvailable());
            return propertyRepository.save(existing);
        });
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}
