package com.example.technologyworld.service;


import com.example.technologyworld.model.Duration;
import com.example.technologyworld.model.Manufacturer;
import com.example.technologyworld.model.Product;

import java.util.List;

public interface ManufacturerService extends Service<Manufacturer> {
    List<Manufacturer> findAllByDuration(Duration duration);
}
