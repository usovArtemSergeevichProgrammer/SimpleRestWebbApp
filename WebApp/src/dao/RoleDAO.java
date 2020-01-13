package dao;

import java.util.List;

import model.Role;
import model.User;

public interface RoleDAO {
	boolean createUser(Role role);
	boolean deleteUser(Role role);
	boolean updateUser(Role role);
	List<Role> getAllRoles();
	
	Role getRoleById(Role role);
	Role getRoleByName(Role role);
	Role getRoleByUser(User user);
}
