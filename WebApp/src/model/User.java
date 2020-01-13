package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private int id;
	private String name;
	private String pass;
	private String email;
	private boolean isActive;
	private Role role;
	
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String name, String pass) {
		super();
		this.name = name;
		this.pass = pass;
	}
	public User(int id) {
		super();
		this.id = id;
	}

	public User(int id, String name, String pass, String email) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pass=" + pass + ", email=" + email + ", isActive=" + isActive
				+ "]";
	}
	
}
