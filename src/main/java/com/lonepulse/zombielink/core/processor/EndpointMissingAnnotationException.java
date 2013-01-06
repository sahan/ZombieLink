/**
 * 
 */
package com.lonepulse.zombielink.core.processor;

import com.lonepulse.zombielink.core.ZombieLinkRuntimeException;

/**
 * <p>This runtime exception is thrown when a required annotation is missing from 
 * the designated endpoint interface.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class EndpointMissingAnnotationException extends ZombieLinkRuntimeException {

	
	private static final long serialVersionUID = 4087362624687849076L;

	
	/**
	 * <p>Displays a detailed description along with the stacktrace. 
	 */
	public EndpointMissingAnnotationException(Class<?> endpointInterface, Class<?> missingAnnotation) {
		
		this("Missing annotation " + missingAnnotation.getName() + " on endpoint " + 
			  endpointInterface.getName());
	}
	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public EndpointMissingAnnotationException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public EndpointMissingAnnotationException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)}.
	 */
	public EndpointMissingAnnotationException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public EndpointMissingAnnotationException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
