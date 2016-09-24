package redis.cli;

import java.util.List;

import redis.clients.jedis.Jedis;

public class TestCli {
	private static String host = "127.0.0.1";
	private static int port = 6379;
	Jedis jedis = new Jedis(host, port);
	public static void main(String[] args) {
		
	}
	
//	@Test
	public void testSelect(){
		String res = jedis.select(15);
		res = jedis.select(0);
//		res = jedis.select(-1);
//		res = jedis.select(16);
		System.out.println(res);
	}
	
//	@Test
	public void testGetSet(){
		jedis.set("message", "hello world!");
		String msg = jedis.get("message");
		System.out.println(msg);
		jedis.getSet("message", "nice!");
		System.out.println(jedis.get("message"));
		jedis.getSet("msg", "abc");
		System.out.println("test getSet msg:" + jedis.get("msg"));
		
	}
//	@Test
	public void rpush() {
		jedis.rpush("alphabet", "a","b","c");
		List<String> s = jedis.lrange("alphabet", 0, 10);
		for(String dString : s)
			System.out.println(dString);
	}
	
//	@Test
	public void hset(){
		jedis.hset("book", "name", "redis in action");
		jedis.hset("book", "author", "josiah L");
		jedis.hset("book", "price", "50");
		
		String s = jedis.hget("book", "name");
		System.out.println("hset and get :" +s);
	}
	
	
//	@Test
	public void del(){
		jedis.set("testdel", "1");
		System.out.println("wether exist testdel:" +jedis.exists("testdel"));
		Long res = jedis.del("testdel");
		System.out.println("del testdel res:"+res);
		System.out.println("wether exist testdel:" +jedis.exists("testdel"));
		
	}
	

}
