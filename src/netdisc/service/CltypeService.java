package netdisc.service;

import netdisc.entity.Cltype;

public interface CltypeService {
	Cltype findByMime(String mime);
	Cltype addType(String mime);
}
