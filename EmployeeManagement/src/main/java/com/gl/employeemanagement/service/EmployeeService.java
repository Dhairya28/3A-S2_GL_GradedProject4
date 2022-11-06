package com.gl.employeemanagement.service;

import java.util.List;
import java.util.Optional;

import com.gl.employeemanagement.entity.Employee;
import com.gl.employeemanagement.entity.Roles;
import com.gl.employeemanagement.entity.User;



public interface EmployeeService {
	public List<Employee> findAll();

	public Optional<Employee> findById(int theId);

	public void save(Employee theEmployee);

	public void updateEmployee(Employee theEmployee);

	public String deleteById(int theId);

	public List<Employee> searchByFirstName(String firstName);

	public List<Employee> sortByFirstName(String sortBy);

	public User saveUser(User user);

	public Roles saveRole(Roles role);

}
