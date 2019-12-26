package org.apiman.client.conditions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ApimanAdminCondition implements Condition {

	private static final String APIMAN_ADMIN_USERNAME = "apiman.admin.username";
	private static final String APIMAN_ADMIN_PASSWORD = "apiman.admin.password";

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

		Environment env = context.getEnvironment();
		return StringUtils.isNotBlank(env.getProperty(APIMAN_ADMIN_USERNAME))
				&& StringUtils.isNotBlank(env.getProperty(APIMAN_ADMIN_PASSWORD));
	}
}
