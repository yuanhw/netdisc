package netdisc.util;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;


public class UpFileUtil {
	private static String basepath;
	
	public static String getBasepath() {
		return basepath;
	}
	public static void setBasepath(String basepath) {
		UpFileUtil.basepath = basepath;
	}
	public static boolean upfile(InputStream input,OutputStream out) throws Exception{
		boolean tag = true;
		byte data[]=new byte[4096];
		try{
			int temp=0;
			while((temp=input.read(data))!=-1){
				out.write(data);
			}
		}catch(Exception e){
			throw e;
		}finally{
			out.close();
			input.close();
		}
		return tag;
	}
	public static boolean delFile(String filepath) throws Exception{
		File file=new File(filepath);
		return file.delete();
	}
}
