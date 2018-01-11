package netdisc.dao.impl;

import netdisc.dao.RfilesDao;
import netdisc.entity.Rfiles;
import netdisc.util.HibernateUtil;
import netdisc.util.UpFileUtil;

public class RfilesDaoImpl implements RfilesDao {

	public RfilesDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCreate(Rfiles rf) {
		// TODO Auto-generated method stub
		HibernateUtil.getCurrentSession().save(rf);
	}

	@Override
	public boolean isHaveMd5(String md5) {
		// TODO Auto-generated method stub
		return HibernateUtil.getCurrentSession().get(Rfiles.class, md5)!=null;
	}

	@Override
	public void updateRefCount(String md5, int count) throws Exception {
		// TODO Auto-generated method stub
		Rfiles rf=(Rfiles) HibernateUtil.getCurrentSession().get(Rfiles.class, md5);
		int i=rf.getRefcount()+count;
		if(i<1){
			String filename=UpFileUtil.getBasepath()+rf.getAlltitle();
			HibernateUtil.getCurrentSession().delete(rf);
			UpFileUtil.delFile(filename);
		}else{
			rf.setRefcount(i);
		}
	}

	@Override
	public void doDelByMd5(String md5) {
		// TODO Auto-generated method stub
		Rfiles r=new Rfiles();
		r.setMd5(md5);
		HibernateUtil.getCurrentSession().delete(r);
	}

	@Override
	public Rfiles findByMd5(String md5) {
		// TODO Auto-generated method stub
		return (Rfiles) HibernateUtil.getCurrentSession().get(Rfiles.class, md5);
	}
	
	

}
