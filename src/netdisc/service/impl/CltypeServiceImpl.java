package netdisc.service.impl;

import netdisc.dao.CltypeDao;
import netdisc.dao.impl.CltypeDaoImpl;
import netdisc.entity.Cltype;
import netdisc.service.CltypeService;
import netdisc.util.HibernateUtil;

public class CltypeServiceImpl implements CltypeService {
	private CltypeDao cdao;
	
	public CltypeServiceImpl() {
		cdao=new CltypeDaoImpl();
	}

	@Override
	public Cltype findByMime(String mime) {
		// TODO Auto-generated method stub
		Cltype c=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			c=this.cdao.findByMime(mime);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return c;
	}

	@Override
	public Cltype addType(String mime) {
		// TODO Auto-generated method stub
		Cltype c=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			c=this.cdao.doCreate(mime);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return c;
	}

}
