package org.apiman.client.resources;

import static org.apiman.client.GenericUtils.buildURL;
import static org.apiman.client.GenericUtils.substitute;

import java.util.HashMap;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.SystemStatus;
import org.springframework.stereotype.Component;

@Component
public class SystemClient extends AbstractApimanClient {
	
	private static final String SYSTEM_PATH = "/system";
	private static final String IMPORT_PATH = "/import";
	private static final String EXPORT_PATH = "/export";
	
	/* Use this endpoint to export data from the API Manager to a file. All data in the API Manager, 
	 * including global/admin information, will be exported. The resulting file should be suitable for importing 
	 * into some other instance of the apiman API Manager. This is useful for upgrades, migrations between environments, 
	 * and backups.
	 */
	public void exportData(String download) {
		
		String url = buildURL(apimanUrl, SYSTEM_PATH, EXPORT_PATH, DOWNLOAD);
		Map<String, String> map = new HashMap<>();
		map.put("download", download);
		url = substitute(url, map);
		
		restTemplate.getForObject(url, Void.class);
	}
	
	/*
	 * 
	 */
	public void importData() {
		
		String url = buildURL(apimanUrl, SYSTEM_PATH, IMPORT_PATH);
		restTemplate.postForObject(url, null, Void.class);
	}
	
	/* This endpoint simply returns the status of the apiman system. This is a useful endpoint to use when testing 
	 * a client's connection to the apiman API Manager REST services.
	 */
	public SystemStatus getSystemStatus() {
		
		String url = buildURL(apimanUrl, SYSTEM_PATH, STATUS_PATH);
		return restTemplate.getForObject(url, SystemStatus.class);
	}
}
