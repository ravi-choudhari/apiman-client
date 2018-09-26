package org.apiman.client.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.net.URLCodec;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericUtils {

	private static final boolean FAIL_IF_VARIABLE_NOT_FOUND_IN_MAP = true;
	public static final String DEFAULT_DATE_FORMAT = "";
	
	public static enum INTERVAL {
		MONTH,
		WEEK,
		DAY,
		HOUR;
		
		public static String getDefault() {
			return DAY.toString();
		}
	};

	public static final synchronized String substitute(String templateString, Map<String, String> valuesMap) {
		
		valuesMap = encode(valuesMap);
		StringSubstitutor substitutor = new StringSubstitutor(valuesMap, FAIL_IF_VARIABLE_NOT_FOUND_IN_MAP);
		String result = substitutor.replace(templateString);
		System.out.println("Substitution : " + result);
		return result;
	}

	public static final synchronized String buildURL(String... pathVariables) {

		if (pathVariables == null) {
			return null;
		}

		StringBuilder strBuilder = new StringBuilder();
		for (String pathVariable : pathVariables) {
			strBuilder.append(pathVariable);
		}
		String url = strBuilder.toString();
		
		System.out.println("buildURL     : " + url);
		return url;
	}

	private static final String encode(String url) {

		try {
			return new URLCodec().encode(url, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final Map<String, String> encode(Map<String, String> valuesMap) {

		for(String key : valuesMap.keySet()) valuesMap.put(key, encode(valuesMap.get(key)));
		return valuesMap;
	}
	
	public static final String formatDate(Date date, String... format) {
		
		return new SimpleDateFormat(format.length > 0 ? format[0] : DEFAULT_DATE_FORMAT).format(date);
	}
}
