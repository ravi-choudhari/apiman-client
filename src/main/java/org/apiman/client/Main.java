package org.apiman.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apiman.client.domain.summary.OrganizationSummary;
import org.apiman.client.domain.system.SystemStatus;
import org.apiman.client.util.GenericUtils.INTERVAL;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) throws JsonProcessingException {

		System.setProperty("apiman.username", "admin");
		System.setProperty("apiman.password", "admin123!");
		System.setProperty("apiman.admin.username", "admin");
		System.setProperty("apiman.admin.password", "admin123!");

		ApplicationContext context = new ClassPathXmlApplicationContext("apiman-client-applicationContext.xml");

		ApimanAdminRestServicesClient adminclient = context.getBean(ApimanAdminRestServicesClient.class);
		ApimanRestServicesClient client = context.getBean(ApimanRestServicesClient.class);

		SystemStatus status = client.getSystemStatus();
		System.out.println(toJson(status));
		List<OrganizationSummary> orgs = client.getApiOrganizations();
		System.out.println(toJson(orgs));

//		NewOrganization neworg = new NewOrganization();
//		neworg.setName("second org");
//		client.createOrganization(neworg);
//		client.deleteOrganization("secondorg");

		orgs = client.getApiOrganizations();
		System.out.println(toJson(orgs));
		// http://localhost:8080/apiman/organizations/organizationId/apis/apiId/versions/version/metrics/clientUsage/
		// http://localhost:8080/apiman/organizations/genliant/apis/soaptest/versions/1.0/metrics/usage?from=2019-12-19T18:30:00.000Z&interval=hour&to=2019-12-27T12:09:25.969Z
		System.out.println(
				client.getApiUsageMetrics("genliant", "soaptest", "1.0", new Date(119, 0, 1), new Date(), INTERVAL.DAY.name()));

		System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date()));
	}

	private static final String toJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		return jsonInString;
	}
}
