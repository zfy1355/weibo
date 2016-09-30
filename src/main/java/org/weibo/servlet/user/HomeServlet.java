package org.weibo.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

import org.weibo.entity.User;
import org.weibo.util.RedisUtils;

@WebServlet("/home")
public class HomeServlet extends BaseServlet{

	private static final long serialVersionUID = 8089875410008505689L;
	
	public void doPost()
			throws ServletException, IOException {
			Cookie[] cookies = request.getCookies();
			String username = null;
			for(Cookie cookie : cookies){
				if("username".equals(cookie.getName()))
					username = cookie.getValue();
			}
			User user = RedisUtils.getUserByUsername(username);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	
	
}

