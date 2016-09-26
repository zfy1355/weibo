package org.weibo.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet{
	private static final long serialVersionUID = 8089875410008505689L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public abstract void doPost() throws ServletException, IOException ;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		request = req;
		response = resp;
		response.setHeader("Content-Encoding", "utf-8");
		doPost();
	}
	
}
