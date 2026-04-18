package com.example.demo.service;


import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class MapServiceImpl {

    public String generateMapLink(String origin, String destination) {
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
