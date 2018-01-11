package netdisc.service.impl;

import netdisc.dao.VtypeDao;
import netdisc.dao.impl.VtypeDaoImpl;
import netdisc.entity.Vtype;
import netdisc.service.VtypeService;
import netdisc.util.HibernateUtil;
import netdisc.util.Tools;

public class VtypeServiceImpl implements VtypeService{

	private VtypeDao vdao;
	public VtypeServiceImpl() {
		// TODO Auto-generated constructor stub
		this.vdao=new VtypeDaoImpl();
	}

	@Override
	public Vtype findById(int id) {
		// TODO Auto-generated method stub
		Vtype v=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			v=this.vdao.findById(id);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return v;
	}

}
