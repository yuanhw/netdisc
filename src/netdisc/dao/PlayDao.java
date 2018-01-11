package netdisc.dao;

import java.util.List;

import netdisc.entity.Play;
import netdisc.entity.Vtype;

public interface PlayDao {

	void doCreate(Play play) throws Exception;
	List<Play> findByUName(String uname);
	void updateSouCount(String keyword);
	void updateSouCountT(String keyword,int tid);
	void updatePlayCount(int id);
	List<Play> findByKeyWord(String keyword,int first,int length);
	List<Play> findByKeyWordT(String keyword,Vtype type,int first,int length);
	boolean  checkOpen(String md5,String uname);
	Play findById(Integer id);
	int totalCount(String keyword);
	int totalCount2(String keyword,int typeid);
	List<Play> findTopList();
	void doDelById(int id);
}
