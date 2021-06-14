package com.example.technologyworld.controller;



import com.example.technologyworld.model.Duration;
import com.example.technologyworld.service.DurationService;
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
public class DurationController {
        @Autowired
        private DurationService durationService;

        //-------------------Retrieve All--------------------------------------------------------

        @RequestMapping(value = "/duration", method = RequestMethod.GET)
        public ResponseEntity<List<Duration>> listAllDuration() {
            List<Duration> durations = (List<Duration>) durationService.findAll();
            if (durations == null) {
                return new ResponseEntity<List<Duration>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<List<Duration>>(durations, HttpStatus.OK);
        }

        //-------------------Retrieve Single --------------------------------------------------------

        @RequestMapping(value = "/duration/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Duration> getDuration(@PathVariable("id") long id) throws Exception {
            System.out.println("Fetching Duration with id " + id);
            Duration duration= durationService.findById(id);
            if (duration== null) {
                System.out.println("Duration with id " + id + " not found");
                return new ResponseEntity<Duration>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Duration>(duration, HttpStatus.OK);
        }

        //-------------------Create a --------------------------------------------------------

        @RequestMapping(value = "/duration", method = RequestMethod.POST)
        public ResponseEntity<Void> createDuration(@RequestBody Duration duration, UriComponentsBuilder ucBuilder) {
            durationService.save(duration);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/duration/{id}").buildAndExpand(duration.id).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }

        //------------------- Update a  --------------------------------------------------------

        @RequestMapping(value = "/duration/{id}", method = RequestMethod.PUT)
        public ResponseEntity<Duration> updateDuration(@PathVariable("id") long id, @RequestBody Duration duration) throws Exception {
            System.out.println("Updating Duration" + id);

            Duration duration1 = durationService.findById(id);

            if (duration1 == null) {
                System.out.println("Duration with id " + id + " not found");
                return new ResponseEntity<Duration>(HttpStatus.NOT_FOUND);
            }
            durationService.save(duration1);
            return new ResponseEntity<Duration>(duration1, HttpStatus.OK);
        }

        //------------------- Delete a Customer --------------------------------------------------------

        @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
        public ResponseEntity<Duration> deleteCustomer(@PathVariable("id") long id) throws Exception {
            System.out.println("Fetching & Deleting Duration with id " + id);

            Duration duration = durationService.findById(id);
            if (duration == null) {
                System.out.println("Unable to delete. Duration with id " + id + " not found");
                return new ResponseEntity<Duration>(HttpStatus.NOT_FOUND);
            }

            durationService.remove(id);
            return new ResponseEntity<Duration>(HttpStatus.NO_CONTENT);
        }
    }
