package com.lonepulse.zombielink.rest.request;

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

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;
import com.lonepulse.zombielink.core.request.AbstractRequestBuilder;
import com.lonepulse.zombielink.core.request.HttpParamBuilder;
import com.lonepulse.zombielink.core.request.MissingRequestAnnotationException;
import com.lonepulse.zombielink.core.request.RequestMethod;
import com.lonepulse.zombielink.rest.annotation.PathParam;
import com.lonepulse.zombielink.rest.annotation.Rest;
import com.lonepulse.zombielink.util.AnnotationExtractor;

/**
 * <p>This is an implementation of {@link AbstractRequestBuilder} which handles the 
 * request creation for RESTful requests.
 * 
 * @version 1.2.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class RestfulRequestBuilder extends AbstractRequestBuilder {


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected URI buildPath(ProxyInvocationConfiguration config) throws Exception {

		return config.getUri(); //root path return as it is
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HttpRequestBase buildRequestWithParameters(URI uri, ProxyInvocationConfiguration config) 
	throws Exception {
		
		Method request = config.getRequest();
		Rest restfulRequest = request.getAnnotation(Rest.class);
		
		if(restfulRequest == null)
			throw new MissingRequestAnnotationException(request, Rest.class);
		
		String subpath = restfulRequest.path();
		
		Map<Object, PathParam> annotatedParams 
			= AnnotationExtractor.<PathParam>extractWithParameterValues(PathParam.class, request, config.getRequestArgs());
		
		Set<Object> methodParams = annotatedParams.keySet();
		
		for (Object paramValue : methodParams) {
			
			if(!(paramValue instanceof String))
				throw new IllegalArgumentException("Path parameters for RESTful requests can only be of type " + 
													String.class.getName());

			subpath = subpath.replaceAll(":" + annotatedParams.get(paramValue).value(), (String)paramValue);
		}
		
		URI uriWithPathParameters = new URI(uri.toASCIIString() + subpath);
		
		HttpRequestBase httpRequestBase  = HttpParamBuilder.build(uriWithPathParameters, config);
		
		RequestMethod httpMethod = restfulRequest.method();
		
		switch (httpMethod) {
		
			case HTTP_GET: { 
				
				return new HttpGet(httpRequestBase.getURI());
			}
			case HTTP_POST: { 
				
				return new HttpPost(httpRequestBase.getURI());
			}
			case HTTP_PUT: { 
				
				return new HttpPut(httpRequestBase.getURI());
			}
			case HTTP_DELETE: { 
				
				return new HttpDelete(httpRequestBase.getURI());
			}
			
			default: {
				
				return null;
			}
		}
	}
}
