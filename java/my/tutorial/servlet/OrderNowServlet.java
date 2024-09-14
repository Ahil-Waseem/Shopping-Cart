package my.tutorial.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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


@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try (PrintWriter out = response.getWriter()){
			//out.print("order now servlet");
			SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
			Date date = new Date();
			
			User auth =(User) request.getSession().getAttribute("auth");
			if(auth !=null) {
				String product = request.getParameter("id");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				if(productQuantity <=0) {
					productQuantity=1;
				}
				
				Order orderModel = new Order();
				orderModel.setId(Integer.parseInt(product));
				orderModel.setUid(auth.getId());
				orderModel.setQuantity(productQuantity);
				orderModel.setDate(formatter.format(date));
				
				OrderDao orderdao = new OrderDao(DbCon.getConnection());
				boolean result=orderdao.insertOrder(orderModel);
				
				if(result) {
					
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if(cart_list != null) {
						for(Cart c:cart_list) {
							if(c.getId()== Integer.parseInt(product)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
							
					}
					
					response.sendRedirect("order.jsp");
				}else {
					out.println("Order failed");
				}
			}else {
				response.sendRedirect("login.jsp");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
