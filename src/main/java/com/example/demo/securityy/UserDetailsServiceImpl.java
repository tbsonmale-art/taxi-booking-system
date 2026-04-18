package com.example.demo.securityy;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepo;

import jakarta.annotation.PostConstruct;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AdminRepo adminRepo;

    @Autowired
    public void setAdminRepo(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        long count = adminRepo.count();
        if (count == 0) {
            Admin admin = new Admin();
            admin.setUsername("tukaram");
            admin.setPassword(passwordEncoder.encode("tukaram123"));
            adminRepo.save(admin);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> byUsername = adminRepo.findByUsername(username);
        Admin admin = byUsername.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return User.withUsername(admin.getUsername())
                   .password(admin.getPassword())
                   .roles("ADMIN")
                   .build();
    }
}
