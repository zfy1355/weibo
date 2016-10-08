package org.weibo.servlet.user;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.weibo.service.UserService;
import org.weibo.util.RedisUtils;

@WebServlet("/registerUser")
public class RegisterServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8089875410008505689L;
	UserService userService = new UserService();
	
	public void doPost()
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		response.setHeader("", "UTF-8");
		
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(password2)){
			request.setAttribute("errMsg", "填写信息不能为空！");
		}
		if(!password.equals(password2)){
			request.setAttribute("errMsg", "两次填写信息不一致！");
		}
		if(RedisUtils.existUser(username)){
			request.setAttribute("errMsg", "该用户名已注册！");
		}
		if(request.getAttribute("errMsg")!=null){
			writeOldAttribute(request);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			userService.registerUser(username,password);
			Cookie cookie = new Cookie("username", username);
			response.addCookie(cookie);
			request.getRequestDispatcher("/home").forward(request, response);
		}
	}
	
	private void writeOldAttribute(HttpServletRequest req){
		Enumeration<String> keys = req.getParameterNames();
		for(String key; keys.hasMoreElements(); ){
			key = keys.nextElement();
			req.setAttribute(key,req.getParameter(key));
		}
	}
	
}
