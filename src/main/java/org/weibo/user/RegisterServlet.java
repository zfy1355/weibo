package org.weibo.user;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.weibo.util.RedisUtils;

import redis.RedisUtil;

public class RegisterServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8089875410008505689L;
	
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
		if(!StringUtils.isEmpty(RedisUtils.getKey("user:username:"+username+":userid"))){
			request.setAttribute("errMsg", "该用户名已注册！");
		}
		if(request.getAttribute("errMsg")!=null){
			writeOldAttribute(request);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			Long id = RedisUtils.getIncr("userid");
			RedisUtils.setKey("user:userid:"+ id+":username",username);
			RedisUtils.setKey("user:userid:"+ id+":password",password);
			RedisUtils.setKey("user:username:"+username+":userid", id+"");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
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
