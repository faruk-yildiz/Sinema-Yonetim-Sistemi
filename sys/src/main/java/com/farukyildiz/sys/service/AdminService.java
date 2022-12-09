package com.farukyildiz.sys.service;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {
	Boolean checkInfo(String username,String password);
}
