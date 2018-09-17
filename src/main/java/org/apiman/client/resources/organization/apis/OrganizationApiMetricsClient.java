package org.apiman.client.resources.organization.apis;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class OrganizationApiMetricsClient extends ApiClient {

	private static final String ORGANIZATION_APIS_METRICS_PATH = ORGANIZATIONS_PATH + "/{organizationId}/apis/{apiId}/versions/{version}/metrics";
	
	/* Retrieves metrics/analytics information for a specific API. This will return response type statistics broken down by client.
	 * 
	 */
	public String getApiResponseStatisticsPerClient() {
		
		return apimanUrl;
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return request count data broken down by client. 
	 * It basically answers the question "who is calling my API?".
	 */
	public String getApiUsageMetricsPerClient() {
		
		return apimanUrl;
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return response type statistics broken down by plan.
	 * 
	 */
	public String getApiResponseStatisticsPerPlan() {
		
		return apimanUrl;
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return request count data broken down by plan. 
	 * It basically answers the question "which API plans are most used?".
	 */
	public String getApiUsageMetricsPerPlan() {
		
		return apimanUrl;
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return a full histogram of response statistics 
	 * data based on the provided date range and interval. Valid intervals are: month, week, day, hour, minute The data returned 
	 * includes total request counts, failure counts, and error counts for each data point in the histogram.
	 */
	public String getApiResponseStatistics() {
		
		return apimanUrl;
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return total response type statistics over 
	 * the given date range. Basically this will return three numbers: total request, # failed responses, # error responses.
	 */
	public String getApiResponseStatisticsSummary() {
		
		return apimanUrl;
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return a full histogram of request count data 
	 * based on the provided date range and interval. Valid intervals are: month, week, day, hour, minute
	 */
	public String getApiUsageMetrics() {
		
		return apimanUrl;
	}
}
