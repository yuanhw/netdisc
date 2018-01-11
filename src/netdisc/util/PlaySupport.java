package netdisc.util;

import java.util.ArrayList;
import java.util.List;

public class PlaySupport {
	private static List<String> list=new ArrayList<>();
	static {
		list.add("flv");
		list.add("f4v");
		list.add("mp4");
		list.add("ogg");
	}
	public static boolean isSupport(String str){
		boolean tag=false;
		for(String item : list){
			if(str.endsWith(item)){
				return true;
			}
		}
		return tag;
	}
}
