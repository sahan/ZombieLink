/**
 * 
 */
package com.lonepulse.zombielink.core.inject;

import java.lang.reflect.Field;

import com.lonepulse.zombielink.core.ZombieLinkRuntimeException;
import com.lonepulse.zombielink.core.processor.ProxyFactory;

/**
 * <p>This runtime exception is thrown whenever a failure occurs in creating 
 * a proxy via an instance of {@link ProxyFactory}.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class InjectionException extends ZombieLinkRuntimeException {


	private static final long serialVersionUID = -1466493374397626604L;

	
	/**
	 * <p>Displays a detailed description along with the stacktrace.
	 */
	public InjectionException(Field field, Class<?> injectee, Class<?> endpoint, Throwable cause) {
		
		this("Failed to inject the endpoint proxy instance of type " + endpoint.getName() + 
			 " on member " + field.getName() + " at " + injectee.getName(), cause);
	}
	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public InjectionException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public InjectionException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)}.
	 */
	public InjectionException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public InjectionException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
