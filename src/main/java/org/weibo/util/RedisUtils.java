package org.weibo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.weibo.entity.Post;
import org.weibo.entity.User;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

public class RedisUtils {
	static ShardedJedisPool pool;
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(1000*60);
		config.setMaxWaitMillis(1000*10);
		String hostA = "192.168.108.149";
		String hostB = "192.168.108.149";
		int portA = 6379;
		int portB = 6381;
		List<JedisShardInfo> jdsInfoList = new ArrayList<JedisShardInfo>(2);
		JedisShardInfo infoA = new JedisShardInfo(hostA, portA);
		JedisShardInfo infoB = new JedisShardInfo(hostB, portB);
		jdsInfoList.add(infoA);
		jdsInfoList.add(infoB);
		
		pool = new ShardedJedisPool(config, jdsInfoList, Hashing.MURMUR_HASH,
				Sharded.DEFAULT_KEY_TAG_PATTERN);
	}
	
	public static String getKey(String key){
		ShardedJedis jedis =  null;
		try{
			jedis = pool.getResource();
			return jedis.get(key);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static String setKey(String key, String val){
		ShardedJedis jedis =  null;
		try{
			jedis = pool.getResource();
			return jedis.set(key,val);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static Long getIncr(String ids){
		ShardedJedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.incr("global:"+ids);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static Long getList(String keys){
		ShardedJedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.incr("global:"+keys);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static void lpush(String stackName,String value){
		ShardedJedis jedis = null;
		try{
			jedis = pool.getResource();
			jedis.lpush(stackName, value);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static String ltrim(String stackName,long start, long end){
		ShardedJedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.ltrim(stackName, start, end);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static List<String> getNew10UserList(){
		ShardedJedis jedis = null;
		try{
			jedis = pool.getResource();

			return jedis.sort("newuserlist",new SortingParams().by("sore").get("user:userid:*:username").limit(0, 10));
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static String hmset(String key, Map<String, String> map){
		ShardedJedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.hmset(key, map);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static List<String>	hmget(String key, String...fields ){
		ShardedJedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.hmget(key, fields);
		}finally{
			pool.returnResource(jedis);
		}
	}

	public static User  getUserByUsername(String username) {
		String userid = getKey("user:username:"+username+":userid");
		if(StringUtils.isEmpty(userid))
			return null;
		User user = new User();
		user.setFollowerC(Integer.parseInt(getKey("user:userid:"+userid+":followerC")));
		user.setFollowingC(Integer.parseInt(getKey("user:userid:"+userid+":followingC")));
		user.setUsername(username);
		user.setId(userid);
		user.setPassword(getKey("user:userid:"+userid+":password"));
		return user;
	}
	
	

}
