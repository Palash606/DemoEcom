package com.spring.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ems.exception.InvalidCredentialsException;
import com.spring.ems.model.Employee;
import com.spring.ems.model.User;
import com.spring.ems.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public User verifyLogin(String username, String password) throws InvalidCredentialsException {
		
		
		return employeeRepository.verifyLogin(username, password);
	}

	public List<Employee> fetchAllCourses() {
		
		return employeeRepository.fetchAllCourses();
	}

	public void softDelete(String eid) {
		
		employeeRepository.softDelete(Integer.parseInt(eid));
		
	}

	public void insertEmployee(Employee employee) {
	 employeeRepository.insertEmployee(employee);
		
	}

		

}
