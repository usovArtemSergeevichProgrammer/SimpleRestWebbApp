package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import dao.UserDAO;
import model.Role;
import model.User;
import utils.DBUtils;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean createUser(User user) {
		Connection conn = null;
		Statement stmt = null;

		try {
            conn = DBUtils.getConnection();
            stmt = conn.createStatement();
            int result = stmt.executeUpdate("INSERT INTO USERS.USER (NAME, PASSWORD, EMAIL) "
                    + "VALUES ('"+user.getName()+"', '"+user.getPass()+"', '"+user.getEmail()+"');");
            if(result == 1) {
            	DBUtils.release(conn, stmt, null);
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); 
        }

		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		String SQL = "DELETE FROM USERS.USER WHERE ID = ?";
		Connection conn = null;
		PreparedStatement pstms = null;
		boolean isDelited = false;
		try {
			conn = DBUtils.getConnection();
			pstms = conn.prepareStatement(SQL);
			pstms.setInt(1, user.getId());
			isDelited = pstms.executeUpdate()==1;
			DBUtils.release(conn, null, pstms);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDelited;
	}

	@Override
	public boolean updateUser(User user) {
		String SQL = "UPDATE users.user SET NAME = ?, PASSWORD = ?, EMAIL = ?, ROLE_ID = ? WHERE ID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean isUpdated = false;
		try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPass());
            pstmt.setString(3, user.getEmail());
            pstmt.setInt(4, user.getRole().getId()); // int
            pstmt.setInt(5, user.getId());
            isUpdated = pstmt.executeUpdate() == 1;
            DBUtils.release(conn, null, pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	@Override
	public User getUserByLogin(User user) {
		//OLD
		//String sql = "SELECT ID, NAME, PASSWORD, EMAIL, IS_ACTIVE FROM USERS.USER WHERE NAME = ? AND PASSWORD = ?";
		String sql = "SELECT U.ID, U.NAME, U.PASSWORD, U.EMAIL, U.ROLE_ID, U.IS_ACTIVE, R.NAME as 'ROLE_NAME', R.DESCR FROM USERS.USER U LEFT JOIN USERS.ROLES R ON U.ROLE_ID = R.ROLE_ID WHERE U.NAME = ? AND U.PASSWORD = ?";
		Connection conn = null;
		PreparedStatement pstms = null;
		
		try {
			conn = DBUtils.getConnection();
			pstms = conn.prepareStatement(sql);
			pstms.setString(1, user.getName());
			pstms.setString(2, user.getPass());
			ResultSet rs = pstms.executeQuery();
			if(rs.next()){
				
				User userFromDB =  new User();
				userFromDB.setId(rs.getInt("ID"));
				userFromDB.setName(rs.getString("NAME"));
				userFromDB.setPass(rs.getString("PASSWORD"));
				userFromDB.setEmail(rs.getString("EMAIL"));
				userFromDB.setActive(rs.getString("IS_ACTIVE").equals("Y")? true: false);
				// POPULATE ROLE OBJECT!
				Role userRole = new Role(rs.getInt("ROLE_ID"));
				userRole.setName(rs.getString("ROLE_NAME"));
				userRole.setDescr(rs.getString("DESCR"));
				
				userFromDB.setRole(userRole);
				
				System.out.println("USER FOUND ->" + userFromDB);
				
				return userFromDB;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> resultUsers = new ArrayList<>();
		User user = null;
		String sql = "SELECT U.ID, U.NAME, U.PASSWORD, U.EMAIL, U.ROLE_ID, U.IS_ACTIVE, R.NAME as 'ROLE_NAME', R.DESCR FROM USERS.USER U LEFT JOIN USERS.ROLES R ON U.ROLE_ID = R.ROLE_ID ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs =  pstmt.executeQuery();
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setPass(rs.getString("PASSWORD"));
				user.setEmail(rs.getString("EMAIL"));
				user.setActive(rs.getString("IS_ACTIVE").equals("Y")? true: false);
				// POPULATE ROLE OBJECT!
				Role userRole = new Role(rs.getInt("ROLE_ID"));
				userRole.setName(rs.getString("ROLE_NAME"));
				userRole.setDescr(rs.getString("DESCR"));
				user.setRole(userRole);
				
				resultUsers.add(user);
			}
			DBUtils.release(conn, null, pstmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return resultUsers;
	}

	@Override
	public void setActive(User user, boolean isActive) {
		String sql = "UPDATE USERS.USER SET IS_ACTIVE = ? WHERE ID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isActive? "Y":"N");
			pstmt.setInt(2, user.getId());
			if(pstmt.executeUpdate() == 1){
				System.out.println("User ID = " + user.getId() + (isActive? " ACTIVATED":"DE-ACTIVATED"));
			}
			DBUtils.release(conn, null, pstmt);
		} catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		UserDAOImpl dao = new UserDAOImpl();
		for (User user : dao.getAllUsers()) {
			System.out.println(user);
		}
		
	}

	@Override
	public User getUserById(User user) {
		String sql = "SELECT U.ID, U.NAME, U.PASSWORD, U.EMAIL, U.ROLE_ID, U.IS_ACTIVE, R.NAME as 'ROLE_NAME', R.DESCR FROM USERS.USER U LEFT JOIN USERS.ROLES R ON U.ROLE_ID = R.ROLE_ID WHERE U.ID = ?";
		Connection conn = null;
		PreparedStatement pstms = null;
		
		try {
			conn = DBUtils.getConnection();
			pstms = conn.prepareStatement(sql);
			pstms.setInt(1, user.getId());
			ResultSet rs = pstms.executeQuery();
			if(rs.next()){
				
				User userFromDB =  new User();
				userFromDB.setId(rs.getInt("ID"));
				userFromDB.setName(rs.getString("NAME"));
				userFromDB.setPass(rs.getString("PASSWORD"));
				userFromDB.setEmail(rs.getString("EMAIL"));
				userFromDB.setActive(rs.getString("IS_ACTIVE").equals("Y")? true: false);
				// POPULATE ROLE OBJECT!
				Role userRole = new Role(rs.getInt("ROLE_ID"));
				userRole.setName(rs.getString("ROLE_NAME"));
				userRole.setDescr(rs.getString("DESCR"));
				
				userFromDB.setRole(userRole);
				
				System.out.println("USER FOUND ->" + userFromDB);
				
				return userFromDB;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
