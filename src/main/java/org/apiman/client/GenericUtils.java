package org.apiman.client;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.text.StringSubstitutor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericUtils {

	public static final synchronized String substitute(String templateString, Map<String, String> valuesMap) {

		valuesMap = encode(valuesMap);
		StringSubstitutor substitutor = new StringSubstitutor(valuesMap);
		
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
	
	public static void main(String[] args) {
		String url = buildURL("/${gatewayId}");
		Map<String, String> map = new HashMap<>();
		map.put("gatewayId", "gateway 2");
		System.out.println(substitute(url, map));
	}
}
