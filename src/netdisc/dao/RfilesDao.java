package netdisc.dao;

import netdisc.entity.Rfiles;

public interface RfilesDao {
	void doCreate(Rfiles rf);
	boolean isHaveMd5(String md5);
	void updateRefCount(String md5,int count) throws Exception;
	void doDelByMd5(String md5);
	Rfiles findByMd5(String md5);
}
