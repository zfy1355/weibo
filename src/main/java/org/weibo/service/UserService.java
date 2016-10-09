package org.weibo.service;

import org.apache.commons.lang.StringUtils;
import org.weibo.entity.User;
import org.weibo.util.RedisUtils;

public class UserService extends BaseService{
	PostService postService = new PostService();
	
	public  User  getUserByUsername(String username) {
		String userid = RedisUtils.getKey("user:username:"+username+":userid");
		if(StringUtils.isEmpty(userid))
			return null;
		User user = new User();
		user.setFollowerC(Integer.parseInt(RedisUtils.getKey("user:userid:"+userid+":followerC")==null?"0":RedisUtils.getKey("user:userid:"+userid+":followerC")));
		user.setFollowingC(Integer.parseInt(RedisUtils.getKey("user:userid:"+userid+":followingC")==null?"0":RedisUtils.getKey("user:userid:"+userid+":followingC")));
		user.setUsername(username);
		user.setId(userid);
		user.setPassword(RedisUtils.getKey("user:userid:"+userid+":password"));
		user.setPosts(postService.getPosts(userid,username));
		return user;
	}
	
	public  User  getUserById(String userid) {
		if(StringUtils.isEmpty(userid))
			return null;
		User user = new User();
		user.setFollowerC(Integer.parseInt(RedisUtils.getKey("user:userid:"+userid+":followerC")==null?"0":RedisUtils.getKey("user:userid:"+userid+":followerC")));
		user.setFollowingC(Integer.parseInt(RedisUtils.getKey("user:userid:"+userid+":followingC")==null?"0":RedisUtils.getKey("user:userid:"+userid+":followingC")));
		user.setUsername(RedisUtils.getKey("user:userid:"+userid+":username"));
		user.setId(userid);
		user.setPassword(RedisUtils.getKey("user:userid:"+userid+":password"));
		user.setPosts(postService.getPosts(userid,user.getUsername()));
		return user;
	}
	
	public  void registerUser(String username, String password) {
		Long id = RedisUtils.getIncr("userid");
		RedisUtils.setKey("user:userid:"+ id+":username",username);
		RedisUtils.setKey("user:userid:"+ id+":password",password);
		RedisUtils.setKey("user:username:"+username+":userid", id+"");
		RedisUtils.lpush("newuserlist", id+"");
	}
	
	public int getfollowerCount(String userid){
		String c = RedisUtils.getKey("user:userid:"+userid+":followingC");
		return Integer.parseInt(c == null ? "0" : c );
	}
	
	public int getfollowingCount(String userid){
		String c = RedisUtils.getKey("user:userid:"+userid+":followerC");
		return Integer.parseInt(c == null ? "0" : c  );
	}
	
}
