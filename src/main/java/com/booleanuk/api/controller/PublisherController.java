package com.booleanuk.api.controller;

import com.booleanuk.api.model.Publisher;
import com.booleanuk.api.model.PublisherDto;
import com.booleanuk.api.repository.PublisherRepository;
import com.booleanuk.api.service.PublisherService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> getAll() {
        return ResponseEntity.ok(this.publisherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.publisherService.getById(id));
    }


    @PostMapping
    public ResponseEntity<Publisher> create(@RequestBody PublisherDto publisherDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            this.publisherService.createPublisher(
                publisherDto.getName(), publisherDto.getLocation()));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Publisher> putPublisher(@PathVariable("id") Integer id, @RequestBody PublisherDto publisherDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(
            this.publisherService.updatePublisher( id,
                publisherDto.getName(), publisherDto.getLocation()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Publisher> deletePublisher(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.publisherService.deletePublisher(id));
    }

}
