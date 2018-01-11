package netdisc.service.factory;

import netdisc.service.DelService;
import netdisc.service.VcatalogService;
import netdisc.service.impl.DelServiceImpl;
import netdisc.service.impl.VServiceImpl;

public class DSFactory {

	public static DelService getInstance(){
		return new DelServiceImpl();
	}
}
