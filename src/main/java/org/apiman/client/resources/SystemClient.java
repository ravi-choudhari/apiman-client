package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;

import java.util.HashMap;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.system.SystemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SystemClient extends AbstractApimanClient {
	
	@Autowired
	@Qualifier("redhatApimanAdminRestClient")
	protected RestTemplate adminTemplate;
	
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
		url = substitute(url, map, true);
		
		adminTemplate.getForObject(url, Void.class);
	}
	
	/*
	 * 
	 */
	public void importData() {
		
		String url = buildURL(apimanUrl, SYSTEM_PATH, IMPORT_PATH);
		adminTemplate.postForObject(url, null, Void.class);
	}
	
	/* This endpoint simply returns the status of the apiman system. This is a useful endpoint to use when testing 
	 * a client's connection to the apiman API Manager REST services.
	 */
	public SystemStatus getSystemStatus() {
		
		String url = buildURL(apimanUrl, SYSTEM_PATH, STATUS_PATH);
		return adminTemplate.getForObject(url, SystemStatus.class);
	}
}
