package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Booking;
import com.example.demo.model.BookingFormDTO;
import com.example.demo.repository.BookingRepo;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepo bookingRepo;

    public BookingServiceImpl(BookingRepo bookingRepo) {
        super();
        this.bookingRepo = bookingRepo;
    }

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    private EmailService emailService; // ✅ Correct type

    @Override
    public Booking saveBookingForm(BookingFormDTO bookingFormDTO) {

        Booking entity = modelMapper.map(bookingFormDTO, Booking.class);

        try {
            emailService.sendBookingSuccessEmail(bookingFormDTO);
        } catch (Exception e) {
            e.printStackTrace(); // VERY IMPORTANT
            System.out.println("Email failed but continuing booking...");
        }

        return bookingRepo.save(entity);
    }

    @Override
    public List<Booking> readAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    public void deleteBookingData(int id) {
        bookingRepo.deleteById(id);
    }
}