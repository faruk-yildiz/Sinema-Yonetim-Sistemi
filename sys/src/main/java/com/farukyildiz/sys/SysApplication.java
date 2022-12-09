package com.farukyildiz.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.farukyildiz.sys.service.SaloonService;

@SpringBootApplication
public class SysApplication {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SysApplication.class, args);
        SaloonService service = applicationContext.getBean(SaloonService.class);
        service.checkExpiration();
	}
	
	
}
