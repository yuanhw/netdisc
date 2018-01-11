package netdisc.action;

import org.apache.struts2.ServletActionContext;

import netdisc.entity.Users;
import netdisc.service.factory.USFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RegAction extends ActionSupport implements ModelDriven<Users>{

	Users users=new Users();
	
	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return users;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		USFactory.getInstance().reg(users);
		ServletActionContext.getRequest().getSession().setAttribute("user", users);
		return super.execute();
	}
	
}
