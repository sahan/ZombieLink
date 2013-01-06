/**
 * 
 */
package com.lonepulse.zombielink.core;

import java.lang.reflect.Constructor;

/**
 * <p>This {@link ZombieLinkRuntimeException} is thrown whenever a failure occurs in creating 
 * an instance via reflective invocation of it's default or parameterized constructor.
 * 
 * @see InstantiationException
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ZombieLinkInstantiationException extends ZombieLinkRuntimeException {


	private static final long serialVersionUID = -6336187359180038139L;

	
	/**
	 * <p>Displays a detailed description along with the stacktrace. 
	 */
	public ZombieLinkInstantiationException(Class<?> componentClass, InstantiationException rootCause) {
		
		this("Could not instantiate component " + componentClass.getName() + 
			 ". Make sure that a default constructor is available and accessible. Note that " + componentClass.getName() + 
			 " cannot be an abstract class, an interface class, " + "an array class, a primitive type, or void.", rootCause);
	}
	
	/**
	 * <p>Displays a detailed description along with the stacktrace. 
	 */
	public ZombieLinkInstantiationException(Class<?> componentClass, Constructor<?> constructor, InstantiationException rootCause) {
		
		this("Could not instantiate component " + componentClass.getName() + 
			 " via the parameterized constructor with arguments " + constructor.getParameterTypes() + 
			 ". Make sure that this constructor is available and accessible. Note that " + componentClass.getName() + 
			 " cannot be an abstract class, an interface class, an array class, a primitive type, or void.", rootCause);
	}
	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public ZombieLinkInstantiationException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public ZombieLinkInstantiationException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)}.
	 */
	public ZombieLinkInstantiationException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public ZombieLinkInstantiationException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
