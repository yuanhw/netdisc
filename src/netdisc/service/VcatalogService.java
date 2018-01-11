package netdisc.service;

import java.util.List;

import netdisc.entity.Cltype;
import netdisc.entity.Rfiles;
import netdisc.entity.Vcatalog;

public interface VcatalogService {
	void addFile(Vcatalog vc,Rfiles rf);
	void addFileHaveMd5(Vcatalog vc) throws Exception;
	List<Vcatalog> loadInitList(int uid);
	List<Vcatalog> loadListByVid(int uid,int vid);
	Vcatalog loadByVid(int vid);
	boolean isHaveF(Integer uid,Integer pid,String title,Cltype c);
	void addF(Vcatalog vc);
	Cltype findCltypeBymd5(String md5);
}
