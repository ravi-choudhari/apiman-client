package org.apiman.client;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) throws JsonProcessingException {
		
//		ApplicationContext context = new ClassPathXmlApplicationContext("apiman-client-applicationContext.xml");
//		
//		ApimanAdminRestServicesClient adminclient = context.getBean(ApimanAdminRestServicesClient.class);
//		SystemStatus status = adminclient.getSystemStatus();
//		System.out.println(toJson(status));
//		
//		ApimanRestServicesClient client = context.getBean(ApimanRestServicesClient.class);
//		List<Organization> orgs = client.getApiOrganizations();
//		System.out.println(toJson(orgs));
//		
//		Organization neworg = new Organization();
//		neworg.setName("second org");
//		client.createOrganization(neworg);
//		
//		orgs = client.getApiOrganizations();
//		System.out.println(toJson(orgs));
	}
	
	private static final String toJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		return jsonInString;
	}
}
