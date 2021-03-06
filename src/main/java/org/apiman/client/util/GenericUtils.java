package org.apiman.client.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.google.common.net.UrlEscapers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class GenericUtils {

	private static final boolean FAIL_IF_VARIABLE_NOT_FOUND_IN_MAP = true;
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";//"yyyy-MM-dd'T'HH:mm:ss.SSSZ";

	public static enum INTERVAL {
		MONTH, WEEK, DAY, HOUR, MINUTE;

		public static String getDefault() {
			return DAY.toString();
		}
	};

	public static final synchronized String substitute(String templateString, Map<String, String> valuesMap,
			boolean encode) {

		valuesMap = encode ? encode(valuesMap) : valuesMap;
		StringSubstitutor substitutor = new StringSubstitutor(valuesMap, FAIL_IF_VARIABLE_NOT_FOUND_IN_MAP);
		String result = substitutor.replace(templateString);
		log.debug("Substitution : " + result);
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

		log.debug("buildURL     : " + url);
		return url;
	}

	public static final Map<String, String> encode(Map<String, String> valuesMap) {

		for (String key : valuesMap.keySet())
			valuesMap.put(key, UrlEscapers.urlFragmentEscaper().escape((valuesMap.get(key))));
		return valuesMap;
	}

	public static final String formatDate(Date date, String... format) {

		return new SimpleDateFormat(format.length > 0 ? format[0] : DEFAULT_DATE_FORMAT).format(date);
	}
}
