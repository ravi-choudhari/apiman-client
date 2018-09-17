package org.apiman.client;

import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

public class GenericUtils {

	public static String substitute(String templateString, Map<String, String> valuesMap) {

		StringSubstitutor substitutor = new StringSubstitutor(valuesMap);
		String resolvedString = substitutor.replace(templateString);
		return resolvedString;
	}
}
