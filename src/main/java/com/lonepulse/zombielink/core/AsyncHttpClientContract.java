/**
 * 
 */
package com.lonepulse.zombielink.core;

import org.apache.http.client.methods.HttpRequestBase;

import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;
import com.lonepulse.zombielink.core.response.AsyncHandler;

/**
 * <p>Declares the asynchronous network communication capabilities of the application.</p> 
 * 
 * @version 1.1.2
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface AsyncHttpClientContract extends HttpClientContract {
	

	/**
	 * <p>Takes an {@link HttpRequestBase}, executes it asynchronously 
	 * and uses the results to run the {@link AsyncHandler}.
	 * 
	 * @param httpRequestBase 
	 * 			any request of type {@link HttpRequestBase}
	 * 
	 * @param config
	 * 			the {@link ProxyInvocationConfiguration} of the current request
	 * <br><br>
	 * @since 1.1.2
	 */
	public abstract <T extends HttpRequestBase> 
	void executeAsyncRequest(final T httpRequestBase, final ProxyInvocationConfiguration config);
}
