package redis.publishandsubscribe;

import redis.RedisUtil;
import redis.clients.jedis.JedisPubSub;

public class ChatSubscribe  extends JedisPubSub{
	/*成员名字*/
	private String clientName;
	/*房间名*/
	private String[] room;
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String[] getRoom() {
		return room;
	}

	public void setRoom(String[] room) {
		this.room = room;
	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		//do nothing
	}
	
	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		RedisUtil.publish( channel,clientName+ "进入房间");
	}
	
	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
	}
	
	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
	}
	
	@Override
	public void onPMessage(String pattern, String channel, String message) {
	}
	
	@Override
	public void onMessage(String channel, String message) {
		System.out.println("收到来自"+channel+"房间的消息："+message);
	}
	@Override
	public void unsubscribe(String... channels) {
		for(String c : channels)
			RedisUtil.publish(c, clientName+"离开房间");
		super.unsubscribe(channels);
	}

}
