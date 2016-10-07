package org.weibo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.weibo.entity.User;
import org.weibo.util.RedisUtils;

@WebServlet("follow")
public class FollowServlet extends BaseServlet {

	@Override
	public void doPost() throws ServletException, IOException {
		String type = request.getParameter("f");
		String followingId = request.getParameter("uid");
		Cookie[] cookies = request.getCookies();
		String username = null;
		for(Cookie cookie : cookies){
			if("username".equals(cookie.getName()))
				username = cookie.getValue();
		}
		User user = RedisUtils.getUserByUsername(username);
		switch (type) {
		case "1":
			RedisUtils.follow(username,followingId);
			if(RedisUtils.exist("user:userid:"+user.getId()+":followingC")){
				RedisUtils.setKey("user:userid:"+user.getId()+":followingC", (Integer.parseInt(RedisUtils.getKey("user:userid:"+user.getId()+":followerC"))+1)+"");
				RedisUtils.setKey("user:userid:"+followingId+":followerC", (Integer.parseInt(RedisUtils.getKey("user:userid:"+user.getId()+":followerC"))+1)+"");
			}else{
				RedisUtils.setKey("user:userid:"+user.getId()+":followingC","1" );
				RedisUtils.setKey("user:userid:"+followingId+":followerC", "1");
			}
			break;
		default:
			RedisUtils.unfollow(username,followingId);
			if(RedisUtils.exist("user:userid:"+user.getId()+":followingC")){
				RedisUtils.setKey("user:userid:"+user.getId()+":followingC", (Integer.parseInt(RedisUtils.getKey("user:userid:"+user.getId()+":followerC"))-1)+"");
				RedisUtils.setKey("user:userid:"+followingId+":followerC", (Integer.parseInt(RedisUtils.getKey("user:userid:"+user.getId()+":followerC"))-1)+"");
			}else{
				RedisUtils.setKey("user:userid:"+user.getId()+":followingC","0" );
				RedisUtils.setKey("user:userid:"+followingId+":followerC", "0");
			}
			break;
		}
		 PrintWriter out = response.getWriter();
		 out.print("1");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	

}