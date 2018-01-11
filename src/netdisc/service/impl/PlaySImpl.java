package netdisc.service.impl;

import java.util.List;

import netdisc.dao.PlayDao;
import netdisc.dao.RfilesDao;
import netdisc.dao.UsersDao;
import netdisc.dao.impl.PlayDaoImpl;
import netdisc.dao.impl.RfilesDaoImpl;
import netdisc.dao.impl.UsersDaoImpl;
import netdisc.entity.Play;
import netdisc.entity.Users;
import netdisc.entity.Vtype;
import netdisc.service.PlayService;
import netdisc.util.HibernateUtil;

public class PlaySImpl implements PlayService {
	private PlayDao pd;
	private RfilesDao rdao;
	private UsersDao udao;
	public PlaySImpl() {
		// TODO Auto-generated constructor stub
		pd=new PlayDaoImpl();
		rdao=new RfilesDaoImpl();
		udao=new UsersDaoImpl();
	}

	@Override
	public void doCreate(Play play) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			this.pd.doCreate(play);
			this.rdao.updateRefCount(play.getMd5(), 1);
			Users u=this.udao.findByUserName(play.getUname());
			this.udao.updateAccount(u.getId(), 5);
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
	public List<Play> findByUName(String uname) {
		// TODO Auto-generated method stub
		List<Play> list=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			list=this.pd.findByUName(uname);
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
	public void updateSouCount(String keyword) {
		// TODO Auto-generated method stub
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			this.pd.updateSouCount(keyword);
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
	public void updatePlayCount(int id) {
		// TODO Auto-generated method stub
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			this.pd.updatePlayCount(id);
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
	public List<Play> findByKeyWord(String keyword,int first,int length) {
		// TODO Auto-generated method stub
		List<Play> list=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			list=this.pd.findByKeyWord(keyword,first,length);
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
	public boolean checkOpen(String md5, String uname) {
		// TODO Auto-generated method stub
		boolean tag=false;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			tag=this.pd.checkOpen(md5, uname);
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
	public Play findById(Integer id) {
		// TODO Auto-generated method stub
		Play p=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			p=this.pd.findById(id);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return p;
	}

	@Override
	public int totalCount(String keyword) {
		// TODO Auto-generated method stub
		int count=0;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			count=this.pd.totalCount(keyword);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return count;
	}

	@Override
	public List<Play> findByKeyWordT(String keyword, Vtype type, int first,
			int length) {
		// TODO Auto-generated method stub
		List<Play> list=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			list=this.pd.findByKeyWordT(keyword, type, first, length);
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
	public int totalCount2(String keyword, int typeid) {
		// TODO Auto-generated method stub
		int count=0;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			count=this.pd.totalCount2(keyword, typeid);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return count;
	}

	@Override
	public void updateSouCountT(String keyword, int tid) {
		// TODO Auto-generated method stub
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			this.pd.updateSouCountT(keyword, tid);
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
	public List<Play> findTopList() {
		// TODO Auto-generated method stub
		List<Play> list=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			list=this.pd.findTopList();
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
	public void doDelById(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delGroupPlays(int[] ids) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			for(int i=0;i<ids.length;i++){
				Play item = this.pd.findById(ids[i]);
				this.pd.doDelById(ids[i]);
				this.rdao.updateRefCount(item.getMd5(), -1);
			}
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

}
