package netdisc.dao.impl;

import java.util.List;

import org.hibernate.jdbc.Work;

import netdisc.dao.VcatalogDao;
import netdisc.entity.Cltype;
import netdisc.entity.Vcatalog;
import netdisc.util.HibernateUtil;

public class VcatalogDaoImpl implements VcatalogDao {

	public VcatalogDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCreate(Vcatalog vc) {
		// TODO Auto-generated method stub
		HibernateUtil.getCurrentSession().save(vc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vcatalog> findInitByUId(int uid) {
		// TODO Auto-generated method stub
		String sql="from Vcatalog v where v.uid='"+uid+"' and v.layer=1";
		return HibernateUtil.getCurrentSession().createQuery(sql).list();
	}

	@Override
	public List<Vcatalog> findByUidAndVid(int uid, int vid) {
		// TODO Auto-generated method stub
		String sql="from Vcatalog v where v.uid='"+uid+"' and v.preid="+vid;
		return HibernateUtil.getCurrentSession().createQuery(sql).list();
	}

	@Override
	public Vcatalog findByVid(int vid) {
		// TODO Auto-generated method stub
		return (Vcatalog) HibernateUtil.getCurrentSession().get(Vcatalog.class, vid);
	}

	@Override
	public boolean isHaveF(Integer uid, Integer preid, String title,Cltype c) {
		String str="";
		if(preid==null){
			str=" preid is null ";
		}else{
			str=" preid = "+preid;
		}
		// TODO Auto-generated method stub
		String sql="select * from vcatalog where uid="+uid
				+" and "+str+" and title='"+title+"' and typeid="+c.getId();
		return HibernateUtil.getCurrentSession().createSQLQuery(sql).addEntity(Vcatalog.class).list().size()>0;
	}

	@Override
	public Cltype findCltypeByMd5(String md5) {
		// TODO Auto-generated method stub
		String sql="from Vcatalog v where v.md5='"+md5+"'";
		Vcatalog v=(Vcatalog) HibernateUtil.getCurrentSession().createQuery(sql).list().get(0);
		return v.getCltype();
	}

	@Override
	public void doDelById(int id) {
		// TODO Auto-generated method stub
		String sql="delete from vcatalog where id="+id;
		HibernateUtil.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vcatalog> findByPid(int pid) {
		// TODO Auto-generated method stub
		String sql="from Vcatalog v where v.preid="+pid;
		return HibernateUtil.getCurrentSession().createQuery(sql).list();
	}

}
