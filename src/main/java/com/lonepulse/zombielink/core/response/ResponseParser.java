/**
 * 
 */
package com.lonepulse.zombielink.core.response;

import org.apache.http.HttpResponse;

import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;

/**
 * <p>This interface policy of a <i>response parser</i>. i.e. the unit which 
 * examines the content of a {@link HttpResponse} and parses that content 
 * into the desired entity.</p>
 * 
 * @version 1.1.4
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface ResponseParser<T extends Object> {

	/**
	 * <p>Executes the following steps for parsing in order. 
	 * <br><br>
	 * <ol>
	 * 	 <li>Check type compatibility</li>
	 *   <li>Process response headers</li>
	 *   <li>Process response body</li>
	 * </ol>
	 * 
	 * @param httpResponse
	 * 				the {@link HttpResponse} from which the content is extracted
	 * 
	 * @param config
	 * 				the {@link ProxyInvocationConfiguration} which supplies all information 
	 * 				regarding the request and it's invocation
	 * <br><br>
	 * @return the entity which is created after parsing the output
	 * <br><br>
	 * @since 1.1.1
	 */
	public T parse(HttpResponse httpResponse, ProxyInvocationConfiguration config);
}