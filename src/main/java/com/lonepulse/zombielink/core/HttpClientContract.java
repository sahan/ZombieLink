/**
 * 
 */
package com.lonepulse.zombielink.core;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * <p>This contract declares the basic network communication capabilities of 
 * the API. It grows on the <b>Apache HTTP Components library</b>.</p> 
 * 
 * @version 1.1.2
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface HttpClientContract {
	
	
	/**
	 * <p>Takes an {@link HttpRequestBase}, executes it and 
	 * returns the results as an {@link HttpResponse}.</p>
	 * 
	 * @param httpRequestBase 
	 * 			any request of type {@link HttpRequestBase}
	 * 
	 * @return the {@link HttpResponse} of the execution.
	 * <br><br>
	 * @since 1.1.1
	 */
	public abstract <T extends HttpRequestBase> HttpResponse executeRequest(T httpRequestBase) 
	throws ClientProtocolException, IOException;
}
