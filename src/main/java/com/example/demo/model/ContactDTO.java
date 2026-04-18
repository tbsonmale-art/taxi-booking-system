package com.example.demo.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactDTO 
{
	@Pattern(regexp="^[A-Za-z//s]+$",message=" name contains only alphabets")
	@Size(min=5,max=20,message="invalid name length")
	private String name;
	
	@NotBlank(message="Email can not be blank")
	private String email;
	
	@Min(value = 1000000000, message = "Phone number must be 10 digits")
	@Max(value = 9999999999L, message = "Phone number must be 10 digits")
	private long phone;

	@NotBlank(message = "Message cannot be blank")
	@Size(min = 5, max = 255, message = "Message must be between 5 and 255 characters")
	private String message;


}
