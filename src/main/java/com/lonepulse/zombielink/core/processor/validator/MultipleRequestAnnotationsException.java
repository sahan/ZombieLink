package com.lonepulse.zombielink.core.processor.validator;

import com.lonepulse.zombielink.core.annotation.Request;
import com.lonepulse.zombielink.rest.annotation.Rest;

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
 * <p>This runtime exception is thrown when <b>both</b> @{@link Request} and @{@link Rest} annotations 
 * are found on an endpoint method definition.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class MultipleRequestAnnotationsException extends RequestValidationFailedException {

	
	private static final long serialVersionUID = 1156746044864241840L;
	

	/**
	 * <p>Displays a detailed description along with the stacktrace.
	 * 
	 * @since 1.2.4
	 */
	public MultipleRequestAnnotationsException() {
		
		this("An endpoint method definition cannot be annotated with both @" + 
			 Request.class.getName() + " and @" + Rest.class.getName());
	}

	/**
	 * See {@link RequestValidationFailedException#RequestValidationFailedException(String)}.
	 * 
	 * @since 1.2.4
	 */
	public MultipleRequestAnnotationsException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RequestValidationFailedException#RequestValidationFailedException(Throwable)}.
	 * 
	 * @since 1.2.4
	 */
	public MultipleRequestAnnotationsException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * <p>Displays a detailed description along with the root cause.
	 * 
	 * @since 1.2.4
	 */
	public MultipleRequestAnnotationsException(String detailMessage, Throwable throwable) {

		super("An endpoint method definition cannot be annotated with both @" + 
			  Request.class.getName() + " and @" + Rest.class.getName(), throwable);
	}
}
