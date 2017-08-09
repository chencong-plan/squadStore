package cc.ccoder.squadStore.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static String getNowTime(){
		Date currentTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
		return dateFormat.format(currentTime);
		
	}
	public static String getNowTime(String format){
		Date currentTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(currentTime);
	}
}
