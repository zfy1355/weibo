package org.weibo.post;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;

import org.weibo.entity.User;
import org.weibo.user.BaseServlet;
import org.weibo.util.RedisUtils;
import org.weibo.util.SessionManager;

public class PostServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5447181731876963907L;
	
	public void doPost() throws ServletException, IOException {
		String content = request.getParameter("content");
		User user = SessionManager.getUser(request.getParameter("username"));
		Long postid = RedisUtils.getIncr("global:postid");
		RedisUtils.setKey("post:postid:"+postid+":userid", user.getId());
		RedisUtils.setKey("post:postid:"+postid+":time", new Date().getTime()+"");
		RedisUtils.setKey("post:postid:"+postid+":content", content);
		 PrintWriter out = response.getWriter();
		 out.print("1");
	}

}









