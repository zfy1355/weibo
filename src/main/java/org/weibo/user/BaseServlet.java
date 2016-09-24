package org.weibo.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class BaseServlet extends HttpServlet{
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8089875410008505689L;
	
	public void doPost() throws ServletException, IOException{
		doPost(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		request = req;
		response = resp;
		response.setHeader("Content-Encoding", "utf-8");
		doPost();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get Method");
		System.out.println(req.getParameter("name"));
		System.out.println(req.getParameter("password"));
		super.doGet(req, resp);
	}
	
	protected void printMsg(String str) throws IOException{
		response.getWriter().write(str);
	}
	
}
