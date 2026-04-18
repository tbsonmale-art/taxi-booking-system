package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.model.ContactDTO;
import com.example.demo.repository.ContactRepo;


@Service
public class ContactServiceImp implements ContactService
{

	private ContactRepo contactRepo;
	
	
	@Autowired
	public void setContactRepo(ContactRepo contactRepo) {
		this.contactRepo = contactRepo;
	}

	@Override
	public Contact saveContactForm(ContactDTO contactDTO) 
	{
		Contact contact= new Contact();
		contact.setName(contactDTO.getName());
		contact.setEmail(contactDTO.getEmail());
		contact.setPhone(contactDTO.getPhone());
		contact.setMessage(contactDTO.getMessage());
		contact.setDate(LocalDateTime.now());
		
		 return contactRepo.save(contact);
	}


	@Override
	public List<Contact> readAllContact() {
		
		return contactRepo.findAll();
	}

	@Override
	public void deleteContact(int id) 
	{
		contactRepo.deleteById(id);
		
	}
	

}
