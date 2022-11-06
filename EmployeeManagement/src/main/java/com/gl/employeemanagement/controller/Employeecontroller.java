package com.gl.employeemanagement.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.employeemanagement.entity.Employee;
import com.gl.employeemanagement.entity.Roles;
import com.gl.employeemanagement.entity.User;
import com.gl.employeemanagement.service.EmployeeService;



@RestController
@RequestMapping("/api")
public class Employeecontroller {
	
	
	private EmployeeService employeeService;
	
	@Autowired
    public Employeecontroller(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
	@PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return employeeService.saveUser(user);
    }

    @PostMapping("/role")
    public Roles saveRole(@RequestBody Roles role) {
        return employeeService.saveRole(role);
    }


	@GetMapping("/employees/employeesList")
	public List<Employee> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return employeeService.findAll();
	}

	@GetMapping("/employees/{employee_id}")
	public Optional<Employee> getEmployee(@PathVariable int employee_id) {
		return employeeService.findById(employee_id);
	}

	@PostMapping("/employees/add")
	public Employee addEmployee(@RequestBody Employee theEmployees) {
		employeeService.save(theEmployees);
		return theEmployees;

	}

	@PutMapping("/employees/update")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	@DeleteMapping("/employees/delete/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		return employeeService.deleteById(employeeId);

	}


	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return employeeService.searchByFirstName(firstName);
	}

	@GetMapping("/employees/sort")
	public List<Employee> sortByFirstName(@RequestParam("order") String sortBy) {
		return employeeService.sortByFirstName(sortBy);
	}


}
