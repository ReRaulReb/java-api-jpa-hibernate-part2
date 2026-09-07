package com.booleanuk.api.controller;

import com.booleanuk.api.model.Book;
import com.booleanuk.api.model.BookDto;
import com.booleanuk.api.repository.BookRepository;
import com.booleanuk.api.service.BookService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(this.bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.bookService.getById(id));
    }


    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            this.bookService.createBook(
                bookDto.getTitle(), bookDto.getGenre(), bookDto.getAuthor_id(), bookDto.getPublisher_id()));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Book> putBook(@PathVariable("id") Integer id, @RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(
            this.bookService.updateBook( id,
                bookDto.getTitle(), bookDto.getGenre(), bookDto.getAuthor_id(), bookDto.getPublisher_id()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.bookService.deleteBook(id));
    }

}
