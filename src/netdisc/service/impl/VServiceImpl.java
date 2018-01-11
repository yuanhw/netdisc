package netdisc.service.impl;

import java.util.List;

import netdisc.dao.RfilesDao;
import netdisc.dao.VcatalogDao;
import netdisc.dao.impl.RfilesDaoImpl;
import netdisc.dao.impl.VcatalogDaoImpl;
import netdisc.entity.Cltype;
import netdisc.entity.Rfiles;
import netdisc.entity.Vcatalog;
import netdisc.service.VcatalogService;
import netdisc.util.HibernateUtil;
import netdisc.util.Tools;

public class VServiceImpl implements VcatalogService {

	private RfilesDao rfdao;
	private VcatalogDao vdao;
	
	public VServiceImpl() {
		// TODO Auto-generated constructor stub
		rfdao=new RfilesDaoImpl();
		vdao=new VcatalogDaoImpl();
	}

	@Override
	public void addFile(Vcatalog vc,Rfiles rf) {
		// TODO Auto-generated method stub
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			if(!vc.getCltype().getTitle().equals("vfile")){
				String allname=Tools.getIpTime();
				rfdao.doCreate(rf);
			}
			vdao.doCreate(vc);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
	}

	@Override
	public List<Vcatalog> loadInitList(int uid) {
		// TODO Auto-generated method stub
		List<Vcatalog> list=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			list=vdao.findInitByUId(uid);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return list;
	}

	@Override
	public List<Vcatalog> loadListByVid(int uid, int vid) {
		// TODO Auto-generated method stub
		List<Vcatalog> list=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			list=vdao.findByUidAndVid(uid, vid);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return list;
	}

	@Override
	public Vcatalog loadByVid(int vid) {
		// TODO Auto-generated method stub
		Vcatalog v=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			v=vdao.findByVid(vid);
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

	@Override
	public void addFileHaveMd5(Vcatalog vc) throws Exception {
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			if(!vc.getCltype().getTitle().equals("vfile")){
				rfdao.updateRefCount(vc.getMd5(), 1);
			}
			vdao.doCreate(vc);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
	}

	@Override
	public boolean isHaveF(Integer uid, Integer pid, String title,Cltype c) {
		// TODO Auto-generated method stub
		boolean rt=false;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			rt=vdao.isHaveF(uid, pid, title, c);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return rt;
	}

	@Override
	public void addF(Vcatalog vc) {
		// TODO Auto-generated method stub
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			vdao.doCreate(vc);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
	}

	@Override
	public Cltype findCltypeBymd5(String md5) {
		// TODO Auto-generated method stub
		Cltype c=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			c=vdao.findCltypeByMd5(md5);
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
