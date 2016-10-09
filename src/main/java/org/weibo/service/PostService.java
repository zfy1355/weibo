package org.weibo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.weibo.entity.Post;
import org.weibo.util.RedisUtils;

public class PostService extends BaseService{
	
	public  Set<Post> getPosts(String userid,String username){
		Set<String>postIds = RedisUtils.zrange("post:poser:"+userid+":postid",0,-1);
		String id = null;
		Set<Post> posts = new TreeSet<Post>();
		for(Iterator<String> ids = postIds.iterator();ids.hasNext(); ){
			id =ids.next();
			Post post = new Post();
			post.setContent(RedisUtils.getKey("post:postid:"+id+":content"));
			post.setTime(formateDate(RedisUtils.getKey("post:postid:"+id+":time")));
			post.setUsername(username);
			post.setId(id);
			posts.add(post);
		}
		return posts;
		
	}
	
		
	public  Post getPostById(String id,String username) {
		Post post = new Post();
		post.setId(id);
		post.setUsername(username);
		post.setContent(RedisUtils.getKey("post:postid:"+id+":content"));
		post.setTime(formateDate(RedisUtils.getKey("post:postid:"+id+":time")));
		return post;
	}
		

}
