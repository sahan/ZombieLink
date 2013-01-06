/**
 * 
 */
package com.lonepulse.zombielink.core.response;

import com.lonepulse.zombielink.core.ZombieLinkRuntimeException;
import com.lonepulse.zombielink.core.annotation.Parser;
import com.lonepulse.zombielink.core.annotation.Request;

/**
 * <p>This runtime exception is thrown when the return type of a {@link Request} method 
 * cannot be assigned to the designated {@link Parser}'s return type. 
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ResponseParserNotAssignableException extends ZombieLinkRuntimeException {


	private static final long serialVersionUID = -2526887708389941964L;

	
	/**
	 * <p>Displays a detailed description along with the stacktrace.
	 */
	public ResponseParserNotAssignableException(Class<?> parserReturnType, Class<?> requestReturnType) {
		
		this("Cannot assign the parser's response of type " + parserReturnType.getName() + 
			  " to an instance of the request return type " + requestReturnType.getName());
	}
	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public ResponseParserNotAssignableException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public ResponseParserNotAssignableException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)}.
	 */
	public ResponseParserNotAssignableException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public ResponseParserNotAssignableException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
