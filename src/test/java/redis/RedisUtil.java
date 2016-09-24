package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class RedisUtil {
	
	private static JedisPool jedisPool;
	
	private RedisUtil(){
	}
	static {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
	    poolConfig.setMaxIdle(5);
	    poolConfig.setMinIdle(1);
	    poolConfig.setTestOnBorrow(true);
	    poolConfig.setNumTestsPerEvictionRun(10);
	    poolConfig.setTimeBetweenEvictionRunsMillis(60000);
	    poolConfig.setMaxWaitMillis(10000);
		
		jedisPool = new JedisPool(poolConfig,"192.168.108.149",6379);
	}
	
	private static void returnResource(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}
	/*发布消息*/
	public static void publish(String channel, String message){
		Jedis jedis = null ;
		try{
			jedis = jedisPool.getResource();
			jedis.publish(channel, message);
		}catch(Exception e){
			System.out.println(e);
		}finally{
				returnResource(jedis);
		}
	};
	/*订阅房间*/
	public static void subscribe(String[] room, JedisPubSub pubSub){
		Jedis jedis = null ;
		try{
			jedis = jedisPool.getResource();
			jedis.subscribe(pubSub , room);
		}catch(Exception e){
			System.out.println(e);
		}finally{
			returnResource(jedis);
		}
	}
}
