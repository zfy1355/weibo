package redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

public class RedisTest {
	static ShardedJedisPool pool;
	public static void main(String[] args) {
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
		
		for(int i=0;i<10;i++){
			String key = generateKey();
			ShardedJedis jeds = null;
			
			jeds = pool.getResource();
			
			System.out.println(key + ":" + jeds.getShard(key).getClient().getHost());
			System.out.println(jeds.set(key, "111111111"));
			pool.returnResource(jeds);
		}
	}
	private static int index = 1;
	private static String generateKey() {
		 return String.valueOf(Thread.currentThread().getId())+"_"+(index++);
	}

}
