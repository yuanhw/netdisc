package netdisc.dao.impl;

import java.util.List;

import netdisc.dao.CltypeDao;
import netdisc.entity.Cltype;
import netdisc.util.HibernateUtil;

public class CltypeDaoImpl implements CltypeDao {

	public CltypeDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cltype findByMime(String mime) {
		// TODO Auto-generated method stub
		String sql="from Cltype u where u.title=:title";
		@SuppressWarnings("unchecked")
		List<Cltype> list=HibernateUtil.getCurrentSession().createQuery(sql).setString("title", mime).list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Cltype doCreate(String mime) {
		// TODO Auto-generated method stub
		Cltype c=new Cltype();
		c.setTitle(mime);
		HibernateUtil.getCurrentSession().save(c);
		return c;
	}

}
