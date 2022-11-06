package com.gl.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gl.employeemanagement.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	
	public User getUserByUsername(String username);
}
