package my.tutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import my.tutorial.model.Cart;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/addtocart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		try(PrintWriter out=response.getWriter()) {
			
			ArrayList<Cart> cartList=new ArrayList<>();
			
			int id=Integer.parseInt(request.getParameter("id"));
			
			Cart cn=new Cart();
			cn.setId(id);
			cn.setQuantity(1);
			
			HttpSession session= request.getSession();
			ArrayList<Cart> cart_list=(ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list ==null) {
				cartList.add(cn);
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("index.jsp");
			}else {
				//out.println("cart list exist");
				cartList = cart_list;
				boolean exist=false;
				
				for(Cart c:cart_list) {
					if(c.getId()==id) {
						exist=true;
						
						out.println("<h2 style='color:red; text-align:center'>Item already exist in Cart.<a href='cart.jsp'>Go To Cart Page</a></h2>");
					}
				
				}
				if(!exist) {
					cartList.add(cn);
					response.sendRedirect("index.jsp");
				}
			}
		
			
		}
	}

}
