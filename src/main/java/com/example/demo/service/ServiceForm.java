package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Servicee;
import com.example.demo.model.ServiceFormDTO;

public interface ServiceForm 
{
	public String addService(ServiceFormDTO serviceFormDTO,MultipartFile multipartFile) throws IOException;
	
	public List<Servicee> readAllServices();
	
	

}
