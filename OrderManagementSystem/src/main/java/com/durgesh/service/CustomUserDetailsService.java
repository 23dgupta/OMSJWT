package com.durgesh.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.durgesh.model.Admin;
import com.durgesh.repository.AdminRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private AdminRepo adminRepo;
 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin= adminRepo.findByUserName(username);
		if(admin==null) {
			throw new UsernameNotFoundException("Bad credentails");
		}
		return new User(admin.getUserName(),admin.getPassword(),new ArrayList<>());
	}

}
