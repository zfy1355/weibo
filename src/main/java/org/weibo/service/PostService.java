package org.weibo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.weibo.entity.Post;
import org.weibo.util.RedisUtils;

public class PostService extends BaseService{
	UserService userService = new UserService();
	
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
	
		
	public  Post getPostById(String id) {
		Post post = new Post();
		post.setId(id);
		post.setUsername(userService.getUserById(RedisUtils.getKey("post:postid:"+id+":userid")).getUsername());
		post.setContent(RedisUtils.getKey("post:postid:"+id+":content"));
		post.setTime(formateDate(RedisUtils.getKey("post:postid:"+id+":time")));
		return post;
	}
	
		
	public  String formateDate(String time){
		long l = Long.parseLong(time);
		long second = (new Date().getTime() - l)/1000;
		String interval = null;
		if(second == 0){
			interval = "刚刚";
		} else if(second <60){
			interval = second + "秒以前";
		} else if(second >=60 && second <60*60){
			interval = second/60 + "分钟前";
		} else if(second >= 60*60 && second <60 * 60 *24){
			long hour = (second /60)/60;
			if(hour <=3){
				interval = hour + "小时前";
			}else {
				interval = "今天" + formateDate(l,"HH:mm");
			}
		} else if (second >=60 * 60 * 24 && second <= 60 * 60 * 24 *2){
			interval = "昨天" + formateDate(l, "HH:mm");
		} else if (second >= 60 * 60 * 24 *2 && second <=60*60*24*7){
			long day = ((second /60)/60 /24);
			interval = day + "天前";
		} else if(second <=60 * 60 *24 *365 && second >=60*60 * 24 *7){
			interval = formateDate(l , "MM-dd HH:mm");
		} else if(second >= 60*60 * 24* 365){
			interval = formateDate(l, "yyyy-MM-dd HH:mm");
		}
		return interval;
	}
	
	
	public  String formateDate(long time, String formateType){
		SimpleDateFormat sdf = new SimpleDateFormat(formateType);
		return sdf.format(time);
	}


}
