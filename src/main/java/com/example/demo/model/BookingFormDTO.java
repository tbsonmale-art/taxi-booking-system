package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookingFormDTO 
{
	@NotBlank(message = "Name cannot be blank")  
	@Size(min = 2, max = 50, message = "Name must be between 2–50 characters")
	@Pattern(regexp="^[A-Za-z//s]+$",message="Name contains only alphabets")
	private String name;
	
	@NotBlank(message = "From location is required")
	private String from;
	 
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "To location is required")
	private String to;
	
	@NotNull(message = "Time is required")
	private LocalTime time;
	
	@NotNull(message = "The date field cannot be null.")
	@Column(nullable = false)
	private LocalDate date;

	
	@NotEmpty(message = "The comfort field cannot be empty")
	@Size(min = 2, max = 20, message = "The comfort value must be between 2 and 20 characters long.")
	private String comfort;

	
	@Min(value = 1, message = "The adult field must have a value of at least 1")
	@Max(value = 4, message = "The adult value cannot be more than 4")
	@Column(nullable = false)
	private int adult;

	 
	@Max(value = 3, message = "The children field can have a value of at most 3")
	@Column(nullable = false)
	private int children;

	
	@NotEmpty(message = "The message field cannot be empty")
	@NotBlank(message = "The message cannot consist of only blank spaces")
	@Size(min = 2, max = 20000, message = "The message must be at least 2 characters long and at most 20,000 characters long")
	@Column(length = 2000, nullable = false)
	private String message;
   
	

}
