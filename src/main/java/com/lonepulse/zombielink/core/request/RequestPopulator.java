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


import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;

/**
 * <p>This contract specifies a strategy for populating the <i>parameters</i> on an HTTP request.</p> 
 * 
 * <p>All implementations must be aware of the {@link ProxyInvocationConfiguration} which can be used 
 * to discover metadata about the endpoint and the request declaration. This information can be queried 
 * based on the targeting criteria for this request populator and the resulting information should be 
 * used to <i>build upon</i> the given {@link HttpRequest}.</p>
 * 
 * <p>It is advised to follow <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html">Section 
 * 9</a> of the <b>HTTP 1.1</b> RFC when designing an implementation.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface RequestPopulator {

	/**
	 * <p>Takes the {@link ProxyInvocationConfiguration} for the given {@link HttpRequestBase} and uses the 
	 * metadata contained within the configuration to <i>build upon</i> the request.</p>
	 * 
	 * <p>The provided {@link HttpRequestBase} will be a concrete implementation which coincides with one of 
	 * the {@link RequestMethod}s, such as {@link HttpGet} or {@link HttpPut}. It would be sensible to check 
	 * the type of the request-method so that you treat each request in a way that complies with 
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html">Section 9</a> of the <b>HTTP 1.1</b> 
	 * RFC when designing an implementation.</p>   
	 * 
	 * @param httpRequestBase
	 * 			a concrete implementation of {@link HttpRequestBase}, such as {@link HttpGet} which should 
	 * 			to grow on based on the targeting criteria for this request populator 
	 *
	 * @param config
	 * 			the {@link ProxyInvocationConfiguration} which is used to discover the request's 
	 * 			{@link RequestMethod} and any metadata along with the actual data to populate 
 	 *
	 * @return an {@link HttpRequestBase} which coincides with the request's {@link RequestMethod}, with 
	 * 		   some content that may have been inserted based on the targeting criteria for this populator 
	 * 
	 * @throws RequestPopulatorException
	 * 			if the populator finds an {@link HttpRequestBase} <i>which it should act upon</i> and yet 
	 * 			fails to perform the necessary population 
	 * 
	 * @since 1.2.4
	 */
	HttpRequestBase populate(HttpRequestBase httpRequestBase, ProxyInvocationConfiguration config) throws RequestPopulatorException;
}
