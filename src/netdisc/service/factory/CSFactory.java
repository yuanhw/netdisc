package netdisc.service.factory;

import netdisc.service.CltypeService;
import netdisc.service.impl.CltypeServiceImpl;

public class CSFactory {

	public static CltypeService getInstance(){
		return new CltypeServiceImpl();
	}

}
