package com.booleanuk.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.booleanuk.api.exception.InputNotValidException;
import com.booleanuk.api.exception.ResourceNotFoundException;
import com.booleanuk.api.model.Author;
import com.booleanuk.api.repository.AuthorRepository;

@Service
public class AuthorService{
	private final AuthorRepository authorRepository;

	public AuthorService(AuthorRepository authorRepository){
		this.authorRepository = authorRepository;
	}

	public List<Author> getAll() {
		return this.authorRepository.findAll();
	}

	public Author getById(Integer id){
		Optional<Author> opt = this.authorRepository.findById(id);
		if(opt.isEmpty())
			throw new ResourceNotFoundException("ID: " + id + " could not be found"); // throw error

		return opt.get();
	}

	public Author createAuthor(String firstName, String lastName, String email, boolean alive){
		if(isBlank(email) || isBlank(firstName) || isBlank(lastName))
			throw new InputNotValidException("All fields need to be filled in");
		
		Author author = new Author();
		author.setEmail(email);
		author.setFirstName(firstName);
		author.setLastName(lastName);
		author.setAlive(alive);
		return this.authorRepository.save(author);
	}


	public Author updateAuthor(Integer id, String firstName, String lastName, String email, boolean alive){
		Optional<Author> opt = this.authorRepository.findById(id);
		if(opt.isEmpty())
			throw new ResourceNotFoundException("ID: " + id + " could not be found");

		if(isBlank(email) || isBlank(firstName) || isBlank(lastName))
			throw new InputNotValidException("All fields need to be filled in");

		Author author = opt.get();
		author.setEmail(email);
		author.setFirstName(firstName);
		author.setLastName(lastName);
		author.setAlive(alive);

		return this.authorRepository.save(author);

	}

	public Author deleteAuthor(Integer id){
		Optional<Author> opt = this.authorRepository.findById(id);
		if(opt.isEmpty())
			throw new ResourceNotFoundException("ID: " + id + " could not be found");


		Author author = opt.get();
		this.authorRepository.delete(author);
		return author;
	}

	private boolean isBlank(String s) {
	    return s == null || s.isBlank();
	}

}
