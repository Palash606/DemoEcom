package com.spring.ems.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.spring.ems.exception.InvalidCredentialsException;
import com.spring.ems.model.Employee;
import com.spring.ems.model.User;

@Repository
public class EmployeeRepository {

	@Autowired
	private JdbcTemplate jdbc;

	public User verifyLogin(String username, String password) throws InvalidCredentialsException {
		String sql = "select * from User where username=? and password=?";
		PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				return pstmt;
			}
		};

		List<User> list = jdbc.query(psc, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUsername(username);
				user.setPassword(password);
				user.setRole(rs.getString("role"));
				return user;
			}

		});
		if (list.isEmpty()) {
			throw new InvalidCredentialsException("Invalid Credentials");
		} else
			return list.get(0);
	}

	public List<Employee> fetchAllCourses() {
		// prepare the statement
		String sql = "select employee_id, name, position, department, salary from employee e where e.is_active=?";

		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setBoolean(1, true);
				return pstmt;
			}

		};

		RowMapper<Employee> rowMapper = new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

				Employee employee = new Employee();

				int employeeId = rs.getInt("employee_id");
				String name = rs.getString("name");
				String position = rs.getString("position");
				String department = rs.getString("department");
				double salary = rs.getDouble("salary");

				employee.setId(employeeId);
				employee.setName(name);
				employee.setPosition(position);
				employee.setDepartment(department);
				employee.setSalary(salary);

				return employee;
			}
		};

		List<Employee> list = jdbc.query(psc, rowMapper);

		return list;

	}

	public void softDelete(int eid) {
		String sql = "update employee set is_active=false where employee_id=?";
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, eid);
				return pstmt;
			}

		};

		jdbc.update(psc);

	}

	public void insertEmployee(Employee employee) {
		
	        String sql = "INSERT INTO Employee (name, position, department, salary) VALUES (?, ?, ?, ?)";
	        PreparedStatementCreator psc = new PreparedStatementCreator()
	        {
	        	@Override
	        	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
	        		 PreparedStatement pstmt = con.prepareStatement(sql);
	                 pstmt.setString(1, employee.getName());
	                 pstmt.setString(2, employee.getPosition());
	                 pstmt.setString(3, employee.getDepartment());
	                 pstmt.setDouble(4, employee.getSalary());
	                 return pstmt;
	        
			}
	        	
	        	};


	            jdbc.update(psc);
	       
		
	}
}
