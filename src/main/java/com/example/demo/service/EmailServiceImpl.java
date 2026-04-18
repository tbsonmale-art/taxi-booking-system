package com.example.demo.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.model.BookingFormDTO;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendBookingSuccessEmail(BookingFormDTO booking) {

        String mapLink = generateMapLink(booking.getFrom(), booking.getTo());

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(booking.getEmail());
        msg.setSubject("Booking Successful");

        msg.setText(
                "Hello " + booking.getName() + ",\n\n"
                + "✅ Your booking has been successfully confirmed.\n\n"

                + "📍 From: " + booking.getFrom() + "\n"
                + "📍 To: " + booking.getTo() + "\n"
                + "🗓 Date: " + booking.getDate() + "\n"
                + "⏰ Time: " + booking.getTime() + "\n"
                + "👤 Adults: " + booking.getAdult() + "\n"
                + "👶 Children: " + booking.getChildren() + "\n\n"

                + "🗺 View your route:\n"
                + mapLink + "\n\n"

                + "Thank you for choosing us!\n\n"
                + "Regards,\nYour Company"
        );

        mailSender.send(msg);
    }

    // 🔗 Google Maps link generator
    private String generateMapLink(String origin, String destination) {
        try {
            String encodedOrigin = URLEncoder.encode(origin, "UTF-8");
            String encodedDestination = URLEncoder.encode(destination, "UTF-8");

            return "https://www.google.com/maps/dir/?api=1"
                    + "&origin=" + encodedOrigin
                    + "&destination=" + encodedDestination
                    + "&travelmode=driving";

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error generating map link", e);
        }
    }
}