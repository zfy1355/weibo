package org.weibo.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseService {
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
