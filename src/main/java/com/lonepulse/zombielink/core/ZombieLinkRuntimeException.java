/**
 * 
 */
package com.lonepulse.zombielink.core;

/**
 * <p>An extension of {@link RuntimeException} which marks unrecoverable 
 * runtime discrepancies.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ZombieLinkRuntimeException extends RuntimeException {


	private static final long serialVersionUID = 6349350227556147057L;

	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public ZombieLinkRuntimeException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public ZombieLinkRuntimeException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)}.
	 */
	public ZombieLinkRuntimeException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public ZombieLinkRuntimeException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
