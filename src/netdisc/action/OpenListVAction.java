package netdisc.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import netdisc.entity.Play;
import netdisc.entity.Users;
import netdisc.entity.Vcatalog;
import netdisc.entity.Vtype;
import netdisc.service.PlayService;
import netdisc.service.factory.PSFactory;
import netdisc.service.factory.USFactory;
import netdisc.service.factory.VSFactory;

import com.opensymphony.xwork2.ActionSupport;

public class OpenListVAction extends ActionSupport {

	private Integer totalRow;
	private Integer totalPage;
	private Integer currentPage=1;
	private List<Play> mylist;
	private List<Play> rslist;
	private String keyword;
	private Integer typeid;
	
	
	public List<Play> getRslist() {
		return rslist;
	}
	public String getKeyword() {
		return keyword;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public List<Play> getMylist() {
		return mylist;
	}
	public Integer getTotalRow() {
		return totalRow;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			PlayService ps = PSFactory.getInstance();
			this.rslist=ps.findTopList();
			if(this.keyword==null){
				this.keyword="";
			}
			if(this.typeid==null||this.typeid==0){
				this.totalRow=ps.totalCount(keyword);
				this.totalPage=(totalRow%16==0)?totalRow/16:(totalRow/16+1);
				if(currentPage<1||currentPage>totalPage){
					currentPage=1;
				}
				int first=(this.currentPage-1)*16;
				this.mylist=ps.findByKeyWord(this.keyword,first,16);
			}else{
				this.totalRow=ps.totalCount2(keyword, typeid);
				this.totalPage=(totalRow%16==0)?totalRow/16:(totalRow/16+1);
				if(currentPage<1||currentPage>totalPage){
					currentPage=1;
				}
				int first=(this.currentPage-1)*16;
				Vtype v=new Vtype();
				v.setId(typeid);
				this.mylist=ps.findByKeyWordT(this.keyword,v,first,16);
			}
		}catch(Exception e){
			e.printStackTrace();
			return INPUT;
		}
		return super.execute();
	}
	
}
