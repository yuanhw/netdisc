package netdisc.action;

import netdisc.service.factory.RSFactory;

import com.opensymphony.xwork2.ActionSupport;

public class TestFileAction extends ActionSupport {
	private String md5;
	private String result;
	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getResult() {
		return result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		boolean tag=RSFactory.getIntance().isHaveMd5(md5);
		result="{rt:"+tag+"}";
		return super.execute();
	}
	
}
