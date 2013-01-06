/**
 * 
 */
package com.lonepulse.zombielink.core.request;

import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;

/**
 * <p>This interface declares the generic policy for a <i>factory</i> which builds the 
 * components of an endpoint.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface EndpointComponentFactory<T> {

	/**
	 * <p>Takes an array of arguments, constructs the component and returns it.
	 * 
	 * @param proxyInvocationConfiguration
	 * 			the arguments which are used to create the component
	 * 
	 * @return the created component
	 * <br><br>
	 * @since 1.1.1
	 */
	public abstract T create(ProxyInvocationConfiguration proxyInvocationConfiguration);
}
