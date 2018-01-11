package netdisc.service.impl;

import netdisc.dao.UsersDao;
import netdisc.dao.impl.UsersDaoImpl;
import netdisc.entity.Users;
import netdisc.service.UserService;
import netdisc.util.HibernateUtil;

public class UserServiceImpl implements UserService {

	private UsersDao udao;
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
		udao=new UsersDaoImpl();
	}

	@Override
	public void reg(Users user) {
		// TODO Auto-generated method stub
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			udao.doCreate(user);
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
	public boolean checkUserName(String username) {
		// TODO Auto-generated method stub
		boolean tag=false;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			tag=udao.isHaveUserName(username);
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
	public boolean checkPhone(String phone) {
		boolean tag=false;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			tag=udao.isHavePhone(phone);
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
	public Users findByUserName(String username) {
		// TODO Auto-generated method stub
		Users u=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			u=udao.findByUserName(username);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return u;
	}

	@Override
	public Users findByPhone(String phone) {
		// TODO Auto-generated method stub
		Users u=null;
		HibernateUtil.getCurrentSession().beginTransaction();
		try{
			u=udao.findByPhone(phone);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(Exception e1){
				throw e1;
			}
			throw e;
		}
		return u;
	}

}
