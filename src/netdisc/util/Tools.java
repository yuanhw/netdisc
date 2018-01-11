package netdisc.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Tools {
	public static String getIpTime() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date())+getRadomFid(3);
	}
	public static String getRadomFid(int n) {
		Random random = new Random();
		String result="";
		for(int i=0;i<n;i++){
			result+=random.nextInt(10);
		}
		return result;
	}
}
