package netdisc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;

import netdisc.entity.Rfiles;
import netdisc.entity.Vcatalog;
import netdisc.service.factory.RSFactory;
import netdisc.service.factory.VSFactory;

import com.opensymphony.xwork2.ActionSupport;

public class DownFileAction extends ActionSupport {

	private String filename;
	private String mime;
	private Integer id;
	private InputStream ins;
	
	public InputStream getIns() {
		return ins;
	}

	public String getFilename() {
		return filename;
	}

	public String getMime() {
		return mime;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			Vcatalog vv = VSFactory.getInstance().loadByVid(id);
			if(!vv.getClass().equals("rfile")){
				//this.filename=vv.getTitle();
				this.filename = URLEncoder.encode(vv.getTitle(), "UTF-8");
				this.mime=vv.getCltype().getTitle();
				Rfiles rs = RSFactory.getIntance().findByMd5(vv.getMd5());
				String pathname=rs.getAlltitle();
				String cpath=ServletActionContext.getServletContext().getRealPath("/");
				File f=new File(cpath+"userfiles"+File.separator+pathname);
				this.ins=new FileInputStream(f);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		return super.execute();
	}

	
}
