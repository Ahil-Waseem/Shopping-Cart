<%@page import="java.util.*" %>
<%@page import="my.tutorial.connection.DbCon"%>
<%@page import="my.tutorial.dao.ProductDao"%>
<%@page import="my.tutorial.model.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ProductDao pd = new ProductDao(DbCon.getConnection());

List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list= (ArrayList<Cart>) session.getAttribute("cart-list");

if(cart_list !=null){// ya yha pr jo three line array list ka code add kiya hai issase humera cart item index page pr bhi show krega
	
	request.setAttribute("cart_list",cart_list);
}

%>
<!-- create instance of product dao and importtt it -->
<!-- now i will call the methods -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Shopping Cart!</title>
<%@include file="includes/head.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">

			<!-- This condition is for If i have not any item in my list and if it not empty its prosess/iteretes the for loop -->
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img class="card-img-top" src="productimages/<%= p.getImage() %>" alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%= p.getName() %></h5>
						<h6 class="price">Price:$<%= p.getPrice() %></h6>
						<h6 class="category">Category: <%= p.getCategory() %></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a href="addtocart?id=<%= p.getId()%>" class="btn btn-dark">Add to cart</a> 
							<a href="order-now?quantity=1&id=<%= p.getId() %>" class="btn btn-primary">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%}
	}
%>






		</div>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>