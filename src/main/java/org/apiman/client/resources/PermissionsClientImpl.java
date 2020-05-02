package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;

import java.util.HashMap;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.permissions.UserPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PermissionsClientImpl extends AbstractApimanClient implements IPermissionsClient {
	
	@Autowired
	@Qualifier("redhatApimanAdminRestClient")
	protected RestTemplate adminTemplate;
	
	private static final String PERMISSIONS_PATH = "/permissions";
	
	/* This endpoint returns all of the permissions assigned to the currently authenticated user.
	 * 
	 */
	@Override
	public UserPermissions getCurrentUserPermissions() {
		
		String url = buildURL(apimanUrl, PERMISSIONS_PATH);
		return restTemplate.getForObject(url, UserPermissions.class);
	}
	
	/* This endpoint returns all of the permissions assigned to a specific user.
	 * 
	 */
	@Override
	public UserPermissions getUserPermissions(String userId) {
		
		String url = buildURL(apimanUrl, PERMISSIONS_PATH, "/${userId}");
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		url = substitute(url, map, false);
		
		return adminTemplate.getForObject(url, UserPermissions.class);
	}
}
