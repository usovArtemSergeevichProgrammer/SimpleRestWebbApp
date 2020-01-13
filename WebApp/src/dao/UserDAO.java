package dao;

import java.util.List;

import model.User;

public interface UserDAO {
	boolean createUser(User user);
	boolean deleteUser(User user);
	boolean updateUser(User user);
	User getUserByLogin(User user);
	User getUserById(User user);
	List<User> getAllUsers();
	void setActive(User user, boolean isActive);
}
