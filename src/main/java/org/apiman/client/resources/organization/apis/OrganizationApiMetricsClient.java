package org.apiman.client.resources.organization.apis;

import static org.apiman.client.GenericUtils.buildURL;
import static org.apiman.client.GenericUtils.substitute;

import java.util.HashMap;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.ApiMetrics;
import org.apiman.client.domain.ApiMetrics.METRICS_TYPE;
import org.apiman.client.domain.ApiMetricsList;
import org.springframework.stereotype.Component;

@Component
public class OrganizationApiMetricsClient extends AbstractApimanClient {

	private static final String ORGANIZATION_API_METRICS_PATH = ORGANIZATION_APIS_PATH + "/${apiId}/versions/${version}/metrics";
	
	/* Retrieves metrics/analytics information for a specific API. This will return response type statistics broken down by client.
	 * 
	 */
	public ApiMetrics getApiResponseStatisticsPerClient(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, METRICS_TYPE.CLIENT_RESPONSE_STATS);
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return request count data broken down by client. 
	 * It basically answers the question "who is calling my API?".
	 */
	public ApiMetrics getApiUsageMetricsPerClient(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, METRICS_TYPE.CLIENT_USAGE);
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return response type statistics broken down by plan.
	 * 
	 */
	public ApiMetrics getApiResponseStatisticsPerPlan(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, METRICS_TYPE.PLAN_RESPONSE_STATS);
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return request count data broken down by plan. 
	 * It basically answers the question "which API plans are most used?".
	 */
	public ApiMetrics getApiUsageMetricsPerPlan(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, METRICS_TYPE.PLAN_USAGE);
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return a full histogram of response statistics 
	 * data based on the provided date range and interval. Valid intervals are: month, week, day, hour, minute The data returned 
	 * includes total request counts, failure counts, and error counts for each data point in the histogram.
	 */
	public ApiMetrics getApiResponseStatistics(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, METRICS_TYPE.RESPONSE_STATS);
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return total response type statistics over 
	 * the given date range. Basically this will return three numbers: total request, # failed responses, # error responses.
	 */
	public ApiMetricsList getApiResponseStatisticsSummary(String organizationId, String apiId, String version, String fromDate, String toDate, String interval) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, interval, METRICS_TYPE.SUMMARY_RESPONSE_STATS);
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return a full histogram of request count data 
	 * based on the provided date range and interval. Valid intervals are: month, week, day, hour, minute
	 */
	public ApiMetricsList getApiUsageMetrics(String organizationId, String apiId, String version, String fromDate, String toDate, String interval) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, interval, METRICS_TYPE.USAGE);
	}
	
	private ApiMetrics getOrganizationApiMetrics(String organizationId, String apiId, String version, String fromDate, String toDate, METRICS_TYPE metricsType) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_METRICS_PATH, "/${metricsType}", FROM_AND_TO_DATES);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		map.put("metricsType", metricsType.getName());
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, ApiMetrics.class);
	}
	
	private ApiMetricsList getOrganizationApiMetrics(String organizationId, String apiId, String version, String fromDate, String toDate, String interval, METRICS_TYPE metricsType) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_METRICS_PATH, "/${metricsType}", FROM_AND_TO_DATES, INTERVAL);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		map.put("metricsType", metricsType.getName());
		map.put("interval", interval);
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, ApiMetricsList.class);
	}
}
