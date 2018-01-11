package netdisc.action;

import javax.servlet.http.HttpServletRequest;

import netdisc.service.factory.USFactory;
import netdisc.web.ALSendCode;
import netdisc.web.SendCode;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class CheckAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest req;
	private String result;

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String uinfo=req.getParameter("uinfo");
		boolean tag=false;
		try{
			tag=USFactory.getInstance().checkUserName(uinfo);
		}catch(Exception e){
			StackTraceElement[] cc = e.getStackTrace();
			for(StackTraceElement t : cc){
				System.out.println(t.toString());
			}
		}
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("rt", tag);
		result = jsonObj.toString();
		return super.execute();
	}

	public String checkPhone() throws Exception{
		String phone=req.getParameter("uinfo");
		boolean tag=USFactory.getInstance().checkPhone(phone);
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("rt", tag);
		result = jsonObj.toString();
		return SUCCESS;
	}
	
	public String sendVCode(){
		String phone=req.getParameter("uinfo");
		try {
			String jsobj = ALSendCode.sendCode(phone);
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("rt", JSONObject.parse(jsobj));
			result = jsonObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.req=arg0;
	}
	
}
