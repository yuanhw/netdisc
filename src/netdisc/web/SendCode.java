package netdisc.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class SendCode {
	private static  String SERVER_URL="https://api.netease.im/sms/sendcode.action";
	//网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
	private static  String APP_KEY="2e1c466cae638c8b6005dee0a3733b92";
	//网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
	private static  String APP_SECRET="dd68eb2e699c";
	//随机数
	private static  String NONCE="765489";
	//短信模板ID
	private static String TEMPLATEID="3054747";
	//验证码长度，范围4～10，默认为4
	private static String CODELEN="6";
	
	public static void init(String server_url,String app_key,String app_secret,String templateid){
		SERVER_URL=server_url;
		APP_KEY=app_key;
		APP_SECRET=app_secret;
		TEMPLATEID=templateid;
	}
	
	public static String sendCode(String mobile) throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(SERVER_URL);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        /*
         * 参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
         */
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

        // 设置请求的header
        httpPost.addHeader("AppKey", APP_KEY);
        httpPost.addHeader("Nonce", NONCE);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        /*
         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
         */
        nvps.add(new BasicNameValuePair("templateid", TEMPLATEID));
        nvps.add(new BasicNameValuePair("mobile", mobile));
        nvps.add(new BasicNameValuePair("codeLen", CODELEN));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        /*
         * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500
         * 2.具体的code有问题的可以参考官网的Code状态表
         */
        return EntityUtils.toString(response.getEntity(), "utf-8");
	}
}
