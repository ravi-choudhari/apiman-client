package org.apiman.client;

import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class MainTest {
	
	public static void main(String[] args) {
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(new FileSystemResource(
				"C:\\Users\\RAVI\\Internal-Workspace\\apiman-client\\src\\main\\resources\\applicationContext.xml").getPath());
		
		ApimanRestServicesClient client = context.getBean(ApimanRestServicesClient.class);
		System.out.println(client.executeEntityAction());
	}
}
