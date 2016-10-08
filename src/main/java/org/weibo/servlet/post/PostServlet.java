package org.weibo.servlet.post;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

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
		
		Cookie[] cookies = request.getCookies();
		String username = null;
		for(Cookie cookie : cookies){
			if("username".equals(cookie.getName()))
				username = cookie.getValue();
		}
		User user = RedisUtils.getUserByUsername(username);
		
		Long postid = RedisUtils.getIncr("global:postid");
		Map<String,String> postMap = new HashMap<String, String>();
		postMap.put("postid", postid+"");
		postMap.put("username", user.getUsername());
		postMap.put("time", RedisUtils.formateDate(new Date().getTime()+""));
		postMap.put("content", content);
		RedisUtils.hmset("post:postid:"+postid, postMap);
		
		String createTime =  new Date().getTime()+"";
		RedisUtils.setKey("post:postid:"+postid+":userid", user.getId());
		RedisUtils.setKey("post:postid:"+postid+":time", createTime);
		RedisUtils.setKey("post:postid:"+postid+":content", content);
		RedisUtils.zadd("post:poser:"+user.getId()+":postid", (double)postid,postid+"");
		RedisUtils.lpush("newpostlist", postid+"");
		response.setContentType("text/json;charset=utf-8");
		 PrintWriter out = response.getWriter();
		 out.print("{\"username\":\""+username+"\",\"content\":\""+content+"\",\"time\":\""+RedisUtils.formateDate(createTime)+"\"}");
		 out.flush();
		 out.close();
	}

}









