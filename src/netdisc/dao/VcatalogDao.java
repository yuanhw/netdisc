package netdisc.dao;

import java.util.List;

import netdisc.entity.Cltype;
import netdisc.entity.Vcatalog;

public interface VcatalogDao {
	void doCreate(Vcatalog vc);
	List<Vcatalog> findInitByUId(int uid);
	List<Vcatalog> findByUidAndVid(int uid,int vid);
	List<Vcatalog> findByPid(int pid);
	Vcatalog findByVid(int vid);
	Cltype findCltypeByMd5(String md5);
	boolean isHaveF(Integer uid,Integer preid,String title,Cltype c);
	void doDelById(int id);
}
