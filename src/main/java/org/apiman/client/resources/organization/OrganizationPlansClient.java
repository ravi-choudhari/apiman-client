package org.apiman.client.resources.organization;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.ActivityList;
import org.apiman.client.domain.OrganizationPlan;
import org.apiman.client.domain.PlanVersion;
import org.apiman.client.domain.ReOrderPolicies;
import org.apiman.client.resources.organization.planpolicy.OrganizationPlansPoliciesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
@Component
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PRIVATE)
public class OrganizationPlansClient extends AbstractApimanClient {
	
	private static final String ORGANIZATION_PLANS_VERSION_PATH = ORGANIZATION_PLANS_PATH + "/${planId}/versions";
	
	@Autowired
	private OrganizationPlansPoliciesClient plansPoliciesClient;
	
	/* Use this endpoint to get a list of all Plans in the Organization.
	 * 
	 */
	public List<OrganizationPlan> listPlans(String organizationId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_PLANS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map);
		
		OrganizationPlan[] organizationPlans = restTemplate.getForObject(url, OrganizationPlan[].class);
		return organizationPlans != null ? Arrays.asList(organizationPlans) : null;
	}
	
	/* Use this endpoint to create a new Plan. Note that it is important to also create an initial version of the Plan (e.g. 1.0). 
	 * This can either be done by including the 'initialVersion' property in the request, or by immediately following up with a call 
	 * to "Create Plan Version". If the former is done, then a first Plan version will be created automatically by this endpoint.
	 */
	public OrganizationPlan createPlan(String organizationId, OrganizationPlan organizationPlan) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_PLANS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map);
		
		return restTemplate.postForObject(url, organizationPlan, OrganizationPlan.class);
	}
	
	/* Use this endpoint to retrieve information about a single Plan by ID. Note that this only returns information about the Plan, 
	 * not about any particular *version* of the Plan.
	 */
	public OrganizationPlan getPlanById(String organizationId, String planId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_PLANS_PATH, "/${planId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, OrganizationPlan.class);
	}
	
	/* Use this endpoint to update information about a Plan.
	 * 
	 */
	public void updatePlan(String organizationId, String planId, OrganizationPlan organizationPlan) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_PLANS_PATH, "/${planId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		url = substitute(url, map);
		
		restTemplate.exchange(url, PUT, new HttpEntity<OrganizationPlan>(organizationPlan, getHeaders()), Void.class);
	}
	
	/* Use this endpoint to delete a plan. Only an unlocked plan may be deleted.
	 * 
	 */
	public void deletePlan(String organizationId, String planId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_PLANS_PATH, "/${planId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		url = substitute(url, map);
		
		restTemplate.delete(url);
	}
	
	/* This endpoint returns audit activity information about the Plan.
	 * 
	 */
	public ActivityList getPlanActivity(String organizationId, String planId, int page, int count) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_PLANS_PATH, "/${planId}", ACTIVITY_PATH, PAGE_NUMBER_AND_COUNT);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		map.put("pageNumber", String.valueOf(page != 0 ? page : DEFAULT_VALUES.PAGE_NUMBER.getValue()));
		map.put("countPerPage", String.valueOf(count != 0 ? count : DEFAULT_VALUES.COUNT_PER_PAGE.getValue()));
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, ActivityList.class);
	}
	
	/* Use this endpoint to list all of the versions of a Plan.
	 * 
	 */
	public List<PlanVersion> listPlanVersions(String organizationId, String planId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_PLANS_VERSION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		url = substitute(url, map);
		
		PlanVersion[] planVersions = restTemplate.getForObject(url, PlanVersion[].class);
		return planVersions != null ? Arrays.asList(planVersions) : null;
	}
	
	/* Use this endpoint to create a new version of the Plan.
	 * 
	 */
	public PlanVersion createPlanVersion(String organizationId, String planId, PlanVersion planVersion) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_PLANS_VERSION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		url = substitute(url, map);
		
		return restTemplate.postForObject(url, planVersion, PlanVersion.class);
	}
	
	/* Use this endpoint to get detailed information about a single version of a Plan.
	 * 
	 */
	public PlanVersion getPlanVersion(String organizationId, String planId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_PLANS_VERSION_PATH, "/${version}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		map.put("version", version);
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, PlanVersion.class);
	}
	
	/* Use this endpoint to get audit activity information for a single version of the Plan.
	 * 
	 */
	public ActivityList getPlanVersionActivity(String organizationId, String planId, String version, int page, int count) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_PLANS_VERSION_PATH, "/${version}", ACTIVITY_PATH, PAGE_NUMBER_AND_COUNT);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		map.put("version", version);
		map.put("pageNumber", String.valueOf(page != 0 ? page : DEFAULT_VALUES.PAGE_NUMBER.getValue()));
		map.put("countPerPage", String.valueOf(count != 0 ? count : DEFAULT_VALUES.COUNT_PER_PAGE.getValue()));
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, ActivityList.class);
	}
	
	/* Use this endpoint to change the order of Policies for a Plan. When a Policy is added to the Plan, it is added as the last 
	 * Policy in the list of Plan Policies. Sometimes the order of Policies is important, so it is often useful to re-order the 
	 * Policies by invoking this endpoint. The body of the request should include all of the Policies for the Plan, in the new 
	 * desired order. Note that only the IDs of each of the Policies is actually required in the request, at a minimum.
	 */
	public void reorderPlanPolicies(String organizationId, String planId, String version, ReOrderPolicies reOrderPolicies) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_PLANS_VERSION_PATH, "/${version}", REORDER_POLICIES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		map.put("version", version);
		url = substitute(url, map);
		
		restTemplate.postForObject(url, reOrderPolicies, Void.class);
	}
}
