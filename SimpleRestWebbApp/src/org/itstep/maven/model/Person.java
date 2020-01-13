package org.itstep.maven.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

//@JsonInclude(Include.NON_EMPTY)
@XmlRootElement
public class Person {

	private int id;
	private String name;
	private List<Person> friends;
	
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
	public List<Person> getFriends() {
		return friends;
	}
	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
	public Person(int id, String name, List<Person> friends) {
		super();
		this.id = id;
		this.name = name;
		this.friends = friends;
	}
	public Person() {
		super();
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", friends=" + friends + "]";
	}
	
	
}
