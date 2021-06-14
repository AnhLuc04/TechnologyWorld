package com.example.technologyworld.repository;

import com.example.technologyworld.model.Manufacturer;
import com.example.technologyworld.model.Product;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByManufacturer(Manufacturer manufacturer);
}
