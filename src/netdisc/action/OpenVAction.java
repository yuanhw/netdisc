package netdisc.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import netdisc.entity.Play;
import netdisc.entity.Users;
import netdisc.entity.Vcatalog;
import netdisc.entity.Vtype;
import netdisc.service.factory.PSFactory;
import netdisc.service.factory.USFactory;
import netdisc.service.factory.VSFactory;

import com.opensymphony.xwork2.ActionSupport;

public class OpenVAction extends ActionSupport {

	private Integer vid;
	private String title;
	private int typeid;
	private String result;
	
	public String getResult() {
		return result;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info="发布成功！";
		Users u=(Users) ServletActionContext.getRequest().getSession().getAttribute("user");
		try{
			Vcatalog v = VSFactory.getInstance().loadByVid(vid);
			boolean tag=PSFactory.getInstance().checkOpen(v.getMd5(), u.getUsername());
			if(!tag){
				Vtype type=new Vtype();
				type.setId(this.typeid);
				Play play=new Play(this.title,type,u.getUsername(),new Date(), v.getMd5());
				PSFactory.getInstance().doCreate(play);
			}else{
				info="发布失败，重复发布！";
			}
		}catch(Exception e){
			info="未知错误！";
			e.printStackTrace();
		}finally{
			this.result="{'rt':'"+info+"'}";
		}
		return super.execute();
	}
	
}
