/**
 * 
 */
package com.lonepulse.zombielink.core.request;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.lonepulse.zombielink.core.annotation.Request;
import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;
import com.lonepulse.zombielink.rest.annotation.Rest;
import com.lonepulse.zombielink.rest.request.RestfulRequestBuilder;

/**
 * <p>This request factory is reponsible for creating instances of {@link AbstractRequestBuilder}s.
 * 
 * @version 1.1.2 - use of {@link ProxyInvocationConfiguration}
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class RequestBuilderFactory implements EndpointComponentFactory<AbstractRequestBuilder> {

		
	/**
	 * <p>Access qualifier set to <i>private</i> to prevent external instantiation.
	 * <br><br>
	 * @since 1.1.1
	 */
	private RequestBuilderFactory() {
	}
	
	/**
	 * <p>Configures a new instance of the {@link RequestBuilderFactory} and returns it.
	 * 
	 * @return the new instance of {@link RequestBuilderFactory}
	 * <br><br>
	 * @since 1.1.2
	 */
	public static RequestBuilderFactory newInstance() {
		
		return new RequestBuilderFactory();
	}
	
	/**
	 * <p>Takes the request {@link Annotation} and returns an instance of the {@link AbstractRequestBuilder} 
	 * associated with it.
	 * 
	 * @param proxyInvocationConfiguration 
	 * 			Used to determine the appropriate {@link AbstractRequestBuilder} which is to be created.
	 * 
	 * @return
	 * 			The instance of {@link AbstractRequestBuilder} associated with the request type
	 * <br><br>
	 * @since 1.1.2
	 * 
	 * @see EndpointComponentFactory#create(ProxyInvocationConfiguration)
	 */
	@Override
	public AbstractRequestBuilder create(ProxyInvocationConfiguration proxyInvocationConfiguration) {
		
		Method request = proxyInvocationConfiguration.getRequest();
		
		return
				(request.isAnnotationPresent(Request.class))? new BasicRequestBuilder():
				(request.isAnnotationPresent(Rest.class))? new RestfulRequestBuilder(): null;
	}
}
