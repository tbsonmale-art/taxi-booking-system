package com.example.demo.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Servicee;
import com.example.demo.model.ServiceFormDTO;
import com.example.demo.repository.ServiceRepo;

import jakarta.transaction.Transactional;

@Service
public class ServiceFormImpl implements ServiceForm {

	private String result;
	
	private ServiceRepo serviceRepo;
	
    @Autowired
	public void setServiceRepo(ServiceRepo serviceRepo) {
		this.serviceRepo = serviceRepo;
	}

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(rollbackOn = Exception.class)
	@Override
	public String addService(ServiceFormDTO serviceFormDTO,MultipartFile multipartFile) throws IOException {
		String originalFilename = multipartFile.getOriginalFilename();
		serviceFormDTO.setImage(originalFilename);
		
		Servicee entity = modelMapper.map(serviceFormDTO, Servicee.class);
		entity.setDateTime(LocalDateTime.now());
		
		serviceRepo.save(entity);
		
	
			byte[] bytes = multipartFile.getBytes();
		String path="C:\\Users\\GitanjaliN\\Documents\\workspace-spring-tools-for-eclipse-4.31.0.RELEASE\\Taxy-Booking\\src\\main\\resources\\static\\myservices\\"+originalFilename;
		 try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(bytes);
			
			result="Success";
			
		} catch (Exception e) {
			
			throw e;
		}
		return result;
	}

	@Override
	public List<Servicee> readAllServices() {
		
		return serviceRepo.findAll();
	}

}






