package com.booleanuk.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.booleanuk.api.exception.InputNotValidException;
import com.booleanuk.api.exception.ResourceNotFoundException;
import com.booleanuk.api.model.Author;
import com.booleanuk.api.model.Book;
import com.booleanuk.api.model.Publisher;
import com.booleanuk.api.repository.BookRepository;

@Service
public class BookService{
	private final BookRepository bookRepository;
	private final AuthorService authorService;
	private final PublisherService publisherService;

	public BookService(BookRepository bookRepository, AuthorService authorService, PublisherService publisherService){
		this.bookRepository = bookRepository;
		this.authorService = authorService;
		this.publisherService = publisherService;
	}

	public List<Book> getAll() {
		return this.bookRepository.findAll();
	}

	public Book getById(Integer id){
		Optional<Book> opt = this.bookRepository.findById(id);
		if(opt.isEmpty())
			throw new ResourceNotFoundException("ID: " + id + " could not be found"); // throw error

		return opt.get();
	}

	public Book createBook(String title, String genre, Integer authorId, Integer publisherId){
		if(isBlank(title) || isBlank(genre) || authorId == null || publisherId == null || authorId < 0 || publisherId < 0)
			throw new InputNotValidException("All fields need to be filled in and valid");
		
		Author author = this.authorService.getById(authorId);
		Publisher publisher = this.publisherService.getById(publisherId);
		Book book = new Book(title, genre, author, publisher);
		return this.bookRepository.save(book);
	}


	public Book updateBook(Integer id, String title, String genre, Integer authorId, Integer publisherId){
		Optional<Book> opt = this.bookRepository.findById(id);
		if(opt.isEmpty())
			throw new ResourceNotFoundException("ID: " + id + " could not be found");

		if(isBlank(title) || isBlank(genre) || authorId == null || publisherId == null || authorId < 0 || publisherId < 0)
			throw new InputNotValidException("All fields need to be filled in and valid");

		Book book = opt.get();
		Author author = this.authorService.getById(authorId);
		Publisher publisher = this.publisherService.getById(publisherId);
		book.setTitle(title);
		book.setGenre(genre);
		book.setAuthor(author);
		book.setPublisher(publisher);

		return this.bookRepository.save(book);

	}

	public Book deleteBook(Integer id){
		Optional<Book> opt = this.bookRepository.findById(id);
		if(opt.isEmpty())
			throw new ResourceNotFoundException("ID: " + id + " could not be found");


		Book book = opt.get();
		this.bookRepository.delete(book);
		return book;
	}

	private boolean isBlank(String s) {
	    return s == null || s.isBlank();
	}

}
