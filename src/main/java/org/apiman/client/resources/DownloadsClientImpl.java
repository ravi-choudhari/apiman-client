package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;

import java.util.HashMap;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.springframework.stereotype.Component;

@Component
public class DownloadsClientImpl extends AbstractApimanClient implements IDownloadsClient {
	
	private static final String DOWNLOADS_PATH = "/downloads";
	
	/* This endpoint is used to download a file that was previously generated by some other REST API call. For example, 
	 * when exporting data via the /system/export endpoint, a temporary download link may be created. This represents that 
	 * temporary download link. In this example, the download will result in the exported data.
	 */
	@Override
	public String downloadFile(String downloadId) {
		
		String url = buildURL(apimanUrl, DOWNLOADS_PATH, "/${downloadId}");
		Map<String, String> map = new HashMap<>();
		map.put("downloadId", downloadId);
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, String.class);
	}
}
