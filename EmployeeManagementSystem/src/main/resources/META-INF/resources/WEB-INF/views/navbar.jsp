<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>dashboard</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* Custom styles for a more polished look */
    .navbar {
      background-color: #4a90e2; /* Custom blue background */
    }
    .navbar-brand, .nav-link, .dropdown-item, .navbar-text {
      color: #fff !important; /* Make text white */
    }
    .navbar-brand {
      font-weight: bold;
      font-size: 1.5rem;
    }
    .nav-link:hover {
      color: #d1ecf1 !important; /* Lightened color on hover */
    }
    .dropdown-menu {
      background-color: #4a90e2;
      border: none;
    }
    .dropdown-item:hover {
      background-color: #357ab8; /* Darker blue on hover */
    }
    .logout-btn {
      color: #fff;
      border-color: #fff;
      transition: background-color 0.3s ease;
    }
    .logout-btn:hover {
      background-color: #e74c3c;
      color: #fff;
    }
    .welcome-text {
      font-weight: 500;
      color: #fff;
      margin-right: 10px;
    }
  </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Employee Management System</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      </ul>

      <!-- Right side: Welcome message and Logout button -->
      <div class="d-flex align-items-center">
        <span class="welcome-text">Welcome, <strong><%=session.getAttribute("username") %></strong></span>
        <a href="/" class="btn btn-sm logout-btn">Logout</a>
      </div>
    </div>
  </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
