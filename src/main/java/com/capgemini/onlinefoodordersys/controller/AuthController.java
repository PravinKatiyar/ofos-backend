package com.capgemini.onlinefoodordersys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinefoodordersys.domain.Response;
import com.capgemini.onlinefoodordersys.domain.UserDTO;
import com.capgemini.onlinefoodordersys.exception.UnauthorizedException;
import com.capgemini.onlinefoodordersys.model.User;
import com.capgemini.onlinefoodordersys.security.JwTokenUtil;
import com.capgemini.onlinefoodordersys.security.JwtUser;
import com.capgemini.onlinefoodordersys.service.AuthService;

@RestController
@CrossOrigin(value="*")

public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwTokenUtil jwTokenUtil;
	

	/*
    *Method Name-------------:Login
    *Method Description------:To authenticate the User
    *Method input Parameter--:User user
    *Method return Type------:ResponseEntity<UserDTO>
    *Method Type-------------:Get
    *Author Name-------------:Pravin Katiyar
    *Method Last Modified----:18-01-2021
	*/
		@PostMapping(value="/login")
		public ResponseEntity<UserDTO> Login(@RequestBody User user,HttpServletRequest request, HttpServletResponse response){
			try {
				Authentication  authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
				final JwtUser userDetails=(JwtUser)authentication.getPrincipal();
				SecurityContextHolder.getContext().setAuthentication(authentication);
				final String token =jwTokenUtil.generateToken(userDetails);
				response.setHeader("Token", token);
				
				return new ResponseEntity<UserDTO>(new UserDTO(userDetails.getUser(),token),HttpStatus.OK);			
			} catch (Exception e) {
				throw new UnauthorizedException("Bad Credentials");				
			}
		}
		
		/*
		    *Method Name-------------:registration
		    *Method Description------:to save User data
		    *Method input Parameter--:User user 
		    *Method return Type------:ResponseEntity<Response>
		    *Method Type-------------:Post
		    *Author Name-------------:Pravin Katiyar
		    *Method Last Modified----:15-01-2021
			*/
	@PostMapping(value="/registration")
	public ResponseEntity<Response> registration (@RequestBody User user){
		User dbUser=authService.registerUser(user);
		if(dbUser!=null) {
			System.out.println(dbUser);
			return new ResponseEntity<Response>(new Response("User is Registered Successfully"),HttpStatus.OK);
		} 
		return new ResponseEntity<Response>(new Response("Something went wrong!!"),HttpStatus.BAD_REQUEST);
	}

}
	
	

