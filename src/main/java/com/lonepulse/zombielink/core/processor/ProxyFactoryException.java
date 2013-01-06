/**
 * 
 */
package com.lonepulse.zombielink.core.processor;

import com.lonepulse.zombielink.core.ZombieLinkRuntimeException;

/**
 * <p>This runtime exception is thrown whenever a failure occurs in creating 
 * a proxy via an instance of {@link ProxyFactory}.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ProxyFactoryException extends ZombieLinkRuntimeException {


	private static final long serialVersionUID = -1466493374397626604L;

	
	/**
	 * <p>Displays a detailed description along with the stacktrace. 
	 */
	public ProxyFactoryException(Class<?> proxyFactoryInstance, Throwable rootCause) {
		
		this("Error in proxy factory " + proxyFactoryInstance.getName(), rootCause);
	}
	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public ProxyFactoryException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public ProxyFactoryException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)}.
	 */
	public ProxyFactoryException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public ProxyFactoryException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
