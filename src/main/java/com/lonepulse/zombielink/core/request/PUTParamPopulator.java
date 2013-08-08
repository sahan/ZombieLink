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


import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;

import com.lonepulse.zombielink.core.annotation.Entity;
import com.lonepulse.zombielink.core.annotation.Param;
import com.lonepulse.zombielink.core.annotation.Request;
import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;
import com.lonepulse.zombielink.rest.annotation.Rest;

/**
 * <p>This is a concrete implementation of {@link RequestPopulator} which serves {@link Request}s and 
 * {@link Rest}ful requests that employ {@link RequestMethod#PUT}.</p>
 * 
 * <p>It identifies an @{@link Param} annotation on a parameter of an endpoint interface method and 
 * inserts the value as a form entity in the resulting {@link HttpPut} request.
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class PUTParamPopulator implements RequestPopulator {

	
	/**
	 * <p>Accepts the {@link ProxyInvocationConfiguration} of a request which uses {@link RequestMethod#PUT} 
	 * and inserts any request parameters which were annotated with  @{@link Param} into the body of the resulting 
	 * {@link HttpPut} request as its entity.</p>
	 * 
	 * <p>It makes no sense to scope multiple entities within the same PUT request (W3 
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html">RFC-2616</a>). Hence the first @{@link Param} 
	 * annotated entity is taken and the rest is discarded.</p> 
	 * 
	 * <p>Parameter types are resolved to their {@link HttpEntity} as specified in {@link RequestUtils#resolveHttpEntity(Object)}.
	 * 
	 * <p>See {@link RequestPopulator#populate(ProxyInvocationConfiguration)}.</p>
	 * 
	 * @param config
	 * 			an immutable instance of {@link ProxyInvocationConfiguration} which is used to retrieve the form 
	 * 			entity and create the {@link HttpPut} request
	 * 
	 * @return an instance of {@link HttpPost} with a body that may include an @{@link Param} annotated parameter 
	 * 		   as the form entity
	 * 
	 * @throws RequestPopulatorException
	 * 			if an {@link HttpPost} instance failed to be created or if a query parameter failed to be inserted
	 * 
	 * @since 1.2.4
	 */
	@Override
	public HttpRequestBase populate(ProxyInvocationConfiguration config) throws RequestPopulatorException {

		try {
			
			HttpPut httpPut = new HttpPut(config.getUri());
			
			HttpEntity httpEntity = RequestUtils.findAndResolveEntity(config);
			
			httpPut.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.getOrDefault(httpEntity).getMimeType());
			httpPut.setEntity(httpEntity);
			
			return httpPut;
		}
		catch(MissingEntityException mee) { //violates HTTP 1.1 specification, be more verbose 
			
			StringBuilder errorContext = new StringBuilder("It is imperative that a PUT request encloses an entity. ")
			.append("Identify exactly one entity by annotating an argument with @")
			.append(Entity.class.getSimpleName());
			
			throw new RequestPopulatorException(errorContext.toString(), mee);
		}
		catch(MultipleEntityException mee) { //violates HTTP 1.1 specification, be more verbose 
			
			StringBuilder errorContext = new StringBuilder("A PUT request is only able to enclose exactly one entity. ")
			.append("Remove all @")
			.append(Entity.class.getSimpleName())
			.append(" annotations except for a single entity which is identified by this URI. ");
			
			throw new RequestPopulatorException(errorContext.toString(), mee);
		}
		catch (EntityResolutionFailedException erfe) { //violates HTTP 1.1 specification, be more verbose
			
			StringBuilder errorContext = new StringBuilder("A PUT request cannot proceed without an enclosing entity. ")
			.append(" Ensure that the entity which is annotated with ")
			.append(Entity.class.getSimpleName())
			.append(" complies with the supported types as documented in ")
			.append(RequestUtils.class.getName())
			.append("#resolveHttpEntity(Object)");
			
			throw new RequestPopulatorException(errorContext.toString(), erfe); 
		}
		catch(Exception e) {
			
			throw new RequestPopulatorException(e);
		}
	}
}
