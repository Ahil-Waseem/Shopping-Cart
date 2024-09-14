package my.tutorial.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my.tutorial.connection.DbCon;
import my.tutorial.dao.UserDao;
import my.tutorial.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
// maine pahale se hi url=(user-login) my.tutorial.servlet package me bna liya tha
//@WebServlet("/user-login") is annototation ko maine isliyea comment out kiya hai kyu ki mera server error de raha tha 
// program run kerne pr

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
// ye maine print kiya hai ye dekhne ke liye li mera servlet connect hua hai ya nahi			
			//out.print("this is login servlet");
			String email =request.getParameter("login-email");//yaha pr login-email isliyae liya hai ki login.jsp page me maine email name=login-email diya hai
			String password =request.getParameter("login-password");
			
			//ab yaha pr servlet me mujhe DbCon.java yani database ko connect kerana hai niche
			try {
				UserDao udao=new UserDao(DbCon.getConnection());
				User user=udao.userlogin(email, password);
				
				if(user !=null) {
					request.getSession().setAttribute("auth", user);
					response.sendRedirect("index.jsp");
					//out.print("user logged in");
				}else {
					out.print("user login failed");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.print("email+password");
			}
	}

}
