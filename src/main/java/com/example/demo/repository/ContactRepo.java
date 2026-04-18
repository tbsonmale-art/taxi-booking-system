package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Integer> 
{

	@Override
	public List<Contact> findAll();
		
	
	@Override
	 <S extends Contact> S save(S entity);
	
	@Override
	 void deleteById(Integer id);
	
		
}