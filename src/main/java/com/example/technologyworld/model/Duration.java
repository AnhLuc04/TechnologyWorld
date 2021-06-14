package com.example.technologyworld.model;

import jdk.jfr.Enabled;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Duration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name_Duration;
}
