package com.example.technologyworld.controller;

import com.example.technologyworld.model.Manufacturer;
import com.example.technologyworld.model.Product;
import com.example.technologyworld.service.ManufacturerService;
import com.example.technologyworld.service.ProductService;
import javassist.NotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Data
@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired(required=true)
    private ProductService productService;
    @Autowired(required=true)
    private ManufacturerService manufacturerService;

    //-------------------Retrieve All --------------------------------------------------------

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> listAllProduct() {
        List<Product> products = (List<Product>) productService.findAll();
        if (products == null) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    //-------------------Retrieve Single -------------------------------------------------------

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) throws Exception {
        System.out.println("Fetching Product with id " + id);
        Product product = productService.findById(id);
        if (product == null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    //-------------------Create a --------------------------------------------------------

    @PostMapping(value = "/product")
    public ResponseEntity<Void> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
        productService.save(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.Id_Product).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a --------------------------------------------------------

    @PutMapping(value = "/productUpdate/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) throws Exception {
        System.out.println("Updating Product " + id);

        Product product1 = productService.findById(id);

        if (product1 == null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        productService.save(product1);
        return new ResponseEntity<Product>(product1, HttpStatus.OK);
    }

    //------------------- Delete a  --------------------------------------------------------

    @DeleteMapping(value = "/productDelete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) throws Exception {
        System.out.println("Fetching & Deleting Product with id " + id);

        Product product = productService.findById(id);
        if (product == null) {
            System.out.println("Unable to delete. Product with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        productService.remove(id);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<List<Product>> listAllProductByManufacturer(@PathVariable("id") long id) throws NotFoundException {
        Manufacturer manufacturer = manufacturerService.findById(id);
        List<Product> products = productService.findAllProductByManufacturer(manufacturer);
        if (products == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
