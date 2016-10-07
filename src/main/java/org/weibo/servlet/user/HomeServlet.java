package org.weibo.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			request.setAttribute("posts", "");
			request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	
}

