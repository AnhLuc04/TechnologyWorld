package com.example.technologyworld.service;

import com.example.technologyworld.model.Duration;
import com.example.technologyworld.repository.DurationRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DurationServiceImpl implements DurationService {
    @Autowired
    DurationRepository durationRepository;

    @Override
    public Iterable<Duration> findAll() {
        return durationRepository.findAll();
    }

    @Override
    public Duration findById(Long id) throws NotFoundException {
        return durationRepository.getById(id);
    }

    @Override
    public void save(Duration model) {
        durationRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        durationRepository.deleteById(id);
    }
}
