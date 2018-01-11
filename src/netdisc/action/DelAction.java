package netdisc.action;

import netdisc.service.factory.DSFactory;

import com.opensymphony.xwork2.ActionSupport;

public class DelAction extends ActionSupport {
	private String cbx;
	private String result;
	
	public String getResult() {
		return result;
	}


	public void setCbx(String cbx) {
		this.cbx = cbx;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String[] strs=cbx.split(",");
		int[] dids=new int[strs.length];
		for(int i=0;i<strs.length;i++){
			dids[i]=Integer.parseInt(strs[i]);
		}
		boolean tag=true;
		try{
			DSFactory.getInstance().delGroupFile(dids);
		}catch(Exception e){
			tag=false;
			e.printStackTrace();
		}
		result="{'rt':"+tag+"}";
		return super.execute();
	}

}
