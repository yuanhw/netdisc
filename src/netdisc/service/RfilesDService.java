package netdisc.service;

import netdisc.entity.Rfiles;

public interface RfilesDService {
	boolean isHaveMd5(String md5);
	Rfiles findByMd5(String md5);
}
