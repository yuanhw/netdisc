package netdisc.dao.impl;

import java.math.BigInteger;
import java.util.List;

import netdisc.dao.PlayDao;
import netdisc.entity.Play;
import netdisc.entity.Vtype;
import netdisc.util.HibernateUtil;

public class PlayDaoImpl implements PlayDao {

	public PlayDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCreate(Play play) {
		HibernateUtil.getCurrentSession().save(play);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Play> findByUName(String uname) {
		return HibernateUtil.getCurrentSession().createQuery("from Play p where p.uname=:uid")
				.setString("uid", uname).list();
	}

	@Override
	public void updateSouCount(String keyword) {
		// TODO Auto-generated method stub
		String sql="update plays as a  INNER JOIN ( select id from plays where title like '%"+keyword
				+"%' order by soucount desc limit 10) as b on a.id=b.id set soucount=(soucount+1)";
		HibernateUtil.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public void updatePlayCount(int id) {
		// TODO Auto-generated method stub
		Play p=(Play) HibernateUtil.getCurrentSession().get(Play.class, id);
		p.setPlaycount(p.getPlaycount()+1);
	}



	@Override
	public boolean checkOpen(String md5, String uname) {
		// TODO Auto-generated method stub
		return HibernateUtil.getCurrentSession().createQuery("from Play p where p.md5=:md5 and p.uname=:uname")
				.setString("md5", md5)
				.setString("uname", uname)
				.list().size()>0;
	}

	@Override
	public Play findById(Integer id) {
		// TODO Auto-generated method stub
		return (Play) HibernateUtil.getCurrentSession().get(Play.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Play> findByKeyWord(String keyword,int first,int length) {
		// TODO Auto-generated method stub
		return HibernateUtil.getCurrentSession().createQuery("from Play p where p.title like '%"+keyword+"%' order by p.playcount desc")
				.setFirstResult(first)
				.setMaxResults(length)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int totalCount(String keyword) {
		// TODO Auto-generated method stub
		int count=0;
		List<BigInteger> list = HibernateUtil.getCurrentSession().createSQLQuery("select count(*) as count from plays where title like '%"+keyword+"%'")
			.list();
		BigInteger is = list.get(0);
		count=is.intValue();
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Play> findByKeyWordT(String keyword, Vtype type, int first,
			int length) {
		return HibernateUtil.getCurrentSession()
				.createQuery("from Play p where p.title like '%"+keyword+"%' and p.type=:type order by p.playcount desc")
				.setEntity("type", type)
				.setFirstResult(first)
				.setMaxResults(length)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int totalCount2(String keyword, int typeid) {
		// TODO Auto-generated method stub
		int count=0;
		List<BigInteger> list = HibernateUtil.getCurrentSession()
				.createSQLQuery("select count(*) as count from plays where title like '%"+keyword+"%' and tid="+typeid)
			.list();
		BigInteger is = list.get(0);
		count=is.intValue();
		return count;
	}

	@Override
	public void updateSouCountT(String keyword, int tid) {
		// TODO Auto-generated method stub
		String sql="update plays as a  INNER JOIN ( select id from plays where title like '%"+keyword
				+"%' and tid = "+tid+"order by soucount desc limit 10) as b on a.id=b.id set soucount=(soucount+1)";
		HibernateUtil.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Play> findTopList() {
		// TODO Auto-generated method stub
		String sql="select * from plays order by soucount desc limit 5";
		return HibernateUtil.getCurrentSession().createSQLQuery(sql).addEntity(Play.class).list();
	}

	@Override
	public void doDelById(int id) {
		// TODO Auto-generated method stub
		String sql="delete from plays where id="+id;
		HibernateUtil.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
}
