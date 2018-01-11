package netdisc.service.factory;

import netdisc.service.VcatalogService;
import netdisc.service.impl.VServiceImpl;

public class VSFactory {

	public static VcatalogService getInstance(){
		return new VServiceImpl();
	}
}
