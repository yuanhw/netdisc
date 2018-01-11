package netdisc.dao.impl;

import netdisc.dao.VtypeDao;
import netdisc.entity.Vtype;
import netdisc.util.HibernateUtil;

public class VtypeDaoImpl implements VtypeDao {

	public VtypeDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vtype findById(int id) {
		// TODO Auto-generated method stub
		return (Vtype) HibernateUtil.getCurrentSession().get(Vtype.class, id);
				
	}

}
