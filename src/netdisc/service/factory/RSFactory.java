package netdisc.service.factory;

import netdisc.service.RfilesDService;
import netdisc.service.impl.RfilesDServiceImpl;

public class RSFactory {
	public static RfilesDService getIntance(){
		return new RfilesDServiceImpl();
	}
}
