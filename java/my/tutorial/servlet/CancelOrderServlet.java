package my.tutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my.tutorial.connection.DbCon;
import my.tutorial.dao.OrderDao;




@WebServlet("/cancle-order")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			String id=request.getParameter("id");
			if(id != null) {
				OrderDao orderDao = new OrderDao(DbCon.getConnection());
				orderDao.cancleOrder(Integer.parseInt(id));
			}
			response.sendRedirect("order.jsp");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
