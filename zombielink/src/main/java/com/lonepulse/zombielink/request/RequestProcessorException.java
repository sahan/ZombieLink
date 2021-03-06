package com.lonepulse.zombielink.request;

/*
 * #%L
 * ZombieLink
 * %%
 * Copyright (C) 2013 - 2014 Lonepulse
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

import java.util.Arrays;

import org.apache.http.client.methods.HttpRequestBase;

import com.lonepulse.zombielink.ZombieLinkRuntimeException;
import com.lonepulse.zombielink.proxy.InvocationContext;

/**
 * <p>This runtime exception is thrown when a {@link AbstractRequestProcessor} fails to execute successfully 
 * for a given {@link InvocationContext} and {@link HttpRequestBase}.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.3.0
 * <br><br>
 * @author <a href="http://sahan.me">Lahiru Sahan Jayasinghe</a>
 */
class RequestProcessorException extends ZombieLinkRuntimeException {

	
	private static final long serialVersionUID = -1466493374397626604L;

	
	/**
	 * <p>Displays a detailed description with information on the failed {@link InvocationContext} and 
	 * {@link AbstractRequestProcessor}.</p>
	 * 
	 * @param context
	 * 			the {@link InvocationContext} which caused the {@link AbstractRequestProcessor} to fail
	 * <br><br>
	 * @param requestProcessorClass
	 * 			the {@link Class} of the {@link AbstractRequestProcessor} implementation which failed
	 * <br><br>
	 * @since 1.3.0
	 */
	public RequestProcessorException(InvocationContext context, Class<?> requestProcessorClass) {
	
		this(new StringBuilder(requestProcessorClass.getName())
			 .append(" failed to process the invocation context for request <")
			 .append(context.getRequest().getName())
			 .append("> with arguments ")
			 .append(Arrays.toString(context.getArguments().toArray())).toString());
	}
	
	/**
	 * <p>Displays a detailed description with information on the failed {@link AbstractRequestProcessor} 
	 * and {@link InvocationContext}, while preserving the stacktrace.</p>
	 * 
	 * @param context
	 * 			the {@link InvocationContext} which caused the {@link AbstractRequestProcessor} to fail
	 * <br><br>
	 * @param requestProcessorClass
	 * 			the {@link Class} of the {@link AbstractRequestProcessor} implementation which failed
	 * <br><br>
	 * @param rootCause
	 * 			the parent exception which caused the {@link AbstractRequestProcessor} to fail
	 * <br><br>
	 * @since 1.3.0
	 */
	public RequestProcessorException(InvocationContext context,
									 Class<?> requestProcessorClass, 
									 Throwable rootCause) {
		
		this(new StringBuilder(requestProcessorClass.getName())
			 .append(" failed to process the invocation context for request <")
			 .append(context.getRequest().getName())
			 .append("> with arguments ")
			 .append(Arrays.toString(context.getArguments().toArray())).toString(), rootCause);
	}
	
	/**
	 * See {@link ZombieLinkRuntimeException#ZombieLinkRuntimeException()}.
	 * <br><br>
	 * @since 1.3.0
	 */
	public RequestProcessorException() {}

	/**
	 * See {@link ZombieLinkRuntimeException#ZombieLinkRuntimeException(String)}.
	 * <br><br>
	 * @since 1.3.0
	 */
	public RequestProcessorException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link ZombieLinkRuntimeException#ZombieLinkRuntimeException(Throwable)}.
	 * <br><br>
	 * @since 1.3.0
	 */
	public RequestProcessorException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link ZombieLinkRuntimeException#ZombieLinkRuntimeException(String, Throwable)}.
	 * <br><br>
	 * @since 1.3.0
	 */
	public RequestProcessorException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
