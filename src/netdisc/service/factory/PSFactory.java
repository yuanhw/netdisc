package netdisc.service.factory;

import netdisc.service.PlayService;
import netdisc.service.impl.PlaySImpl;

public class PSFactory {

	public static PlayService getInstance(){
		return new PlaySImpl();
	}

}
