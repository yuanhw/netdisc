package netdisc.action;

import java.util.ArrayList;
import java.util.List;

import netdisc.entity.Users;
import netdisc.entity.Vcatalog;
import netdisc.service.factory.VSFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MainAction extends ActionSupport{
	private List<Vcatalog> list;
	private Integer pid;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public List<Vcatalog> getList() {
		return list;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			Users u=(Users) ActionContext.getContext().getSession().get("user");
			if(pid!=null){
				list=VSFactory.getInstance().loadListByVid(u.getId(), pid);
			}else{
				list=VSFactory.getInstance().loadInitList(u.getId());
			}
		}catch(Exception e){
			return INPUT;
		}
		return super.execute();
	}
	
	public String loadPreList(){
		if(pid==null)
			return INPUT;
		try{
			Users u=(Users) ActionContext.getContext().getSession().get("user");
			Vcatalog v = VSFactory.getInstance().loadByVid(pid);
			if(v!=null){
				Integer i = v.getPreid();
				if(i!=null){
					list=VSFactory.getInstance().loadListByVid(u.getId(), i);
					pid=i;
				}else{
					return INPUT;
				}
			}else{
				return INPUT;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
}
