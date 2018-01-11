package netdisc.action;

import java.util.Date;

import netdisc.entity.Cltype;
import netdisc.entity.Users;
import netdisc.entity.Vcatalog;
import netdisc.service.VcatalogService;
import netdisc.service.factory.CSFactory;
import netdisc.service.factory.VSFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AddFAction extends ActionSupport {

	private String fname;
	private String pid;
	private String result;
	
	
	public String getResult() {
		return result;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public AddFAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		boolean tag=false;
		
		try{
			Users u=(Users) ActionContext.getContext().getSession().get("user");
			Cltype c = CSFactory.getInstance().findByMime("vfile");
			if(pid!=null&&!pid.equals("")){
				int item=Integer.parseInt(pid);
				tag=VSFactory.getInstance().isHaveF(u.getId(), item, this.fname, c);
			}else{
				VcatalogService vss = VSFactory.getInstance();
				tag=vss.isHaveF(u.getId(),null, this.fname, c);
			}
			if(!tag){
				Vcatalog v=this.asseam();
				v.setUid(u.getId());
				v.setCltype(c);
				VSFactory.getInstance().addF(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			result="{'rt':"+tag+"}";
		}
		return SUCCESS;
	}
	private Vcatalog asseam(){
		Vcatalog v=new Vcatalog();
		v.setTitle(this.fname);
		v.setAltertime(new Date());
		if(pid!=null&&!pid.equals("")){
			int item=Integer.parseInt(pid);
			Vcatalog c = VSFactory.getInstance().loadByVid(item);
			v.setPreid(item);
			v.setLayer(c.getLayer()+1);
		}else{
			v.setPreid(null);
			v.setLayer(1);
		}
		return v;
	}
}
