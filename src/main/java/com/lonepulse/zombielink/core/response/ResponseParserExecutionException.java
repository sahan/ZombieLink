/**
 * 
 */
package com.lonepulse.zombielink.core.response;

import org.apache.http.HttpResponse;

import com.lonepulse.zombielink.core.ZombieLinkRuntimeException;

/**
 * <p>This runtime exception is thrown whenever there is a failure in parsing 
 * the content of an {@link HttpResponse} to the desired entity.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ResponseParserExecutionException extends ZombieLinkRuntimeException {

	
	private static final long serialVersionUID = 8193182870145739105L;

	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public ResponseParserExecutionException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public ResponseParserExecutionException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)}.
	 */
	public ResponseParserExecutionException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public ResponseParserExecutionException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
