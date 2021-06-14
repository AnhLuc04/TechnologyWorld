package com.example.technologyworld.repository;

import com.example.technologyworld.model.Duration;
import com.example.technologyworld.model.Manufacturer;
import com.example.technologyworld.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
    List<Manufacturer> findAllByDuration(Duration duration);
}
