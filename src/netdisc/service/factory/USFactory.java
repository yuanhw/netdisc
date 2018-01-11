package netdisc.service.factory;

import netdisc.service.UserService;
import netdisc.service.impl.UserServiceImpl;

public class USFactory {
	public static UserService getInstance(){
		return new UserServiceImpl();
	}
}
