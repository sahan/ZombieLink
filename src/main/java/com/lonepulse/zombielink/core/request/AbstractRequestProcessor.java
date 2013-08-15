package com.lonepulse.zombielink.core.request;

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


import org.apache.http.client.methods.HttpRequestBase;

import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;

/**
 * <p>An abstract implementation of {@link RequestProcessor} which provides and implementation of 
 * {@link RequestProcessor#run(Object...)} that checks the preconditions for executing 
 * {@link #process(HttpRequestBase, ProxyInvocationConfiguration)}.
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class AbstractRequestProcessor implements RequestProcessor {

	
	/**
	 * <p>Accepts an {@link HttpRequestBase} and a {@link ProxyInvocationConfiguration}, validates all preconditions 
	 * and uses the metadata contained within the configuration to process and subsequently build upon the request. 
	 * Any implementations that wish to check additional preconditions or those that wish to alter this basic approach 
	 * should override this method.</p>
	 * 
	 * <p><b>Note</b> that this method returns {@code null} for all intents and purposes.</p>
	 * 
	 * <p>Delegates to {@link #process(HttpRequestBase, ProxyInvocationConfiguration)}.</p>
	 * 
	 * <p>See {@link RequestProcessor#run(Object...)}.</p>
	 *
	 * @param args
	 * 			a array of <b>length 2</b> with an {@link HttpRequestBase} and a {@link ProxyInvocationConfiguration} 
	 * 			in that <b>exact order</b> 
	 * 
	 * @return {@code null} for all intents and purposes; implementations should process the original instance of 
	 * 		   {@link HttpRequestBase} without recreating or reusing a separate instance with similar properties
	 * 
	 * @throws IllegalArgumentException
	 * 			
	 * 
	 * @throws RequestProcessorException
	 * 			if {@link #process(HttpRequestBase, ProxyInvocationConfiguration)} failed for the given 
	 * 			{@link HttpRequestBase} and {@link ProxyInvocationConfiguration}
	 * 
	 * @since 1.2.4
	 */
	@Override
	public Void run(Object... args) throws RequestProcessorException {

		if(args == null || args.length != 2) {
			
			StringBuilder errorContext = new StringBuilder("A ")
			.append(RequestProcessor.class.getName())
			.append(" requires exactly two arguments: the ")
			.append(HttpRequestBase.class.getName())
			.append(" which it should process and the ")
			.append(ProxyInvocationConfiguration.class.getName())
			.append(" which provides the data and metadata for processing. ");
			
			throw new IllegalArgumentException(errorContext.toString());
		}
		
		StringBuilder accumulatedContext = new StringBuilder();
		boolean hasIllegalArguments = false;
		
		if(args[0] == null || !(args[0] instanceof HttpRequestBase)) {
			
			accumulatedContext.append("The first argument should be an instance of ")
			.append(HttpRequestBase.class.getName())
			.append(" which cannot be <null>. ");
			
			hasIllegalArguments = true;
		}
		
		if(args[1] == null || !(args[1] instanceof ProxyInvocationConfiguration)) {
			
			accumulatedContext.append("The second argument to should be an instance of ")
			.append(ProxyInvocationConfiguration.class.getName())
			.append(" which cannot be <null>. ");
			
			hasIllegalArguments = true;
		}
		
		if(hasIllegalArguments) {
			
			throw new IllegalArgumentException(accumulatedContext.toString());
		}
		
		process((HttpRequestBase)args[0], (ProxyInvocationConfiguration)args[1]);
		
		return null;
	}
}
