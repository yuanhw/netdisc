package netdisc.action;

import netdisc.service.factory.PSFactory;

import com.opensymphony.xwork2.ActionSupport;

public class DelPlayAction extends ActionSupport {

	private String ids;
	private String result;
	
	public String getResult() {
		return result;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		boolean tag=true;
		try{
			String[] idss=ids.split(",");
			System.out.println(ids);
			int[] its=new int[idss.length];
			for(int i=0;i<idss.length;i++){
				its[i]=Integer.parseInt(idss[i]);
			}
			PSFactory.getInstance().delGroupPlays(its);
		}catch(Exception e){
			tag=false;
		}
		this.result="{'rt':"+tag+"}";
		return super.execute();
	}
	
}
