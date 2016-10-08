package org.weibo.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.weibo.entity.User;
import org.weibo.util.RedisUtils;

@WebServlet("/profile")
public class ProfileServlet extends BaseServlet{

	private static final long serialVersionUID = 8089875410008505689L;
	
	public void doPost()
			throws ServletException, IOException {
			User user  = getUser(request.getParameter("username"));
			request.setAttribute("user", user);
			
			Cookie[] cookies = request.getCookies();
			String username = null;
			for(Cookie cookie : cookies){
				if("username".equals(cookie.getName()))
					username = cookie.getValue();
			}
			boolean isFollowing = RedisUtils.getIsFollowing(username,user.getId());
			request.setAttribute("isFollowing", isFollowing);
			request.setAttribute("posts",RedisUtils.getPosts(user.getId(), username));
			request.getRequestDispatcher("/profile.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	public User getUser(String username){
		User user = new User();
		user.setId(RedisUtils.getKey("user:username:"+username+":userid"));
		user.setPassword("user:userid"+user.getId()+":password");
		user.setUsername(username);
		
		return user;
	}
	
	
}

