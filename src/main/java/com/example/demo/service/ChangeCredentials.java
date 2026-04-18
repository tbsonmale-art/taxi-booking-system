package com.example.demo.service;

public interface ChangeCredentials 
{
	public String checkCredential(String oldusername, String oldpassword);
	
	public String updateCredentials(String newusername, String newpassword,String oldusername);

}
