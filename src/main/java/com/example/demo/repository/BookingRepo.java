package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{

	
	@Override
	public List<Booking> findAll();
		
	
	
	@Override
	void deleteById(Integer id) ;
}
