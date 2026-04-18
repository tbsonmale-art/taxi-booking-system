package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Booking;
import com.example.demo.model.BookingFormDTO;

public interface BookingService 
{
	public Booking saveBookingForm(BookingFormDTO bookingFormDTO);
	
	public List<Booking> readAllBookings();
	
	public void deleteBookingData(int id);
	

}
