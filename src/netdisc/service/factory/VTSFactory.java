package netdisc.service.factory;

import netdisc.service.VtypeService;
import netdisc.service.impl.VtypeServiceImpl;

public class VTSFactory {

	public static VtypeService getInstance(){
		return new VtypeServiceImpl();
	}

}
