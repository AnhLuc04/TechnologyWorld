package com.example.technologyworld.model;

import jdk.nashorn.internal.runtime.options.Option;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Manufacturer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;
    public String name_Manufacturer;
    @ManyToOne
    public  Duration duration;
}
