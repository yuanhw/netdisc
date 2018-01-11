package netdisc.action;

import netdisc.service.factory.PSFactory;
import netdisc.service.factory.RSFactory;

import com.opensymphony.xwork2.ActionSupport;

public class UpdateVAction extends ActionSupport {
	private String keyword;
	private Integer tid;
	private String result;
	

	public String getResult() {
		return result;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public void setTid(Integer tid) {
		this.tid = tid;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			if(tid==0){
				PSFactory.getInstance().updateSouCount(keyword);
			}else{
				PSFactory.getInstance().updateSouCountT(keyword,tid);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		result="{'rt':true}";
		return super.execute();
	}
	
}
