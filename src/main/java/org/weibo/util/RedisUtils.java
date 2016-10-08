package org.weibo.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
/*		String hostA = "192.168.108.149";
		String hostB = "192.168.108.149";
		int portA = 6379;
		int portB = 6381;*/
		String hostA = "127.0.0.1";
		String hostB = "127.0.0.1";
		int portA = 6379;
		int portB = 6379;
		List<JedisShardInfo> jdsInfoList = new ArrayList<JedisShardInfo>(2);
		JedisShardInfo infoA = new JedisShardInfo(hostA, portA);
		JedisShardInfo infoB = new JedisShardInfo(hostB, portB);
		jdsInfoList.add(infoA);
		jdsInfoList.add(infoB);
		
		pool = new ShardedJedisPool(config, jdsInfoList, Hashing.MURMUR_HASH,
				Sharded.DEFAULT_KEY_TAG_PATTERN);
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
	
	public static List getNew50PostList() {
		ShardedJedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.sort("newpostlist",new SortingParams().by("sore").limit(0, 50));
		} finally{
			pool.returnResource(jedis);
		}
	}
	
	
	public static boolean existUser(String username){
		ShardedJedis jedis =  null;
		try{
			jedis = pool.getResource();
			return jedis.exists("user:username:"+username+":userid");
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static boolean exist(String key){
		ShardedJedis jedis =  null;
		try{
			jedis = pool.getResource();
			return jedis.exists(key);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	
	public static void follow(String username, String followingId) {
		sadd("user:following:username:"+username, followingId);
	}
	public static void unfollow(String username, String followingId) {
		srem("user:following:username:"+username, followingId);
	}
	
	public static boolean getIsFollowing(String username, String id) {
		return sismember("user:following:username:"+username,id);
	}

	public static Long lpop(String key,String value){
		ShardedJedis jedis =  null;
		try{
			jedis = pool.getResource();
			return jedis.lrem(key, 1, value);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static Boolean sismember(String key,String value){
		ShardedJedis jedis =  null;
		try{
			jedis = pool.getResource();
			return jedis.sismember(key, value);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static Set<String> smembers(String key){
		ShardedJedis jedis =  null;
		try{
			jedis = pool.getResource();
			return jedis.smembers(key);
		}finally{
			pool.returnResource(jedis);
		}
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
	
	public static String hmset(String key, Map<String, String> map){
		ShardedJedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.hmset(key, map);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static Long sadd(String key, String val){
		ShardedJedis jedis =  null;
		try{
			jedis = pool.getResource();
			return jedis.sadd(key, val);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static Long zadd(String key, double score, String val){
		ShardedJedis jedis =  null;
		try{
			jedis = pool.getResource();
			return jedis.zadd(key, score, val);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	
	public static Set<String> zrange(String key,long start, long end){
		ShardedJedis jedis =  null;
		try{
			jedis = pool.getResource();
			return jedis.zrange(key, start,end);
		}finally{
			pool.returnResource(jedis);
		}
	}
	
	public static Long srem(String key, String val){
		ShardedJedis jedis =  null;
		try{
			jedis = pool.getResource();
			return jedis.srem(key, val);
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

	





}
