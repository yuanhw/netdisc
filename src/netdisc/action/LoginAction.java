package netdisc.action;

import javax.servlet.http.HttpServletRequest;

import netdisc.entity.Users;
import netdisc.service.factory.USFactory;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest req;
	private String result;

	public String getResult() {
		return result;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.req=arg0;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String uinfo=req.getParameter("uinfo");
		String password=req.getParameter("password");
		boolean tag=false;
		try{
			Users u=USFactory.getInstance().findByUserName(uinfo);
			if(u!=null&&u.getPassword().equals(password)){
				tag=true;
				req.getSession().setAttribute("user", u);
			}else{
				Users u1=USFactory.getInstance().findByPhone(uinfo);
				if(u1!=null&&u1.getPassword().equals(password)){
					tag=true;
					req.getSession().setAttribute("user", u1);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		JSONObject j=new JSONObject();
		j.put("rt", tag);
		result=j.toString();
		return super.execute();
	}
	
	
	
}
