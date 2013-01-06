/**
 *
 */
package com.lonepulse.zombielink.core;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * <p>A primitive implementation of {@link HttpClientContract} which provides the 
 * bare essentials of network interfacing.</p>
 * 
 * @version 1.1.3
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class BasicHttpClient implements HttpClientContract {

	
	/**
	 * <p>The implementation of {@link HttpClient} which is used to execute 
	 * the requests.</p>
	 */
	private HttpClient httpClient;
	
	
	/**
	 * <p>Default constructor overridden to provide an implementation of 
	 * {@link HttpClient}.</p> 
	 * <br><br>
	 * @since 1.1.2
	 */
	public BasicHttpClient() {
		
		this(new DefaultHttpClient());
	}
	
	/**
	 * <p>Parameterized constructor takes an implementation of {@link HttpClient}.</p> 
	 * 
	 * @param httpClient 	
	 * 				the {@link HttpClient} which is to be used
	 * <br><br>
	 * @since 1.1.1
	 */
	public BasicHttpClient(HttpClient httpClient) {

		this.httpClient = httpClient;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public HttpResponse executeRequest(HttpRequestBase httpRequestBase) 
	throws ClientProtocolException, IOException {

		return httpClient.execute(httpRequestBase);
	}
	
	/**
	 * <p>Retrieves the instance of {@link HttpClient} which is being used.</p>
	 * 
	 * @return the {@link HttpClientContract}
	 * <br><br>
	 * @since 1.1.1
	 */
	protected HttpClient getHttpClient() {
		
		return httpClient;
	}
}
