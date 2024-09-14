package my.tutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my.tutorial.connection.DbCon;
import my.tutorial.dao.OrderDao;
import my.tutorial.model.Cart;
import my.tutorial.model.Order;
import my.tutorial.model.User;



/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/cart-check-out")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try(PrintWriter out= response.getWriter()) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
			Date date = new Date();
			
			//retrive all cart products
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			//user authentication
			User auth =(User) request.getSession().getAttribute("auth");
			
			// check auth and cart list
			if( cart_list !=null && auth != null){
				
				for(Cart c:cart_list){
					//prepeared the order object
					Order order= new Order();
					order.setId(c.getId());
					order.setUid(auth.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					//instantiate the dao class
					OrderDao oDao = new OrderDao(DbCon.getConnection());
					
					//calling the insert method
					boolean result = oDao.insertOrder(order);
					if(!result) break;
				}
				cart_list.clear();
				response.sendRedirect("order.jsp");
				
			}else {
				if(auth == null) response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
