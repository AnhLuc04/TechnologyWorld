package com.example.technologyworld.service;

import com.example.technologyworld.model.Manufacturer;
import com.example.technologyworld.model.Product;
import com.example.technologyworld.repository.ProductRepository;
import javassist.NotFoundException;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) throws NotFoundException {
        return productRepository.getById(id);
    }

    @Override
    public void save(Product model) {
        productRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllProductByManufacturer(Manufacturer manufacturer) {

        return productRepository.findAllByManufacturer(manufacturer);
    }
}
