<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand" href="index.jsp">E-Commerce-Shopping Cart</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation"></button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart<span class="badge badge-danger px-1">${ cart_list.size()}</span></a></li><!-- yaha pr maine cart me total no of item dikhne ke liye code likh rahe hain <span tag ke ander> -->

				<!-- Hum cahate hai ki jab user login kre keval tab hi user ko Orders Logout option show ho
					iske liye mai use (scriplet tag me rakhenge) (<%  %>)-->
					
				<%
				if (auth != null) {%>
				<li class="nav-item"><a class="nav-link" href="order.jsp">Orders</a></li>
				<li class="nav-item"><a class="nav-link" href="logoutservlet">Logout</a></li>
					<%} else {%>
					 <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				
				<%}
				%>
				
 
			</ul>

		</div>
	</div>
</nav>