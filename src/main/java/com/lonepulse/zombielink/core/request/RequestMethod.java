/**
 * 
 */
package com.lonepulse.zombielink.core.request;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;

/**
 * <p>This enum is used to identify the request type. This may be a basic HTTP request 
 * or it might be a JSON based web service request...etc
 * 
 * @version 1.1.3
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum RequestMethod {

	/**
	 * <p>Identifies an {@link HttpGet} request.
	 */
	HTTP_GET,
	
	/**
	 * <p>Identifies an {@link HttpPost} request.
	 */
	HTTP_POST,
	
	/**
	 * <p>Identifies an {@link HttpPut} request.
	 */
	HTTP_PUT,
	
	/**
	 * <p>Identifies an {@link HttpDelete} request.
	 */
	HTTP_DELETE,
	
	/**
	 * <p>Identifies an {@link HttpHead} request.
	 */
	HTTP_HEAD,
	
	/**
	 * <p>Identifies an {@link HttpTrace} request.
	 */
	HTTP_TRACE,
	
	/**
	 * <p>Identifies an {@link HttpOptions} request.
	 */
	HTTP_OPTIONS;
}
