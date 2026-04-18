package com.example.demo.securityy;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogOutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // Set a logout flag in servlet context so the login page can show a message
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("logout", true);
        // Do NOT redirect here - let Spring Security's logoutSuccessUrl handle the redirect
    }
}
