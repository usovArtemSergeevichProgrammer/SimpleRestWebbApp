package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.RoleDAO;
import model.Role;
import model.User;
import utils.DBUtils;

public class RoleDAOImpl implements RoleDAO {

	@Override
	public boolean createUser(Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> resultRoles = new ArrayList<>();
		Role role = null;
		String sql = "SELECT ROLE_ID, NAME, DESCR FROM USERS.ROLES ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs =  pstmt.executeQuery();
			while(rs.next()){
				role = new Role(rs.getInt("ROLE_ID"));
				role.setName(rs.getString("NAME"));
				role.setDescr(rs.getString("DESCR"));
				resultRoles.add(role);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(conn, null, pstmt);
		}
	
		return resultRoles;
	}
	

	@Override
	public Role getRoleById(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public Role getRoleByName(Role role) {
        String selectSQL ="SELECT ROLE_ID, NAME, DESCR FROM USERS.ROLES WHERE NAME = ?" ;
           Connection conn = DBUtils.getConnection();
           PreparedStatement pstmt = null;
           ResultSet rs = null;
           try {
               pstmt = conn.prepareStatement(selectSQL);
               pstmt.setString(1, role.getName());
               rs = pstmt.executeQuery();
               if(rs.next()) {
                   role = new Role(rs.getInt("ROLE_ID"));
                   role.setName(rs.getString("NAME"));
                   role.setDescr(rs.getString("DESCR"));
                   
               }
           } catch (SQLException e) {
               e.printStackTrace();
           } finally {
               DBUtils.release(conn, null, pstmt);
           }
           return role;
	}

	@Override
	public Role getRoleByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
