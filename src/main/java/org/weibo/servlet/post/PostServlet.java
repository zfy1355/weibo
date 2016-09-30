package org.weibo.servlet.post;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.weibo.entity.User;
import org.weibo.servlet.user.BaseServlet;
import org.weibo.util.RedisUtils;
import org.weibo.util.SessionManager;

@WebServlet("/post")
public class PostServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5447181731876963907L;
	
	public void doPost() throws ServletException, IOException {
		String content = request.getParameter("content");
		User user = SessionManager.getUser(request.getParameter("username"));
		Long postid = RedisUtils.getIncr("global:postid");
		Map<String,String> postMap = new HashMap<String, String>();
		postMap.put("postid", postid+"");
		postMap.put("username", user.getUsername());
		postMap.put("time", new Date().getTime()+"");
		postMap.put("content", content);
		RedisUtils.hmset("post:postid:"+postid, postMap);
		
		RedisUtils.setKey("post:postid:"+postid+":userid", user.getId());
		RedisUtils.setKey("post:postid:"+postid+":time", new Date().getTime()+"");
		RedisUtils.setKey("post:postid:"+postid+":content", content);
		 PrintWriter out = response.getWriter();
		 out.print("1");
	}

}









