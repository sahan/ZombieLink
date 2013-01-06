/**
 * 
 */
package com.lonepulse.zombielink.core.processor;

import java.lang.reflect.Method;

import com.lonepulse.zombielink.core.ZombieLinkRuntimeException;

/**
 * <p>This runtime exception is thrown when a header parameter annotation is not 
 * marked on a variable of type {@link StringBuilder}.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class HeaderParamTypeException extends ZombieLinkRuntimeException {

	
	private static final long serialVersionUID = -8060844427557378441L;

	
	/**
	 * <p>Displays a detailed description along with the stacktrace. 
	 */
	public HeaderParamTypeException(Object param, Method method) {
		
		this("Variable header parameters should be of type " + StringBuilder.class.getSimpleName() + 
			 ". Instead type " + param.getClass().getName() + " was found on request " + method.getName());
	}
	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public HeaderParamTypeException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public HeaderParamTypeException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)}.
	 */
	public HeaderParamTypeException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public HeaderParamTypeException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
