/**
 * 
 */
package com.lonepulse.zombielink.rest.request;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

import com.lonepulse.zombielink.core.annotation.Param;
import com.lonepulse.zombielink.core.processor.AnnotationExtractor;
import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;
import com.lonepulse.zombielink.core.request.AbstractRequestBuilder;
import com.lonepulse.zombielink.core.request.RequestMethod;
import com.lonepulse.zombielink.rest.annotation.Rest;

/**
 * <p>This is an implementation of {@link AbstractRequestBuilder} which handles the 
 * request creation for RESTful requests.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class RestfulRequestBuilder extends AbstractRequestBuilder {

	
	@Override
	protected URI buildPath(ProxyInvocationConfiguration config) throws Exception {

		return config.getUri(); //root path return as it is
	}
	
	@Override
	protected HttpRequestBase buildRequestWithParameters(URI uri, ProxyInvocationConfiguration config) throws Exception {
		
		Method request = config.getRequest();
		
		Rest restfulRequest = request.getAnnotation(Rest.class);
		String subpath = restfulRequest.path();
		
		Map<Object, Param> annotatedParams = AnnotationExtractor.<Param>extractWithParameterValues(Param.class, request, config.getRequestArgs());
		Set<Object> methodParams = annotatedParams.keySet();
		
		for (Object paramValue : methodParams) {
			
			if(!(paramValue instanceof String))
				throw new IllegalArgumentException("Parameters for RESTful requests can only be of type " + String.class.getName()); 

			subpath = subpath.replaceAll(":" + annotatedParams.get(paramValue).value(), (String)paramValue); //replace param place-holders with param value
		}
		
		URI uriWithParameters = new URI(uri.toASCIIString() + subpath);
		
		RequestMethod httpMethod = (restfulRequest == null)? RequestMethod.HTTP_GET :restfulRequest.method();
		
		switch (httpMethod) {
		
			case HTTP_GET: { 
				
				return new HttpGet(uriWithParameters);
			}
			case HTTP_POST: { 
				
				return new HttpPost(uriWithParameters);
			}
			case HTTP_PUT: { 
				
				return new HttpPut(uriWithParameters);
			}
			case HTTP_DELETE: { 
				
				return new HttpDelete(uriWithParameters);
			}
			
			default:
				return null;
		}
	}

	@Override
	protected HttpRequestBase buildHeader(HttpRequestBase httpRequestBase, ProxyInvocationConfiguration config) throws Exception {

		//TODO so RESTful requests, the header is built in the same "basic" way? 
		
		return null;
	}

}
