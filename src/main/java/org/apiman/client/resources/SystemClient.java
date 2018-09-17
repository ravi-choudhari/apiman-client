package org.apiman.client.resources;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class SystemClient extends ApiClient {
	
	private static final String SYSTEM_PATH = "/system";
	
	/* Use this endpoint to export data from the API Manager to a file. All data in the API Manager, 
	 * including global/admin information, will be exported. The resulting file should be suitable for importing 
	 * into some other instance of the apiman API Manager. This is useful for upgrades, migrations between environments, 
	 * and backups.
	 */
	public String exportData() {
		
		return apimanUrl;
	}
	
	/*
	 * 
	 */
	public String importData() {
		
		return apimanUrl;
	}
	
	/* This endpoint simply returns the status of the apiman system. This is a useful endpoint to use when testing 
	 * a client's connection to the apiman API Manager REST services.
	 */
	public String getSystemStatus() {
		
		return apimanUrl;
	}
}
