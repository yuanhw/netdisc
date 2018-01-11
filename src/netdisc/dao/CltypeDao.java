package netdisc.dao;

import netdisc.entity.Cltype;

public interface CltypeDao {
	Cltype findByMime(String mime);
	Cltype doCreate(String mime);
}
