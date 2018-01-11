package netdisc.dao.impl;

import java.util.List;

import netdisc.dao.UsersDao;
import netdisc.entity.Users;
import netdisc.util.HibernateUtil;

public class UsersDaoImpl implements UsersDao {

	public UsersDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCreate(Users user) {
		HibernateUtil.getCurrentSession().save(user);
	}

	@Override
	public Users findByUserName(String username) {
		// TODO Auto-generated method stub
		String sql="from Users u where u.username=:uname";
		@SuppressWarnings("unchecked")
		List<Users> list=HibernateUtil.getCurrentSession().createQuery(sql).setString("uname", username).list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Users findByPhone(String phone) {
		// TODO Auto-generated method stub
		String sql="from Users u where u.phone=:phone";
		@SuppressWarnings("unchecked")
		List<Users> list=HibernateUtil.getCurrentSession().createQuery(sql).setString("phone", phone).list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean isHaveUserName(String username) {
		// TODO Auto-generated method stub
		String sql="from Users u where u.username=:uname";
		return HibernateUtil.getCurrentSession().createQuery(sql).setString("uname", username).list().size()>0;
	}

	@Override
	public boolean isHavePhone(String phone) {
		// TODO Auto-generated method stub
		String sql="from Users u where u.phone=:phone";
		return HibernateUtil.getCurrentSession().createQuery(sql).setString("phone", phone).list().size()>0;
	}

	@Override
	public void updateAccount(int uid,int add) {
		// TODO Auto-generated method stub
		Users u=(Users) HibernateUtil.getCurrentSession().get(Users.class, uid);
		if(u.getAccount()==null){
			u.setAccount(new Double(add));
		}else{
			u.setAccount(u.getAccount()+add);
		}
	}

}
