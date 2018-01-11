package netdisc.action;

import org.apache.struts2.ServletActionContext;

import netdisc.entity.Play;
import netdisc.entity.Rfiles;
import netdisc.entity.Vcatalog;
import netdisc.service.PlayService;
import netdisc.service.factory.PSFactory;
import netdisc.service.factory.RSFactory;
import netdisc.service.factory.VSFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class VplainAction extends ActionSupport {

	private Integer id;
	private String pname;
	private String vname;

	public String getPname() {
		return pname;
	}

	public String getVname() {
		return vname;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(ServletActionContext.getRequest().getSession().getAttribute("user")==null){
			return INPUT;
		}
		try{
			Vcatalog v = VSFactory.getInstance().loadByVid(id);
			Rfiles r = RSFactory.getIntance().findByMd5(v.getMd5());
			this.pname=r.getAlltitle();
			this.vname=this.unsuffix(v.getTitle());
		}catch(Exception e){
			e.printStackTrace();
			return INPUT;
		}
		return super.execute();
	}
	private String unsuffix(String title) {
		// TODO Auto-generated method stub
		return title.substring(0,title.lastIndexOf("."));
	}
	public String commPlay(){
		try{
			PlayService pss = PSFactory.getInstance();
			Play play = pss.findById(id);
			pss.updatePlayCount(play.getId());
			Rfiles r = RSFactory.getIntance().findByMd5(play.getMd5());
			this.pname=r.getAlltitle();
			this.vname=play.getTitle();
		}catch(Exception e){
			return INPUT;
		}
		return SUCCESS;
	}
}
