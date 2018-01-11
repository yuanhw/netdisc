package netdisc.service;

import netdisc.entity.Users;

public interface UserService {
	void reg(Users user);
	boolean checkUserName(String username);
	boolean checkPhone(String phone);
	Users findByUserName(String username);
	Users findByPhone(String phone);
}
