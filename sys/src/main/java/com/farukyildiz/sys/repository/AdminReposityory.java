package com.farukyildiz.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farukyildiz.sys.entity.Admin;

public interface AdminReposityory extends JpaRepository<Admin, Long> {
	public Admin findByUsername(String username);
}
