package org.apiman.client.util;

public class CannotResolveVariableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4296342044111482285L;

	public CannotResolveVariableException(String variableName) {
		super("Cannot resolve variable '" + variableName + "'.");
	}	
}
