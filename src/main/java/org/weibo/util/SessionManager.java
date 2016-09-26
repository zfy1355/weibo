package org.weibo.util;

import java.util.concurrent.ConcurrentHashMap;

import org.weibo.entity.User;

public class SessionManager {
	private static final ConcurrentHashMap<String, User> USERS = new ConcurrentHashMap<String, User>();
	
	public static synchronized void addSession(String key , User user){
		USERS.put(key, user);
	}
	
	public  static User  popSession(String key){
		return USERS.remove(key);
	}
	
	public  static User  getUser(String key){
		return USERS.get(key);
	}

	public  static void  addUser(String id){
		User user = new User();
		user.setId(id);
		user.setUsername(RedisUtils.getKey("user:userid:"+id+":username"));
		user.setPassword(RedisUtils.getKey("user:userid:"+id+":password"));
		addSession(user.getUsername(), user);
	}
}
