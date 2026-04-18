package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Servicee;

@Repository
public interface ServiceRepo extends JpaRepository<Servicee, Integer> {
 
	
	
	@Override
	 <S extends Servicee> S save(S entity);
	
	
	@Override
	public List<Servicee> findAll();
	
}
