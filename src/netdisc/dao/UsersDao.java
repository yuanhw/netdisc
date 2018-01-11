package netdisc.dao;

import netdisc.entity.Users;

public interface UsersDao {
	void doCreate(Users user);
	Users findByUserName(String username);
	Users findByPhone(String phone);
	boolean isHaveUserName(String username);
	boolean isHavePhone(String phone);
	void updateAccount(int id,int add);
}
