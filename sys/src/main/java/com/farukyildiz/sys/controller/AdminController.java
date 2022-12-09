package com.farukyildiz.sys.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farukyildiz.sys.entity.Admin;
import com.farukyildiz.sys.service.AdminService;

@RestController
@RequestMapping("/api/auth")
public class AdminController {
	
	private AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Boolean> getToken (@RequestBody Admin admin,HttpServletResponse response) {
		Boolean status=adminService.checkInfo(admin.getUsername(), admin.getPassword());
		HttpHeaders headers =new HttpHeaders();
		if(status) {
			String tokenString=generateToken();
			Cookie cookie=new Cookie("sys_admin_token", tokenString);
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			return new ResponseEntity<Boolean>(true,headers,HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(false,headers,HttpStatus.FORBIDDEN);
		}
	}
	
	public String generateToken() {
		 String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		         + "0123456789"
		         + "abcdefghijklmnopqrstuvxyz";
		 
		  // create StringBuffer size of AlphaNumericString
		  StringBuilder sb = new StringBuilder(10);
		 
		  for (int i = 0; i < 10; i++) {
		 
		   // generate a random number between
		   // 0 to AlphaNumericString variable length
		   int index
		    = (int)(AlphaNumericString.length()
		      * Math.random());
		 
		   // add Character one by one in end of sb
		   sb.append(AlphaNumericString
		      .charAt(index));
		  }
		 
		  return sb.toString();
		 }
	
	
}
