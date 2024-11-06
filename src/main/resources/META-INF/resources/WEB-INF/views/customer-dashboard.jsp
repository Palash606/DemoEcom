<%@ page import="com.example.demoecom.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container-fluid" style="margin: 0%; padding: 0px;">
    <div class="row">
        <div class="col-lg-12">
            <%@ include file="navbar.jsp" %> <!-- Include a navigation bar -->
        </div>
    </div>

    <!-- Display Available Products -->
    <div class="row mt-4">
        <div class="col-lg-12">
            <h3>Available Products</h3>
            <%
                // Fetch the list of products from the request scope
                List<Product> products = (List<Product>) request.getAttribute("products");

                if (products != null && !products.isEmpty()) {
                    for (Product product : products) {
            %>
            <div class="col-md-4 mt-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><%= product.getName() %></h5>
                        <p class="card-text">Price: $<%= product.getPrice() %></p>
                        <form method="post" action="${pageContext.request.contextPath}/buy-product">
                            <input type="hidden" name="productId" value="<%= product.getId() %>">
                            <button type="submit" class="btn btn-success">Buy</button>
                        </form>
                    </div>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <p>No products available.</p>
            <%
                }
            %>
        </div>
    </div>

    <!-- List Purchased Products -->
    <div class="row mt-4">
        <div class="col-lg-12">
            <h3>Purchased Products</h3>
            <%
                // Fetch the list of purchased products from the request scope
                List<Product> purchasedProducts = (List<Product>) request.getAttribute("purchasedProducts");

                if (purchasedProducts != null && !purchasedProducts.isEmpty()) {
                    for (Product product : purchasedProducts) {
            %>
            <div class="col-md-4 mt-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><%= product.getName() %></h5>
                        <p class="card-text">Price: $<%= product.getPrice() %></p>
                    </div>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <p>No products purchased yet.</p>
            <%
                }
            %>
        </div>
    </div>

    <!-- Add New Product Form (for admin or sellers) -->
    <div class="row mt-4">
        <div class="col-lg-12">
            <h3>Add a New Product</h3>
            <form method="post" action="${pageContext.request.contextPath}/add-product">
                <div class="mb-3">
                    <label for="name" class="form-label">Product Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Price</label>
                    <input type="number" step="0.01" class="form-control" id="price" name="price" required>
                </div>
                <div class="mb-3">
                    <label for="stock" class="form-label">Stock</label>
                    <input type="number" class="form-control" id="stock" name="stock" required>
                </div>
                <button type="submit" class="btn btn-primary">Add Product</button>
            </form>
        </div>
    </div>

</div>
</body>
</html>
