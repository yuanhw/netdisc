package netdisc.action;

import org.apache.struts2.ServletActionContext;

import netdisc.entity.Users;
import netdisc.service.factory.USFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ReturnAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(ServletActionContext.getRequest().getSession().getAttribute("user")!=null){
			ServletActionContext.getRequest().getSession().removeAttribute("user");;
		}
		return super.execute();
	}
	
}
