package org.weibo.user;

import java.io.IOException;

import javax.servlet.ServletException;

import org.weibo.util.RedisUtils;

public class HomeServlet extends BaseServlet{

	private static final long serialVersionUID = 8089875410008505689L;
	
	public void doPost()
			throws ServletException, IOException {
			request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	
	
}