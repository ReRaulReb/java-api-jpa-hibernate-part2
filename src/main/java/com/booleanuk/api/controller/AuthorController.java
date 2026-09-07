package com.booleanuk.api.controller;

import com.booleanuk.api.model.Author;
import com.booleanuk.api.model.AuthorDto;
import com.booleanuk.api.repository.AuthorRepository;
import com.booleanuk.api.service.AuthorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAll() {
        return ResponseEntity.ok(this.authorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.authorService.getById(id));
    }


    @PostMapping
    public ResponseEntity<Author> create(@RequestBody AuthorDto authorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            this.authorService.createAuthor(
                authorDto.getFirst_name(), authorDto.getLast_name(), authorDto.getEmail(), authorDto.isAlive()));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Author> putAuthor(@PathVariable("id") Integer id, @RequestBody AuthorDto authorDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(
            this.authorService.updateAuthor( id,
                authorDto.getFirst_name(), authorDto.getLast_name(), authorDto.getEmail(), authorDto.isAlive()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.authorService.deleteAuthor(id));
    }

}
