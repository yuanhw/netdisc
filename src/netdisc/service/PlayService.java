package netdisc.service;

import netdisc.dao.PlayDao;

public interface PlayService  extends PlayDao{
	void delGroupPlays(int[] ids) throws Exception;
}
