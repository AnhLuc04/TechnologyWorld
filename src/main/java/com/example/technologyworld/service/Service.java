package com.example.technologyworld.service;


import com.example.technologyworld.model.Manufacturer;
import javassist.NotFoundException;
import jdk.nashorn.internal.runtime.options.Option;

public interface Service<T> {
    Iterable<T> findAll();

  T  findById(Long id) throws NotFoundException;

    void save(T model);

    void remove(Long id);
}
