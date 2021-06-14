package com.example.technologyworld.service;

import com.example.technologyworld.model.Duration;
import com.example.technologyworld.model.Manufacturer;
import com.example.technologyworld.repository.ManufacturerRepository;
import javassist.NotFoundException;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Override
    public Iterable<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer findById(Long id) throws NotFoundException {
        return manufacturerRepository.getById(id);
    }

    @Override
    public void save(Manufacturer model) {
        manufacturerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        manufacturerRepository.deleteById(id);
        ;
    }

    @Override
    public List<Manufacturer> findAllByDuration(Duration duration) {
        return manufacturerRepository.findAllByDuration(duration);
    }
}
