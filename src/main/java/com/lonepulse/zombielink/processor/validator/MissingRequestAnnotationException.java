package com.lonepulse.zombielink.processor.validator;

import com.lonepulse.zombielink.annotation.Request;

/*
 * #%L
 * ZombieLink
 * %%
 * Copyright (C) 2013 Lonepulse
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


/**
 * <p>This runtime exception is thrown when an @{@link Request} annotation is not found on an 
 * endpoint method definition.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class MissingRequestAnnotationException extends RequestValidationFailedException {

	
	private static final long serialVersionUID = -1394041181643064582L;
	

	/**
	 * <p>Displays a detailed description along with the stacktrace.
	 * 
	 * @since 1.2.4
	 */
	public MissingRequestAnnotationException() {
		
		this(new StringBuilder("An endpoint method definition requires an @")
			 .append(Request.class.getName()).append(" annotation. ").toString());
	}

	/**
	 * See {@link RequestValidationFailedException#RequestValidationFailedException(String)}.
	 */
	public MissingRequestAnnotationException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RequestValidationFailedException#RequestValidationFailedException(Throwable)}.
	 */
	public MissingRequestAnnotationException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * <p>Displays a detailed description along with the root cause.
	 */
	public MissingRequestAnnotationException(String detailMessage, Throwable rootCause) {

		super(new StringBuilder("An endpoint method definition requires an @")
		 	  .append(Request.class.getName()).append(" annotation. ").toString(), rootCause);
	}
}