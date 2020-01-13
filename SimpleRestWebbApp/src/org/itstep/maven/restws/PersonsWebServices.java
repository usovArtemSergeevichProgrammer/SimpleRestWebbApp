package org.itstep.maven.restws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.itstep.maven.model.Person;

@Path("/persons")
public class PersonsWebServices {
	private static List<Person> persons = new ArrayList<>();
	static {
		Person p1 = new Person(12123, "Alex", null);
		Person p2 = new Person(2342, "Bob", null);
		Person p3 = new Person(34, "Mike", null);
		Person p4 = new Person(566, "Jim", null);
		Person p5 = new Person(22, "Jackson", null);

		List<Person> l1 = Arrays.asList(p1, p2, p3);
		List<Person> l2 = Arrays.asList(p3, p1, p3);
		p4.setFriends(l1);
		p5.setFriends(l2);
		persons.add(p4);
		persons.add(p5);
	}

	@GET
	@Path("/all")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Person> getPersons() {
		return persons;
	}

	@GET
	@Path("/allj")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersonsJson() {
		return Response.ok(persons, MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("/updatePersons")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public List<Person> getUpdatePersons(Person p) {
		for (int i = 0; i < persons.size(); i++) {
			if (p.getId() == persons.get(i).getId()) {
				persons.set(i, p);
			}
		}
		return persons;
	}

}