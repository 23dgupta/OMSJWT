package com.durgesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.filter.JwtUtil;
import com.durgesh.model.Admin;
import com.durgesh.model.AuthRequest;
import com.durgesh.model.JwtResponse;
import com.durgesh.model.Response;
import com.durgesh.service.AdminService;
import com.durgesh.service.CustomUserDetailsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@GetMapping("/")
	public Response test() {
		return new Response("Welcome to Admin Service");
	}

	@PostMapping("/register")
	public Long createAdmin(@RequestBody Admin admin) {
		return adminService.saveAdmin(admin);
	}

	@PostMapping("/login")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invaid username or Password");
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
		String token = jwtUtil.generateToken(userDetails);
		// converting token to json
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
