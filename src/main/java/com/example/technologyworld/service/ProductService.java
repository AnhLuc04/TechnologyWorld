package com.example.technologyworld.service;

import com.example.technologyworld.model.Manufacturer;
import com.example.technologyworld.model.Product;

import java.util.List;

public interface ProductService extends Service<Product> {
    List<Product> findAllProductByManufacturer(Manufacturer manufacturer);
}
