package redis.publishandsubscribe;

import java.util.Date;

import redis.RedisUtil;

/**   
* @Title: ChatRoom.java 
* @Package redis.publishandsubscribe 
* @author zfy1355_gmail_com   
* @date 2016年3月23日 上午10:08:14 
* @version V1.0   
* @desc 聊天室
*/
public class ChatRoom {
	public String room;
	public void setRoom(String client){
		this.room = client;
	}
	/*发布消息*/
	public void publish(String message){
		 RedisUtil.publish(room, message);
	}
	
	public static void main(String[] args) throws InterruptedException {
		ChatRoom room = new ChatRoom();
		room.setRoom("Bob::live::room");
		while(true){
			Thread.sleep(1000);
			room.publish("hello"+new Date());
		}
	}

}
