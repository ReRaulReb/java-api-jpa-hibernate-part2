package com.booleanuk.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.booleanuk.api.exception.InputNotValidException;
import com.booleanuk.api.exception.ResourceNotFoundException;
import com.booleanuk.api.model.Publisher;
import com.booleanuk.api.repository.PublisherRepository;

@Service
public class PublisherService{
	private final PublisherRepository publisherRepository;

	public PublisherService(PublisherRepository publisherRepository){
		this.publisherRepository = publisherRepository;
	}

	public List<Publisher> getAll() {
		return this.publisherRepository.findAll();
	}

	public Publisher getById(Integer id){
		Optional<Publisher> opt = this.publisherRepository.findById(id);
		if(opt.isEmpty())
			throw new ResourceNotFoundException("ID: " + id + " could not be found"); // throw error

		return opt.get();
	}

	public Publisher createPublisher(String name, String location){
		if(isBlank(name) || isBlank(location))
			throw new InputNotValidException("All fields need to be filled in");
		
		Publisher publisher = new Publisher();
		publisher.setName(name);
		publisher.setLocation(location);
		return this.publisherRepository.save(publisher);
	}


	public Publisher updatePublisher(Integer id, String name, String location){
		Optional<Publisher> opt = this.publisherRepository.findById(id);
		if(opt.isEmpty())
			throw new ResourceNotFoundException("ID: " + id + " could not be found");

		if(isBlank(name) || isBlank(location))
			throw new InputNotValidException("All fields need to be filled in");

		Publisher publisher = opt.get();
		publisher.setName(name);
		publisher.setLocation(location);

		return this.publisherRepository.save(publisher);

	}

	public Publisher deletePublisher(Integer id){
		Optional<Publisher> opt = this.publisherRepository.findById(id);
		if(opt.isEmpty())
			throw new ResourceNotFoundException("ID: " + id + " could not be found");


		Publisher publisher = opt.get();
		this.publisherRepository.delete(publisher);
		return publisher;
	}

	private boolean isBlank(String s) {
	    return s == null || s.isBlank();
	}

}
