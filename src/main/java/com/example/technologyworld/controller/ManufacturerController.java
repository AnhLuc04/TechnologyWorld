package com.example.technologyworld.controller;

import com.example.technologyworld.model.Duration;
import com.example.technologyworld.model.Manufacturer;
import com.example.technologyworld.model.Product;
import com.example.technologyworld.service.DurationService;
import com.example.technologyworld.service.ManufacturerService;
import com.example.technologyworld.service.ProductService;
import javassist.NotFoundException;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Data
@RestController
@CrossOrigin("*")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private DurationService durationService;

    //-------------------Retrieve All --------------------------------------------------------

    @RequestMapping(value = "/manufacturers", method = RequestMethod.GET)
    public ResponseEntity<List<Manufacturer>> listAllManufacturers() {
        List<Manufacturer> manufacturers = (List<Manufacturer>) manufacturerService.findAll();
        if (manufacturers == null) {
            return new ResponseEntity<List<Manufacturer>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Manufacturer>>(manufacturers, HttpStatus.OK);
    }

    //-------------------Retrieve Single --------------------------------------------------------

    @RequestMapping(value = "/manufacturer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manufacturer> getManufacturer(@PathVariable("id") long id) throws Exception {
        System.out.println("Fetching manufacturer with id " + id);
        Manufacturer manufacturer = manufacturerService.findById(id);
        if (manufacturer == null) {
            System.out.println("Manufacturer with id " + id + " not found");
            return new ResponseEntity<Manufacturer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Manufacturer>(manufacturer, HttpStatus.OK);
    }

    //-------------------Create a -------------------------------------------------------

    @RequestMapping(value = "/manufacturer", method = RequestMethod.POST)
    public ResponseEntity<Void> createManufacturer(@RequestBody Manufacturer manufacturer, UriComponentsBuilder ucBuilder) {
        manufacturerService.save(manufacturer);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/manufacturer/{id}").buildAndExpand(manufacturer.Id).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a Customer --------------------------------------------------------

    @RequestMapping(value = "/manufacturer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable("id") long id, @RequestBody Manufacturer manufacturer) throws Exception {
        System.out.println("Updating Manufacturer " + id);

        Manufacturer manufacturer1 = manufacturerService.findById(id);

        if (manufacturer1 == null) {
            System.out.println("Manufacturer with id " + id + " not found");
            return new ResponseEntity<Manufacturer>(HttpStatus.NOT_FOUND);
        }
        manufacturerService.save(manufacturer1);
        return new ResponseEntity<Manufacturer>(manufacturer1, HttpStatus.OK);
    }

    //------------------- Delete a  --------------------------------------------------------

    @RequestMapping(value = "/manufacturer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Manufacturer> deleteManufacturer(@PathVariable("id") long id) throws Exception {
        System.out.println("Fetching & Deleting Manufacturer with id " + id);

        Manufacturer manufacturer = manufacturerService.findById(id);
        if (manufacturer == null) {
            System.out.println("Unable to delete. Manufacturer with id " + id + " not found");
            return new ResponseEntity<Manufacturer>(HttpStatus.NOT_FOUND);
        }
        manufacturerService.remove(id);
        return new ResponseEntity<Manufacturer>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/manufacturers/{id}")
    public ResponseEntity<List<Manufacturer>> listAllManufacturerByDuration(@PathVariable("id") long id) throws NotFoundException {
        Duration duration = durationService.findById(id);
        List<Manufacturer> manufacturers = manufacturerService.findAllByDuration(duration);
        if (duration == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Manufacturer>>(manufacturers, HttpStatus.OK);
    }

}
