<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=ISO-8859-
1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>Product Details</h1>
		</div>
	</div>
	</section>
	<section class="container">
	<div class="row">
		<div class="col-md-5">
			<img
				src="<c:url value="/resource/images/${product.productId}.png"></c:url>"
				alt="image" style="width: 100%" />
		</div>
		<div class="col-md-5">
			<h3>${product.name}</h3>
			<p>${product.description}</p>
			<p>
				<strong>Item Code : </strong><span class="label label-warning">${product.productId}</span>
			</p>
			<p>
				<strong>manufacturer</strong> : ${product.manufacturer}
			</p>
			<p>
				<strong>category</strong> : ${product.category}
			</p>
			<p>
				<strong>Availble units in stock </strong> : ${product.unitsInStock}
			</p>
			<h4>${product.unitPrice}USD</h4>
			<p>
				<a href="#" class="btn btn-warning btn-large"> <span
					class="glyphicon-shopping-cart glyphicon"></span> Order Now
				</a>
			</p>

			<a href="<spring:url value="/products" />" class="btn btn-default">
				<span class="glyphicon-hand-left glyphicon"></span> back
			</a>
		</div>
	</div>
	</section>
</body>
</html>