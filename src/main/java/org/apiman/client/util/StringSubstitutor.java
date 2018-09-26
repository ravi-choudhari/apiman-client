package org.apiman.client.util;

import java.util.Map;

import org.apache.commons.text.TextStringBuilder;
import org.apache.commons.text.lookup.StringLookup;

public class StringSubstitutor extends org.apache.commons.text.StringSubstitutor {

	private boolean failIfKeyNotFound = false;

	public StringSubstitutor(Map<String, String> valuesMap, boolean fail) {

		super(valuesMap);
		this.failIfKeyNotFound = fail;
	}

	public String resolveVariable(final String variableName, final TextStringBuilder buf, final int startPos,
			final int endPos) {
		final StringLookup resolver = getStringLookup();
		if (resolver == null) {
			return null;
		}

		String value = resolver.lookup(variableName);
		if (!failIfKeyNotFound || (failIfKeyNotFound && value != null))
			return value;
		else
			throw new CannotResolveVariableException(variableName);
	}
}
