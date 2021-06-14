package com.example.technologyworld.repository;

import com.example.technologyworld.model.Duration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
@Repository
public interface DurationRepository extends JpaRepository<Duration,Long> {
}
