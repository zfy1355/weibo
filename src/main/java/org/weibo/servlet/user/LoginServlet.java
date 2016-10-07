package org.weibo.servlet.user;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.weibo.util.RedisUtils;
import org.weibo.util.SessionManager;

@WebServlet("/login")
public class LoginServlet extends BaseServlet{

	private static final long serialVersionUID = 8089875410008505689L;
	
	public void doPost()
			throws ServletException, IOException {
		String username = request.getParameter("user");
		String password = request.getParameter("passwd");
		response.setHeader("", "UTF-8");
		
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			request.setAttribute("errMsg1", "填写信息不能为空！");
		}
		String id = RedisUtils.getKey("user:username:"+username+":userid");
		if(StringUtils.isEmpty(id)){
			request.setAttribute("errMsg1", "用户名不存在");
		}else{
			if(!password.equals(RedisUtils.getKey("user:userid:"+id+":password"))){
				request.setAttribute("errMsg1", "用户名或密码错误");
			}
		}
		if(request.getAttribute("errMsg1")!=null){
			writeOldAttribute(request);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			Cookie cookie = new Cookie("username", username);
			response.addCookie(cookie);
			SessionManager.addUser(id);
			response.sendRedirect("/home");
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
