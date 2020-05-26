package com.cts.auth.server.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.auth.server.request.AuthRequest;
import com.cts.auth.server.util.JWTUtils;


@RestController
@RequestMapping("/authenticate")
public class AuthenticationResource {

	
	@Autowired
	JWTUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	  
	  @PostMapping("/user")
	  public String generateTocken(@RequestBody AuthRequest authRequest) throws Exception {
		try {  
		  authenticationManager.authenticate(
				   								new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
		                                    );
		}catch(Exception e) {
			throw new Exception("Invalid UserName and password");
		}
		String token =  jwtUtils.generateToken(authRequest.getUserName());
		
		return token;
	  }
	
	
}
