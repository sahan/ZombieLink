/**
 * 
 */
package com.lonepulse.zombielink.core.request;

import com.lonepulse.zombielink.core.ZombieLinkRuntimeException;
import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;

/**
 * <p>This runtime exception is thrown when an HTTP request cannot be created 
 * using a give configuration.   
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class RequestBuilderException extends ZombieLinkRuntimeException {

	
	private static final long serialVersionUID = -1466493374397626604L;

	
	/**
	 * <p>Displays a detailed description along with the stacktrace.
	 */
	public RequestBuilderException(Class<? extends AbstractRequestBuilder> requestCreatorClass, 
									ProxyInvocationConfiguration config, 
									Throwable throwable) {
	
		this(requestCreatorClass.getName() + " was unable to create request with configuration " + config, throwable);
	}
	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public RequestBuilderException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public RequestBuilderException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)}.
	 */
	public RequestBuilderException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public RequestBuilderException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
