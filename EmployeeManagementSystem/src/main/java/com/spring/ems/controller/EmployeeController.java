package com.spring.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.ems.exception.InvalidCredentialsException;
import com.spring.ems.model.Employee;
import com.spring.ems.model.User;
import com.spring.ems.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String showLogin() {
		return "login";
	}

	@GetMapping("/login-form")
	public String handleLogin(HttpServletRequest req, HttpSession session) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			User user = employeeService.verifyLogin(username, password);
			if (user.getRole().equalsIgnoreCase("admin")) {
				session.setAttribute("username", username);
				List<Employee> employees = employeeService.fetchAllCourses();
				req.setAttribute("listEmployees", employees);

				return "employee_dashboard";
			}
		} catch (InvalidCredentialsException e) {
			req.setAttribute("msg", e.getMessage());
			return "login";
		}
		return null;
	}

	@GetMapping("/employee-dashboard")
	public String goToStudentDashboard(HttpServletRequest req, HttpSession session) {
		// fetch all courses
		List<Employee> employees = employeeService.fetchAllCourses();
		// List listCourses courseService.fetchAllCourses();
		req.setAttribute("listEmployees", employees);

		return "employee_dashboard";
	}

	@GetMapping("/delete-employee")
	public String deleteEmployee(HttpServletRequest req) {
		String eid = req.getParameter("eid");
		employeeService.softDelete(eid);
		return "redirect:/employee-dashboard";
	}

	@GetMapping("/add-employee")
	public String showAddEmployeeForm() {
		return "add_employee";
	}

	@PostMapping("/save-employee")
	public String saveEmployee(HttpServletRequest req) {
	String name = req.getParameter("name");
	String position = req.getParameter("position");
	String department = req.getParameter("department");
	double salary = Double.parseDouble(req.getParameter("salary"));

	// Create and set up the employee object
	Employee employee = new Employee();
	employee.setName(name);
	employee.setPosition(position);
	employee.setDepartment(department);
	employee.setSalary(salary);

	employeeService.insertEmployee(employee); // Save employee to the database

		return "redirect:/employee-dashboard"; // Redirect to the home or employee list page
	}

}