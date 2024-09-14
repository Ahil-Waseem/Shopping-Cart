package my.tutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my.tutorial.model.Cart;


@WebServlet("/removefromcartservlet")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try (PrintWriter out= response.getWriter()){
		
		String id= request.getParameter("id");
		out.println("Product Id: "+ id);
		
		if(id!=null) {
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			if(cart_list != null) {
				for(Cart c:cart_list) {
					if(c.getId()== Integer.parseInt(id)) {
						cart_list.remove(cart_list.indexOf(c));
						break;
					}
				}
				response.sendRedirect("cart.jsp");	
			}
		}else {
			response.sendRedirect("cart.jsp");
		}
	}
	}

	
}
