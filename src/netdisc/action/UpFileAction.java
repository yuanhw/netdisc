package netdisc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import netdisc.entity.Cltype;
import netdisc.entity.Rfiles;
import netdisc.entity.Users;
import netdisc.entity.Vcatalog;
import netdisc.service.factory.CSFactory;
import netdisc.service.factory.VSFactory;
import netdisc.util.Tools;
import netdisc.util.UpFileUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UpFileAction extends ActionSupport {

	private File myfile;
	private String myfileFileName;
	private String myfileContentType;
	private double myfileSize;
	private String result;
	private String md5;
	private String pid;
	
	public void setMyfileSize(double myfileSize) {
		this.myfileSize = myfileSize;
	}
	public void setPid(String pid) {
		this.pid=pid;
	}
	public String getResult() {
		return result;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public void setMyfile(File myfile) {
		this.myfile = myfile;
	}
	public void setMyfileFileName(String myfileFileName) {
		this.myfileFileName = myfileFileName;
	}
	public void setMyfileContentType(String myfileContentType) {
		this.myfileContentType = myfileContentType;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		boolean tag=false;
		try{
			Vcatalog v=this.assemb();
			v.setSize(myfile.length()*1.0);
			String allname=Tools.getIpTime()+suffix(v.getTitle());
			VSFactory.getInstance().addFile(v,new Rfiles(v.getMd5(),allname,1));
			FileInputStream fin=new FileInputStream(myfile);
			String cpath=ServletActionContext.getServletContext().getRealPath("/");
			File f=new File(cpath+"userfiles"+File.separator+allname);
			FileOutputStream fout=new FileOutputStream(f);
			UpFileUtil.upfile(fin, fout);
			tag=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			result="{'rt':"+tag+"}";
		}
		return super.execute();
	}
	private String suffix(String title) {
		// TODO Auto-generated method stub
		return title.substring(title.lastIndexOf("."));
	}
	public String md5HaveFile(){
		boolean tag=false;
		try{
			Vcatalog v=this.assemb();
			Cltype c=VSFactory.getInstance().findCltypeBymd5(v.getMd5());
			v.setCltype(c);
			VSFactory.getInstance().addFileHaveMd5(v);
			tag=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			result="{'rt':"+tag+"}";
		}
		return SUCCESS;
	}
	private Vcatalog assemb(){
		Cltype ftype = CSFactory.getInstance().findByMime(this.myfileContentType);
		if(ftype==null){
			ftype=CSFactory.getInstance().addType(this.myfileContentType);
		}
		Users u=(Users) ActionContext.getContext().getSession().get("user");
		Vcatalog v=new Vcatalog();
		v.setAltertime(new Date());
		v.setCltype(ftype);
		v.setMd5(md5);
		v.setSize(this.myfileSize);
		v.setTitle(this.myfileFileName);
		v.setUid(u.getId());
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
