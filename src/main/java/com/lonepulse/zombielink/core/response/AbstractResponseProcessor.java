package com.lonepulse.zombielink.core.response;

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


import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import com.lonepulse.zombielink.core.processor.Processor;
import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;
import com.lonepulse.zombielink.core.request.RequestProcessorException;

/**
 * <p>This is an abstract implementation of {@link Processor} which specifies a template for processing the 
 * <i>response</i> of a request execution by referencing the <i>metadata</i> on a proxy endpoint <b>request</b>. 
 * It includes an implementation of {@link Processor#run(Object...)} that checks the preconditions for executing 
 * {@link #process(HttpRequestBase, ProxyInvocationConfiguration)}.</p>
 * 
 * <p>All implementations must be aware of the {@link ProxyInvocationConfiguration} which can be used to discover 
 * information about the endpoint and the request declaration. This information can be queried based on the 
 * <i>targeting criteria</i> for this reponse processor and the resulting information should be used to <i>parse</i> 
 * the given {@link HttpResponse}.</p>
 * 
 * <p>It is advised to adhere to <a href="www.w3.org/Protocols/rfc2616/rfc2616.html‎">RFC 2616</a> of <b>HTTP 1.1</b> 
 * when designing an implementation.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class AbstractResponseProcessor implements Processor<Map<String, Object>, ResponseProcessorException> {

	
	/**
	 * <p>Accepts an {@link HttpResponse} and a {@link ProxyInvocationConfiguration}, validates all preconditions 
	 * and uses the metadata contained within the configuration to process and subsequently parse the request. Any 
	 * implementations that wish to check additional preconditions or those that wish to alter this basic approach 
	 * should override this method.</p>
	 * 
	 * <p><b>Note</b> that this method should return an <i>unmodifiable</i> {@link Map} of results which contains 
	 * the <i>parsed response</i> and may include additional content such as the {@link HttpResponse} itself. Chains 
	 * which use processors of this type must clearly document the final result-map with the keys to be used.</p>
	 * 
	 * <p>Delegates to {@link #process(HttpResponse, ProxyInvocationConfiguration)}.</p>
	 * 
	 * <p>See {@link Processor#run(Object...)}.</p>
	 *
	 * @param args
	 * 			a array of <b>length 3</b> with an {@link HttpResponse}, a {@link ProxyInvocationConfiguration} and 
	 * 			the aggregated result-map in that <b>exact order</b> 
	 * <br><br>
	 * @return an <i>unmodifiable</i> {@link Map} of results containing the <i>parsed response</i> including any 
	 * 		   additional content such as the {@link HttpResponse}
	 * <br><br>
	 * @throws IllegalArgumentException
	 * 			if the supplied arguments array is {@code null} or if the number of arguments does not equal 2, 
	 * 			or if the arguments are not of the expected type
	 * <br><br>
	 * @throws RequestProcessorException
	 * 			if {@link #process(HttpResponse, ProxyInvocationConfiguration)} failed for the given 
	 * 			{@link HttpResponse} and {@link ProxyInvocationConfiguration}
	 * <br><br>
	 * @since 1.2.4
	 */
	@Override @SuppressWarnings("unchecked") //checked cast from Object to Map<String, Object>
	public Map<String, Object> run(Object... args) throws ResponseProcessorException {

		if(args == null || args.length != 3) {
			
			StringBuilder errorContext = new StringBuilder("An ")
			.append(AbstractResponseProcessor.class.getName())
			.append(" requires exactly three arguments: the ")
			.append(HttpResponse.class.getName())
			.append(" which it should process, the ")
			.append(ProxyInvocationConfiguration.class.getName())
			.append(" which provides the data and metadata for processing and a ")
			.append(Map.class.getName())
			.append(" of aggregated processor results. ");
			
			throw new IllegalArgumentException(errorContext.toString());
		}
		
		StringBuilder accumulatedContext = new StringBuilder();
		boolean hasIllegalArguments = false;
		
		if(args[0] == null || !(args[0] instanceof HttpRequestBase)) {
			
			accumulatedContext.append("The first argument should be an instance of ")
			.append(HttpResponse.class.getName())
			.append(" which cannot be <null>. ");
			
			hasIllegalArguments = true;
		}
		
		if(args[1] == null || !(args[1] instanceof ProxyInvocationConfiguration)) {
			
			accumulatedContext.append("The second argument to should be an instance of ")
			.append(ProxyInvocationConfiguration.class.getName())
			.append(" which cannot be <null>. ");
			
			hasIllegalArguments = true;
		}
		
		Map<String, Object> resultMap = null;
		
		if(args[2] == null || !(args[2] instanceof Map)) {
			
			accumulatedContext.append("The third argument to should be an instance of type java.util.Map")
			.append(" which cannot be <null>. ");
			
			hasIllegalArguments = true;
		}
		else {
			
			try {
		
				resultMap = (Map<String, Object>)args[2];
				
				for (@SuppressWarnings("unused") String key : resultMap.keySet());
			}
			catch(ClassCastException cce) {
				
				accumulatedContext.append("The third argument should be of type java.util.Map<String, Object>")
				.append(" and \"ensure\" that all its keys are of type java.lang.String ");
				
				hasIllegalArguments = true;
			}
		}
		
		if(hasIllegalArguments) {
			
			throw new IllegalArgumentException(accumulatedContext.toString());
		}
		
		return process((HttpResponse)args[0], (ProxyInvocationConfiguration)args[1], resultMap);
	}
	
	/**
	 * <p>Takes the {@link ProxyInvocationConfiguration} for the given {@link HttpResponse} and uses the 
	 * metadata contained within the configuration to <i>parse</i> the <i>response body</i> and perform 
	 * additional processing based on the <i>response headers</i>.</p>
	 * 
	 * <p>The provided {@link HttpResponse} may contain a response entity which should be parsed to the 
	 * correct type and it may contain certain essential response headers which should be processed. Any 
	 * implementation may wish to perform processing conditionally based on the response code. Refer 
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html">Section 9</a> of the <b>HTTP 1.1</b> 
	 * for more information.</p>
	 * 
	 * @param HttpResponse
	 * 			the {@link HttpResponse} received as result of a request execution; the response body should 
	 * 			be parsed to the correct type and the response headers should be processed if required 
	 * <br><br>
	 * @param config
	 * 			the {@link ProxyInvocationConfiguration} which is used to discover any annotated metadata 
	 * 			for the request declarion which may help in processing the response and making the necessary 
	 * 			information available for the result-map
	 * <br><br>
	 * @param results
	 * 			the result-map which contains an aggregation of all the results produced by the chain insofar 
	 * 			and serves as the container for the results produced by this processor
 	 * <br><br>
 	 * @return the result-map which contains the all the results aggregated over the chain so far - including 
 	 * 		   the results of the current processor
 	 * <br><br>
	 * @throws RequestProcessorException
	 * 			if the processor finds an {@link HttpResponse} <i>which it should act upon</i> and yet 
	 * 			fails to perform the necessary processing
	 * <br><br>
	 * @since 1.2.4
	 */
	protected abstract Map<String, Object> process(HttpResponse HttpResponse, ProxyInvocationConfiguration config, Map<String, Object> results)
	throws ResponseProcessorException;
}
