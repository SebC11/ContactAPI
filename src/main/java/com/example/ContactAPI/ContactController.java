package com.example.ContactAPI;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//Wrapping Repo in web layer
public class ContactController {
	
	private final ContactRepo repo;
	private final ContactModelAssembler assembler;
	
	//Constructor 
	public ContactController(ContactRepo repo, ContactModelAssembler assembler) {
		this.repo = repo;
		this.assembler = assembler;
	}
	
	//Start mapping out actions we can do
	//Functional programming really proved useful in writing this cleanly
	//GET
	
	@GetMapping("/contacts")
	CollectionModel<EntityModel<Contact>> all() {
		//Use map to call toModel on each Contact resource
	    List<EntityModel<Contact>> contacts = repo.findAll().stream()
	      .map(assembler::toModel)
	      .collect(Collectors.toList());
	    return CollectionModel.of(contacts, linkTo(methodOn(ContactController.class).all()).withSelfRel());
	}
	
	@GetMapping("/contacts/{id}")
	EntityModel<Contact> search(@PathVariable Long id) {
		//Find contact
		Contact contact = find(id).orElseThrow(() -> new ContactNotFoundException(id));
		//Link making
		return assembler.toModel(contact);
	}
	
	//Messing around with caching searches. May do it behind the scene already but not sure
	@Cacheable(value="contacts")
	Optional<Contact> find(Long id) {
		return repo.findById(id);
	}
	
	//POST
	
	@PostMapping("/contacts")
	EntityModel<Contact> newContact(@RequestBody Contact newContact) {
		return assembler.toModel(repo.save(newContact));
	}
	
	//PUT
	
	//Functional Programming super useful here. 
	@PutMapping("/contacts/{id}")
	EntityModel<Contact> replaceContact(@RequestBody Contact newContact, @PathVariable Long id) {
		
		return repo.findById(id).map(contact -> {
			contact.setName((newContact.getName()));
			contact.setRelation(newContact.getRelation());
			return assembler.toModel(repo.save(contact));
		}).orElseGet(() -> {
			newContact.setId(id);
			return assembler.toModel(repo.save(newContact));
		});
	}
}