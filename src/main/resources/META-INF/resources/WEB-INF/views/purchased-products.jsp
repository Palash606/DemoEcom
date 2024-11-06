<%@ page import="com.example.demoecom.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!doctype html public "-//w3c/dtd HTML 4.0//en">
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<body>
<div class="container-fluid" style="margin: 0%; padding: 0px;">
    <div class="row">
        <div class="col-lg-12">
            <%@ include file="navbar.jsp" %>
        </div>
        <%
            List<Product> purchasedProducts = (List<Product>) request.getAttribute("purchasedProducts");
            for (Product product : purchasedProducts) {
        %>

        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><%= product.getName() %>
                        </h5>
                        <p class="card-text">Price: <%= product.getPrice() %>
                        </p>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
