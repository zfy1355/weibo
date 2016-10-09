package org.weibo.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends BaseServlet{

	private static final long serialVersionUID = 8089875410008505689L;
	
	public void doPost()
			throws ServletException, IOException {
			Cookie cookie = new Cookie("username", "");
			response.addCookie(cookie);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	
}

