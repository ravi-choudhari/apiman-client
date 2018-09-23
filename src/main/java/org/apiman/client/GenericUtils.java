package org.apiman.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericUtils {

	public static final synchronized String substitute(String templateString, Map<String, String> valuesMap) {

		StringSubstitutor substitutor = new StringSubstitutor(valuesMap);
		return substitutor.replace(templateString);
	}

	public static final synchronized String buildURL(String... pathVariables) {

		if (pathVariables == null) {
			return null;
		}

		StringBuilder strBuilder = new StringBuilder();
		for (String pathVariable : pathVariables) {
			strBuilder.append(pathVariable);
		}
		return strBuilder.toString();
	}

	public static final String encode(String url) {

		try {
			return URLEncoder.encode(url, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
