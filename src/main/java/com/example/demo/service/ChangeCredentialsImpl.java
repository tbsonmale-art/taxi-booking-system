package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepo;

@Service
public class ChangeCredentialsImpl implements ChangeCredentials
{

    private AdminRepo adminRepo;
	
	@Autowired
	public void setAdminRepo(AdminRepo adminRepo) {
		this.adminRepo = adminRepo;
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String checkCredential(String oldusername, String oldpassword)
	{
	   Optional<Admin> byUsername = adminRepo.findByUsername(oldusername);
	   if(byUsername.isPresent())
	   {
		   Admin admin = byUsername.get();
		   boolean matches = passwordEncoder.matches(oldpassword, admin.getPassword());
		   if(matches)
		   {
			   return "SUCCESS";
		   }
		   else
		   {
			   return"WRONG CREDENTIALS";
		   }
		}
	   else
	   {
		   return"WRONG CREDENTIALS";
	   }
	}

	@Override
	public String updateCredentials(String newusername, String newpassword, String oldusername) 
	{
		int row = adminRepo.updateCredentials(newusername,passwordEncoder.encode(newpassword), oldusername);
		
		if(row==1)
		{
			return "Credentials Update Successfully";	
		}
		else
		{
			return "Failed to update";
		}
			
	}

	
}
