package org.weibo.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.weibo.entity.User;
import org.weibo.util.RedisUtils;

@WebServlet("/profile")
public class ProfileServlet extends BaseServlet{

	private static final long serialVersionUID = 8089875410008505689L;
	
	public void doPost()
			throws ServletException, IOException {
			User user  = getUser(request.getParameter("username"));
			request.setAttribute("user", user);
			request.getRequestDispatcher("/profile.jsp").forward(request, response);
	}
	
	
	public User getUser(String username){
		User user = new User();
		user.setId(RedisUtils.getKey("user:username:"+username+":userid"));
		user.setPassword("user:userid"+user.getId()+":password");
		user.setUsername(username);
		
		return user;
	}
	
	
}

