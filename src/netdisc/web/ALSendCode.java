package netdisc.web;

import netdisc.util.Tools;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class ALSendCode {

	private final static String product = "Dysmsapi";//短信API产品名称
	private final static String domain = "dysmsapi.aliyuncs.com";//短信API产品域名
	//替换成你的AK
	private final static String accessKeyId = "LTAImPwsyRF5NIQT";//你的accessKeyId,参考本文档步骤2
	private final static String accessKeySecret = "A1ZSDccwhvvkvrBIkYNSfKOTdBpCNc";//你的accessKeySecret，参考本文档步骤2
	static{
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
	}
	public static String sendCode(String phone) throws ClientException{
		//初始化ascClient需要的几个参数
		//初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
		accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		 //组装请求对象
		 SendSmsRequest request = new SendSmsRequest();
		 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		 request.setPhoneNumbers(phone);
		 //必填:短信签名-可在短信控制台中找到
		 request.setSignName("OV共享网");
		 //必填:短信模板-可在短信控制台中找到
		 request.setTemplateCode("SMS_77380009");
		 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		 String random=Tools.getRadomFid(6);
		 request.setTemplateParam("{\"vcode\":\""+random+"\"}");
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			return "{'code':'200','obj':'"+random+"'}";
		}
		return null;
	}

}
