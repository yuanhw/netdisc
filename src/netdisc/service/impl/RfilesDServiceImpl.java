package netdisc.service.impl;

import netdisc.dao.RfilesDao;
import netdisc.dao.impl.RfilesDaoImpl;
import netdisc.entity.Rfiles;
import netdisc.service.RfilesDService;
import netdisc.util.HibernateUtil;

public class RfilesDServiceImpl implements RfilesDService{

	private RfilesDao rd;
	public RfilesDServiceImpl() {
		// TODO Auto-generated constructor stub
		rd=new RfilesDaoImpl();
	}

	@Override
	public boolean isHaveMd5(String md5) {
		// TODO Auto-generated method stub
		boolean tag=false;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			tag=this.rd.isHaveMd5(md5);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return tag;
	}

	@Override
	public Rfiles findByMd5(String md5) {
		// TODO Auto-generated method stub
		Rfiles r=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			r=this.rd.findByMd5(md5);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return r;
	}

}
