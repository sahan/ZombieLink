/**
 * 
 */
package com.lonepulse.zombielink.core.processor;

import java.net.URI;

import org.apache.http.client.utils.URIBuilder;

import com.lonepulse.zombielink.core.annotation.Endpoint;

/**
 * <p>The policy for validating an endpoint interface.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface EndpointValidator {

	/**
	 * <p>Takes the given endpoint {@link Class} representation and validates 
	 * it to be a true endpoint interface.</p>
	 * 
	 * @param endpointInterface
	 * 				the {@link Class} of the interface which is models an 
	 * 				{@link Endpoint}
	 * 
	 * @return the parsed {@link URI} 
	 * <br><br>
	 * @since 1.1.1
	 * 				{@link URI} creation via {@link URIBuilder} 
	 */
	public abstract URI validate(Class<?> endpointInterface);
}
