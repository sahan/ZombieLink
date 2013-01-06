/**
 * 
 */
package com.lonepulse.zombielink.core.processor;

import com.lonepulse.zombielink.core.annotation.Endpoint;
import com.lonepulse.zombielink.core.annotation.Param;
import com.lonepulse.zombielink.core.annotation.Request;



/**
 * <p>This is is a common policy which all proxy factories must implement.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface ProxyFactory {

	/**
	 * <p>This factory method takes a {@link Class} representation of an interface 
	 * which models a communication endpoint and returns a single instance of a 
	 * dynamically generated proxy for that endpoint interface.</p>
	 * <br>
	 * <p>The interface should be annotated with {@link Endpoint} and may 
	 * contain one or more abstract method declarations which are annotated with 
	 * {@link Request} (and optionally {@link Param}).</p>
	 * 
	 * @param typeClass
	 * 				the {@link Class} of the interface which model an {@link Endpoint}
	 * <br><br>
	 * @return the dynamically created proxy for the endpoint interface
	 * <br><br>
	 * @throws ProxyFactoryException
	 * 				due to any failure in creating a proxy
	 * <br><br>
	 * @since 1.1.1			
	 */
	public abstract <T> T create(final Class<T> typeClass);
}
