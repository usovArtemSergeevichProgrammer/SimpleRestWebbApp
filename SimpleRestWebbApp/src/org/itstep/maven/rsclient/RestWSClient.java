package org.itstep.maven.rsclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestWSClient {

	public static ClientResponse getClientResponse(String path, String mtype, String method) {
		Client client = Client.create();

		WebResource webResource = client.resource(path);
		ClientResponse response = null;
		if (mtype != null) {
			switch (method) {
			case "get":
				response = webResource.accept("application/" + mtype).get(ClientResponse.class);
				break;
			case "put":
				response = webResource.accept("application/" + mtype).put(ClientResponse.class);
				break;
			case "post":
				response = webResource.accept("application/" + mtype).post(ClientResponse.class);
				break;
			case "delete":
				response = webResource.accept("application/" + mtype).delete(ClientResponse.class);
				break;

			default:
				break;
			}
		} else {
			switch (method) {
			case "get":
				response = webResource.get(ClientResponse.class);
				break;
			case "put":
				response = webResource.put(ClientResponse.class);
				break;
			case "post":
				response = webResource.post(ClientResponse.class);
				break;
			case "delete":
				response = webResource.delete(ClientResponse.class);
				break;

			default:
				break;
			}
		}
		return response;
	}

	public static void main(String[] args) {
		try {
			String path = "http://localhost:8080/wsapp/rest/persons/allj";
			String json = "json";
			// String xml = "xml";
			ClientResponse response = getClientResponse(path, null, "get");

			// Client client = Client.create();
			//
			// WebResource webResource =
			// client.resource("http://localhost:8080/wsapp/rest/persons/all");
			//
			// ClientResponse response =
			// webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
