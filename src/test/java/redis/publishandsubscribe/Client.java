package redis.publishandsubscribe;

import java.util.Scanner;

import redis.RedisUtil;

/**   
* @Title: Client.java 
* @Package redis.publishandsubscribe 
* @author zfy1355_gmail_com   
* @date 2016年3月23日 上午10:22:12 
* @version V1.0   
*/
public class Client {
	private String name ;
	private ChatSubscribe roomSubListerner;
	
	public Client(){
		roomSubListerner = new ChatSubscribe();
	}
	
	public void setName(String name){
		this.name =name;
	}
	public String getName(){
		return name;
	}
	/* 进入房间*/
	public void subscribe(final String[] room){
		ChatSubscribe roomSub = roomSubListerner;
		roomSub.setClientName(name);
		roomSub.setRoom(room);
		RedisUtil.subscribe(room, roomSub);
	}
	/* 退出房间*/
	public void unSubscribe(final String[] room){
		roomSubListerner.unsubscribe(room);
	}
	/*说话*/
	public void say(final String room,String message){
		RedisUtil.publish(room, name+" say:"+message);
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		final Client client = new Client();
		client.setName("Mark");
		final String[] rooms = {"movie::live::room"};
		new Thread(new Runnable() {
			@Override
			public void run() {
//				String[] rooms = {"peter::live::room","Bob::live::room"};
				client.subscribe(rooms);
			}
		}).start();
		Thread.sleep(3000);
		while(true){
			System.out.print("say something:");
			Scanner scanner = new Scanner(System.in);
			String message = scanner.nextLine(); 
			if("quit".equals(message)){
				break;
			}else{
				client.say(rooms[0], message);
				System.out.println();
			}
		}
		String[] unSubRoom ={"movie::live::room"}; 
		client.unSubscribe(unSubRoom);
	}

}
