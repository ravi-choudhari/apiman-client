package org.apiman.client.domain.summary;

public enum METRICS_TYPE {

	CLIENT_RESPONSE_STATS("clientResponseStats"), CLIENT_USAGE("clientUsage"), PLAN_RESPONSE_STATS(
			"planResponseStats"), PLAN_USAGE("planUsage"), RESPONSE_STATS("responseStats"), SUMMARY_RESPONSE_STATS(
					"summaryResponseStats"), USAGE("usage"), API_USAGE("apiUsage");

	private String name;

	METRICS_TYPE(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
