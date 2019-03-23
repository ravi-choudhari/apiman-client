package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;

import java.util.HashMap;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.PermissionsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PermissionsClient extends AbstractApimanClient {
	
	@Autowired
	@Qualifier("redhatApimanAdminRestClient")
	protected RestTemplate adminTemplate;
	
	private static final String PERMISSIONS_PATH = "/permissions";
	
	/* This endpoint returns all of the permissions assigned to the currently authenticated user.
	 * 
	 */
	public PermissionsList getCurrentUserPermissions() {
		
		String url = buildURL(apimanUrl, PERMISSIONS_PATH);
		return restTemplate.getForObject(url, PermissionsList.class);
	}
	
	/* This endpoint returns all of the permissions assigned to a specific user.
	 * 
	 */
	public PermissionsList getUserPermissions(String userId) {
		
		String url = buildURL(apimanUrl, PERMISSIONS_PATH, "/${userId}");
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		url = substitute(url, map, true);
		
		return adminTemplate.getForObject(url, PermissionsList.class);
	}
}
