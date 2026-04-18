package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.ServiceFormDTO;
import com.example.demo.service.BookingService;
import com.example.demo.service.ChangeCredentials;
import com.example.demo.service.ContactService;
import com.example.demo.service.ServiceForm;

import jakarta.validation.Valid;

@Controller
@RequestMapping("admin")
public class AdminController 
{

	
	
	private ChangeCredentials changeCredentials;
   
	@Autowired
	public void setChangeCredentials(ChangeCredentials changeCredentials) {
		this.changeCredentials = changeCredentials;
	}


	private ContactService contactService;

  
	@Autowired
	public void setContactService(ContactService contactService) {
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

	@GetMapping("dashboard")
	public String dashboardView()
	{
		return"admin/dashboard";
	}
	
	@GetMapping("readAllContact")
	public String readAllContact(Model model)
	{
		model.addAttribute("allContacts", contactService.readAllContact());
		
		return "admin/readallcontacts";
	}
	
	@GetMapping("deleteContact/{id}")
	public String deleteContact(@PathVariable int id,RedirectAttributes redirectAttributes)
	{
		contactService.deleteContact(id);
		redirectAttributes.addFlashAttribute("msg", "Contact deleted Successfully");
		System.out.println(id);
		
		
		return "redirect:/admin/readAllContact";
		
	}
	
	@GetMapping("readAllBooking")
	public String readAllBookings(Model model)
	{
		model.addAttribute("allbookings",bookingService.readAllBookings());
		
		
		return "admin/readallbookings";
		
	}
	
	@GetMapping("deleteBooking/{id}")
	public String deleteBooking(@PathVariable int id,RedirectAttributes redirectAttributes)
	{
		bookingService.deleteBookingData(id);
		redirectAttributes.addFlashAttribute("msg", "Booking deleted Successfully");
		System.out.println(id);
		
		
		return "redirect:/admin/readAllBooking";
		
	}
		
	@GetMapping("changeCredentials")
	public String changeCredentialView()
	{
		return "admin/changecredentials";
	}
	
	@PostMapping("changeCredentials")
	public String checkCredential(@RequestParam("oldusername") String oldusername,
			@RequestParam("oldupassword") String oldupassword,
			@RequestParam("newusername") String newusername,
			@RequestParam("newpassword") String newpassword,RedirectAttributes redirectAttributes
			
			)
	{
		
		String result = changeCredentials.checkCredential(oldusername, oldupassword);
		System.out.println(result);
		if(result.equals("SUCCESS"))
		{
			 result = changeCredentials.updateCredentials(newusername, newpassword, oldusername);
			 redirectAttributes.addFlashAttribute("msg", result);
		}
		else
		{
			redirectAttributes.addFlashAttribute("wmsg", result);
		}
		return "redirect:/admin/dashboard";
		
	}
	@GetMapping("addServices")
	public String addServiceView()
	{
		
		return"admin/addservices";
	}
	
	@InitBinder
	public void stopBinding(WebDataBinder webDataBinder)
	{
		webDataBinder.setDisallowedFields("image");
	}
	
	
	@PostMapping("addServices")
	public String addService(@Valid @ModelAttribute ServiceFormDTO serviceFormDTO,
		@RequestParam("image")MultipartFile multipartFile,RedirectAttributes redirectAttributes)
	{
		try {
			String result = serviceForm.addService(serviceFormDTO, multipartFile);
			redirectAttributes.addFlashAttribute("msg", result);
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
		}
		
		
		System.out.println(serviceFormDTO);
		return"redirect:/admin/addServices";
	}
	
	
	
	

}
