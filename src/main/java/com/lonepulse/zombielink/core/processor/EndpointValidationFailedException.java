package com.lonepulse.zombielink.core.processor;

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

import com.lonepulse.zombielink.core.ZombieLinkRuntimeException;

/**
 * <p>This runtime exception is thrown when a validation fails on a particular 
 * endpoint interface's {@link Class}.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class EndpointValidationFailedException extends ZombieLinkRuntimeException {

	
	private static final long serialVersionUID = 3284104184402594315L;

	
	/**
	 * <p>Displays a detailed description along with the stacktrace. 
	 */
	public EndpointValidationFailedException(Class<?> endpointInterface, Throwable throwable) {
		
		this("Validation failed for endpoint " + endpointInterface.getName(), throwable);
	}
	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public EndpointValidationFailedException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public EndpointValidationFailedException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)}.
	 */
	public EndpointValidationFailedException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public EndpointValidationFailedException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
