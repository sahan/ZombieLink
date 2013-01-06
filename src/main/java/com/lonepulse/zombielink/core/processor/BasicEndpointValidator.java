/**
 * 
 */
package com.lonepulse.zombielink.core.processor;

import java.net.URI;

import org.apache.http.client.utils.URIBuilder;

import com.lonepulse.zombielink.core.annotation.Endpoint;

/**
 * <p>Validates the structure of an endpoint interface.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class BasicEndpointValidator implements EndpointValidator {


	/**
	 * {@inheritDoc}
	 */
	@Override
	public URI validate(Class<?> endpointInterface) {
		
		try {
			
			if(!endpointInterface.isAnnotationPresent(Endpoint.class))
				throw new EndpointMissingAnnotationException(endpointInterface, Endpoint.class);
			
			String scheme = endpointInterface.getAnnotation(Endpoint.class).scheme();
			String host = endpointInterface.getAnnotation(Endpoint.class).value();
			String port = endpointInterface.getAnnotation(Endpoint.class).port();
			String path = endpointInterface.getAnnotation(Endpoint.class).path();
			
			URIBuilder uriBuilder = new URIBuilder();
			uriBuilder.setScheme(scheme).setHost(host).setPath(path);
			
			if(!port.equals("")) 
				uriBuilder.setPort(Integer.parseInt(port));
			
			return uriBuilder.build();
		}
		catch(Exception e) {
			
			throw new EndpointValidationFailedException(endpointInterface, e);
		}
	}
}
