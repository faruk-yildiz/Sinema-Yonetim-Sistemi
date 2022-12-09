package com.farukyildiz.sys.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farukyildiz.sys.entity.Admin;
import com.farukyildiz.sys.repository.AdminReposityory;
import com.farukyildiz.sys.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private final AdminReposityory adminReposityory;
	
	@Autowired
	public AdminServiceImpl(AdminReposityory adminReposityory) {
		this.adminReposityory = adminReposityory;
	}

	@Override
	public Boolean checkInfo(String username, String password) {
		Admin admin=adminReposityory.findByUsername(username);
		if (admin!=null) {
			if(admin.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	
	
}
