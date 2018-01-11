package netdisc.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import netdisc.entity.Play;
import netdisc.entity.Users;
import netdisc.service.factory.PSFactory;

import com.opensymphony.xwork2.ActionSupport;

public class OpenedListAction extends ActionSupport {
	private List<Play> mylist;

	public List<Play> getMylist() {
		return mylist;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			Users u=(Users) ServletActionContext.getRequest().getSession().getAttribute("user");
			mylist=PSFactory.getInstance().findByUName(u.getUsername());
		}catch(Exception e){
			return ERROR;
		}
		return super.execute();
	}
	

}
