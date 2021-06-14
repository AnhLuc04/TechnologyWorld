package com.example.technologyworld.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id_Product;
    @ManyToOne
    public Manufacturer manufacturer;
    public String product_Name;
    public String product_Img;
    public int product_Price;
    public int product_Amount;
    public String product_Size;
    public String product_Weight;
    public String product_Color;
    public String product_Music;
    public String product_Memory;
    public String product_Operating;
    public String product_MemoryStick;
    public String product_Camera;
    public String product_Pin;
    public String product_Guarantee;
    public String product_Connect;
    public String product_Price_km;
    public String product_start_km;
    public String product_end_km;
}
