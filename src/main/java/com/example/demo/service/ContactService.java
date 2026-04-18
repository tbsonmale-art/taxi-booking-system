package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Contact;
import com.example.demo.model.ContactDTO;

public interface ContactService
{
    public Contact saveContactForm(ContactDTO contactDTO);
    
    public List<Contact> readAllContact();
    
    public void deleteContact(int id);
    

}
