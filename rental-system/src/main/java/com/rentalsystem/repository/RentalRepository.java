package com.rentalsystem.repository;

import com.rentalsystem.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    // Προσθήκη custom query method
    List<Rental> findByTenantId(Long tenantId);
}
