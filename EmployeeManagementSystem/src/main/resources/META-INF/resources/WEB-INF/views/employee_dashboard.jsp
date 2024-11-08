<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.spring.ems.model.Employee" %>
<html>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <body>
        <%
            // Retrieve the list of employees from the request scope
            List<Employee> employees = (List<Employee>) request.getAttribute("listEmployees");
        %>
        <div class="container-fluid" style="margin: 0%; padding: 0px;">
            <div class="row">
                <div class="col-lg-12">
                    <%@ include file="navbar.jsp" %>
                </div>
            </div>
            <div class="row">
                <!-- Sidebar Section with Add Employee Button -->
                <div class="col-sm-2 p-3 bg-light">
                    <button onclick="window.location.href='<%= request.getContextPath() %>/add-employee'" 
                            class="btn btn-primary w-100">Add New Employee</button>
                </div>

                <!-- Main Content Section -->
                <div class="col-md-10 mt-4">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="display-6">Employee List</h1>
                            <hr>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Employee Id</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Position</th>
                                        <th scope="col">Department</th>
                                        <th scope="col">Salary</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(Employee employee : employees) { %>
                                        <tr>
                                            <th scope="row"><%= employee.getId() %></th>
                                            <td><%= employee.getId() %></td>
                                            <td><%= employee.getName() %></td>
                                            <td><%= employee.getPosition() %></td>
                                            <td><%= employee.getDepartment() %></td>
                                            <td><%= employee.getSalary() %></td>
                                            <td>
                                                <a href="<%=request.getContextPath() %>/delete-employee?eid=<%=employee.getId() %>" class="text-danger">Delete</a>
                                            </td>
                                        </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
