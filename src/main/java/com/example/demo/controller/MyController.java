package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Contact;
import com.example.demo.entity.Servicee;
import com.example.demo.model.BookingFormDTO;
import com.example.demo.model.ContactDTO;
import com.example.demo.service.BookingService;
import com.example.demo.service.ContactService;
import com.example.demo.service.ServiceForm;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class MyController {

    private ContactService contactService;

    public MyController(ContactService contactService) {
        super();
        this.contactService = contactService;
    }

    private BookingService bookingService;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private ServiceForm serviceForm;

    @Autowired
    public void setServiceForm(ServiceForm serviceForm) {
        this.serviceForm = serviceForm;
    }

    @GetMapping(path = {"/", "/home", "/index"})
    public String indexView(HttpServletRequest req, Model model) {
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        model.addAttribute("mycurrentpage", requestURI);
        model.addAttribute("bookingFormDTO", new BookingFormDTO());
        return "index";
    }

    @GetMapping("/about")
    public String aboutView(HttpServletRequest req, Model model) {
        String requestURI = req.getRequestURI();
        model.addAttribute("mycurrentpage", requestURI);
        return "about";
    }

    @GetMapping("/cars")
    public String carView(HttpServletRequest req, Model model) {
        String requestURI = req.getRequestURI();
        model.addAttribute("mycurrentpage", requestURI);
        return "cars";
    }

    @GetMapping("/services")
    public String serviceView(HttpServletRequest req, Model model) {
        String requestURI = req.getRequestURI();
        model.addAttribute("mycurrentpage", requestURI);
        List<Servicee> allServices = serviceForm.readAllServices();
        model.addAttribute("allservices", allServices);
        return "services";
    }

    @GetMapping("/contacts")
    public String contactView(HttpServletRequest req, Model model) {
        String requestURI = req.getRequestURI();
        model.addAttribute("mycurrentpage", requestURI);
        model.addAttribute("contactdto", new ContactDTO());
        return "contacts";
    }

    @GetMapping("/login")
    public String adminLoginView(HttpServletRequest request, Model model) {
        ServletContext servletContext = request.getServletContext();
        Object attribute = servletContext.getAttribute("logout");
        if (attribute instanceof Boolean) {
            model.addAttribute("logout", attribute);
            servletContext.removeAttribute("logout");
        }

        // Show error message if login failed
        String error = request.getParameter("error");
        if (error != null) {
            model.addAttribute("loginerror", "Invalid username or password. Please try again.");
        }

        // Show logout message
        String logout = request.getParameter("logout");
        if (logout != null) {
            model.addAttribute("logoutmsg", "You have been logged out successfully.");
        }

        return "adminlogin";
    }

    @PostMapping("contactsform")
    public String saveContact(@Valid @ModelAttribute("contactdto") ContactDTO contactdto,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingresult", bindingResult);
            return "contacts";
        } else {
            Contact result = contactService.saveContactForm(contactdto);
            if (result != null) {
                redirectAttributes.addFlashAttribute("msg", "Contact Saved successfully");
            } else {
                redirectAttributes.addFlashAttribute("wmsg", "Something went wrong");
            }
        }
        System.out.println(contactdto);
        return "redirect:/contacts";
    }

    @PostMapping("bookingform")
    public String bookingForm(@Valid @ModelAttribute("bookingFormDTO") BookingFormDTO bookingFormDTO,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        try {

            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
                return "redirect:/";
            } else if (bookingFormDTO.getAdult() + bookingFormDTO.getChildren() > 4) {
                redirectAttributes.addFlashAttribute("message",
                        "The total number of passengers (adults + children) cannot exceed 4");
            } else {

                System.out.println("Booking DTO: " + bookingFormDTO); // DEBUG

                System.out.println("Booking DTO: " + bookingFormDTO);
                Booking result = bookingService.saveBookingForm(bookingFormDTO);

                if (result != null) {
                    redirectAttributes.addFlashAttribute("msg", "Booking successfully");
                } else {
                    redirectAttributes.addFlashAttribute("wmsg", "Something went wrong");
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // VERY IMPORTANT
            redirectAttributes.addFlashAttribute("wmsg", "Error occurred during booking");
        }

        return "redirect:/";
    }
}
