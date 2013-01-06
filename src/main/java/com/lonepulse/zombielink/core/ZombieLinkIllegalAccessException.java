/**
 * 
 */
package com.lonepulse.zombielink.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * <p>This {@link ZombieLinkRuntimeException} is thrown when a {@link Field}, {@link Method} or 
 * {@link Constructor} on a {@link Class} cannot be accessed reflectively; or if the target 
 * {@link Class} itself cannot be accessed.
 * 
 * @see IllegalAccessException
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ZombieLinkIllegalAccessException extends ZombieLinkRuntimeException {


	private static final long serialVersionUID = -8300092576066969939L;

	
	/**
	 * <p>Displays a detailed description along with the stacktrace. 
	 */
	public ZombieLinkIllegalAccessException(Class<?> componentClass, IllegalAccessException rootCause) {
		
		this("Accessiblity failed on " + componentClass.getName() + 
			 ". Make sure that the invoked field, method or constructor is accessible.", rootCause);
	}
	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public ZombieLinkIllegalAccessException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public ZombieLinkIllegalAccessException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)}.
	 */
	public ZombieLinkIllegalAccessException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public ZombieLinkIllegalAccessException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
