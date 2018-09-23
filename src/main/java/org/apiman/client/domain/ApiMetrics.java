package org.apiman.client.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ApiMetrics {

	@Getter
	@AllArgsConstructor
	public enum METRICS_TYPE {
		
		CLIENT_RESPONSE_STATS("clientResponseStats"),
		CLIENT_USAGE("clientUsage"),
		PLAN_RESPONSE_STATS("planResponseStats"),
		PLAN_USAGE("planUsage"),
		RESPONSE_STATS("responseStats"),
		SUMMARY_RESPONSE_STATS("summaryResponseStats"),
		USAGE("usage");

		private String name;
	}
}
