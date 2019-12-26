package org.apiman.client.resources.organization.apis;

import java.util.Date;

import org.apiman.client.domain.summary.ResponseStats;
import org.apiman.client.domain.summary.ResponseStatsHistogram;
import org.apiman.client.domain.summary.ResponseStatsSummary;
import org.apiman.client.domain.summary.Usage;
import org.apiman.client.domain.summary.UsageHistogram;

public interface IOrganizationApiMetricsClient {

	ResponseStats getApiResponseStatisticsPerClient(String organizationId, String apiId, String version, Date fromDate,
			Date toDate);

	Usage getApiUsageMetricsPerClient(String organizationId, String apiId, String version, Date fromDate, Date toDate);

	ResponseStats getApiResponseStatisticsPerPlan(String organizationId, String apiId, String version, Date fromDate,
			Date toDate);

	Usage getApiUsageMetricsPerPlan(String organizationId, String apiId, String version, Date fromDate, Date toDate);

	ResponseStatsHistogram getApiResponseStatistics(String organizationId, String apiId, String version, Date fromDate,
			Date toDate);

	ResponseStatsSummary getApiResponseStatisticsSummary(String organizationId, String apiId, String version,
			Date fromDate, Date toDate, String interval);

	UsageHistogram getApiUsageMetrics(String organizationId, String apiId, String version, Date fromDate, Date toDate,
			String interval);

}
