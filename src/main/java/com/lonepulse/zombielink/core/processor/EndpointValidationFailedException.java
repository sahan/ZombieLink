/**
 * 
 */
package com.lonepulse.zombielink.core.processor;

import com.lonepulse.zombielink.core.ZombieLinkRuntimeException;

/**
 * <p>This runtime exception is thrown when a validation fails on a particular 
 * endpoint interface's {@link Class}.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class EndpointValidationFailedException extends ZombieLinkRuntimeException {

	
	private static final long serialVersionUID = 3284104184402594315L;

	
	/**
	 * <p>Displays a detailed description along with the stacktrace. 
	 */
	public EndpointValidationFailedException(Class<?> endpointInterface, Throwable throwable) {
		
		this("Validation failed for endpoint " + endpointInterface.getName(), throwable);
	}
	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public EndpointValidationFailedException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public EndpointValidationFailedException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)}.
	 */
	public EndpointValidationFailedException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public EndpointValidationFailedException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
