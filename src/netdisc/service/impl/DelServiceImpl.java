package netdisc.service.impl;

import java.util.List;

import netdisc.dao.RfilesDao;
import netdisc.dao.VcatalogDao;
import netdisc.dao.impl.RfilesDaoImpl;
import netdisc.dao.impl.VcatalogDaoImpl;
import netdisc.entity.Vcatalog;
import netdisc.service.DelService;
import netdisc.util.HibernateUtil;
import netdisc.util.Tools;

public class DelServiceImpl implements DelService{

	private RfilesDao rdao;
	private VcatalogDao vdao;
	
	public DelServiceImpl() {
		rdao=new RfilesDaoImpl();
		vdao=new VcatalogDaoImpl();
	}

	@Override
	public boolean delGroupFile(int[] ids) throws Exception {
		// TODO Auto-generated method stub
		boolean tag=true;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			this.delList(ids);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			tag=false;
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return tag;
	}

	private void delList(int[] ids) throws Exception {
		for(int i=0;i<ids.length;i++){
			Vcatalog v = this.vdao.findByVid(ids[i]);
			if(v.getCltype().getTitle().equals("vfile")){
				List<Vcatalog> list=this.vdao.findByPid(v.getId());
				if(list.size()>0){
					int[] idss=new int[list.size()];
					for(int j=0;j<list.size();j++){
						idss[j]=list.get(j).getId();
					}
					this.delList(idss);
				}
				this.vdao.doDelById(v.getId());
			}else{
				this.rdao.updateRefCount(v.getMd5(), -1);
				this.vdao.doDelById(v.getId());
			}
		}
	}
}
