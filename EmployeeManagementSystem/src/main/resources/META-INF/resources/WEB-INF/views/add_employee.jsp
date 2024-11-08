<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Employee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
            background-color: #f0f8ff; /* Light pastel blue background */
            color: #333; /* Dark text for readability */
        }
        .container {
            max-width: 600px; /* Limit the width of the form */
            margin-top: 50px; /* Space from the top */
            background-color: #ffffff; /* White background for the form */
            border-radius: 10px; /* Rounded corners */
            padding: 30px; /* Inner spacing */
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
        }
        h2 {
            color: #5c6bc0; /* Pastel color for the heading */
            text-align: center; /* Center the heading */
        }
        .btn-primary {
            background-color: #5c6bc0; /* Pastel blue for the button */
            border: none; /* Remove border */
        }
        .btn-primary:hover {
            background-color: #4f5b93; /* Darker shade on hover */
        }
        label {
            font-weight: bold; /* Bold labels */
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h2>Add New Employee</h2>
        <form action="${pageContext.request.contextPath}/save-employee" method="post">
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="mb-3">
                <label for="position" class="form-label">Position</label>
                <input type="text" class="form-control" id="position" name="position" required>
            </div>
            <div class="mb-3">
                <label for="department" class="form-label">Department</label>
                <input type="text" class="form-control" id="department" name="department" required>
            </div>
            <div class="mb-3">
                <label for="salary" class="form-label">Salary</label>
                <input type="text" class="form-control" id="salary" name="salary" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Save Employee</button>
        </form>
    </div>
</body>
</html>
