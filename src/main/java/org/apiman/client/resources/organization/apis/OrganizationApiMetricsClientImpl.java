package org.apiman.client.resources.organization.apis;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.summary.METRICS_TYPE;
import org.apiman.client.domain.summary.ResponseStats;
import org.apiman.client.domain.summary.ResponseStatsHistogram;
import org.apiman.client.domain.summary.ResponseStatsSummary;
import org.apiman.client.domain.summary.Usage;
import org.apiman.client.domain.summary.UsageHistogram;
import org.apiman.client.util.GenericUtils;
import org.springframework.stereotype.Component;

@Component
public class OrganizationApiMetricsClientImpl extends AbstractApimanClient implements IOrganizationApiMetricsClient {

	private static final String ORGANIZATION_API_METRICS_PATH = ORGANIZATION_APIS_PATH + "/${apiId}/versions/${version}/metrics";
	
	/* Retrieves metrics/analytics information for a specific API. This will return response type statistics broken down by client.
	 * 
	 */
	@Override
	public ResponseStats getApiResponseStatisticsPerClient(String organizationId, String apiId, String version, Date fromDate, Date toDate) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, METRICS_TYPE.CLIENT_RESPONSE_STATS, ResponseStats.class);
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return request count data broken down by client. 
	 * It basically answers the question "who is calling my API?".
	 */
	@Override
	public Usage getApiUsageMetricsPerClient(String organizationId, String apiId, String version, Date fromDate, Date toDate) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, METRICS_TYPE.CLIENT_USAGE, Usage.class);
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return response type statistics broken down by plan.
	 * 
	 */
	@Override
	public ResponseStats getApiResponseStatisticsPerPlan(String organizationId, String apiId, String version, Date fromDate, Date toDate) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, METRICS_TYPE.PLAN_RESPONSE_STATS, ResponseStats.class);
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return request count data broken down by plan. 
	 * It basically answers the question "which API plans are most used?".
	 */
	@Override
	public Usage getApiUsageMetricsPerPlan(String organizationId, String apiId, String version, Date fromDate, Date toDate) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, METRICS_TYPE.PLAN_USAGE, Usage.class);
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return a full histogram of response statistics 
	 * data based on the provided date range and interval. Valid intervals are: month, week, day, hour, minute The data returned 
	 * includes total request counts, failure counts, and error counts for each data point in the histogram.
	 */
	@Override
	public ResponseStatsHistogram getApiResponseStatistics(String organizationId, String apiId, String version, Date fromDate, Date toDate) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, METRICS_TYPE.RESPONSE_STATS, ResponseStatsHistogram.class);
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return total response type statistics over 
	 * the given date range. Basically this will return three numbers: total request, # failed responses, # error responses.
	 */
	@Override
	public ResponseStatsSummary getApiResponseStatisticsSummary(String organizationId, String apiId, String version, Date fromDate, Date toDate, String interval) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, interval, METRICS_TYPE.SUMMARY_RESPONSE_STATS, ResponseStatsSummary.class);
	}
	
	/* Retrieves metrics/analytics information for a specific API. This will return a full histogram of request count data 
	 * based on the provided date range and interval. Valid intervals are: month, week, day, hour, minute
	 */
	@Override
	public UsageHistogram getApiUsageMetrics(String organizationId, String apiId, String version, Date fromDate, Date toDate, String interval) {
		
		return getOrganizationApiMetrics(organizationId, apiId, version, fromDate, toDate, interval, METRICS_TYPE.USAGE, UsageHistogram.class);
	}
	
	private <T> T getOrganizationApiMetrics(String organizationId, String apiId, String version, Date fromDate, Date toDate, METRICS_TYPE metricsType, Class<T> type) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_METRICS_PATH, "/${metricsType}", FROM_AND_TO_DATES);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		map.put("metricsType", metricsType.getName());
		map.put("fromDate", GenericUtils.formatDate(fromDate != null ? fromDate : new Date()));
		map.put("toDate", GenericUtils.formatDate(toDate != null ? toDate : new Date()));
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, type);
	}
	
	private <T> T getOrganizationApiMetrics(String organizationId, String apiId, String version, Date fromDate, Date toDate, String interval, METRICS_TYPE metricsType, Class<T> type) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_METRICS_PATH, "/${metricsType}", FROM_AND_TO_DATES, INTERVAL);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		map.put("metricsType", metricsType.getName());
		map.put("interval", isNotBlank(interval) ? interval : GenericUtils.INTERVAL.getDefault());
		map.put("fromDate", GenericUtils.formatDate(fromDate != null ? fromDate : new Date()));
		map.put("toDate", GenericUtils.formatDate(toDate != null ? toDate : new Date()));
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, type);
	}
}
