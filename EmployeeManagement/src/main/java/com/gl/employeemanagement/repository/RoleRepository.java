package com.gl.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.employeemanagement.entity.Roles;



public interface RoleRepository extends JpaRepository<Roles, Integer> {

}
