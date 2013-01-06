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
public class ParamPopulatorException extends ZombieLinkRuntimeException {

	
	private static final long serialVersionUID = -1466493374397626604L;

	
	/**
	 * <p>Displays a detailed description along with the stacktrace.
	 */
	public ParamPopulatorException(Class<?> paramPopulatorClass, 
									ProxyInvocationConfiguration config, 
									Throwable throwable) {
	
		this(paramPopulatorClass.getName() + " was unable to populate request with configuration " + config, throwable);
	}
	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public ParamPopulatorException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public ParamPopulatorException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)}.
	 */
	public ParamPopulatorException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public ParamPopulatorException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
