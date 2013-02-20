package com.lonepulse.zombielink.core.processor.executor;

/*
 * #%L
 * ZombieLink
 * %%
 * Copyright (C) 2013 Lonepulse
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

import com.lonepulse.zombielink.core.MultiThreadedHttpClient;
import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;
import com.lonepulse.zombielink.core.response.AsyncHandler;
import com.lonepulse.zombielink.core.response.ResponseHandlers;

/**
 * <p>A concrete implementation of {@link RequestExecutor} which executes 
 * {@link HttpRequest}s asynchronously.</p>
 * 
 * <p>The thread pool is managed by {@link MultiThreadedHttpClient} using 
 * a {@link PoolingClientConnectionManager}.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class AsyncRequestExecutor implements RequestExecutor {

	
	/**
	 * <p>A cached thread pool which will be used to execute asynchronous requests.
	 */
	private static final ExecutorService ASYNC_EXECUTOR_SERVICE = Executors.newCachedThreadPool();
	
	
	/**
	 * <p>Takes an {@link HttpRequestBase} and executes it asynchronously. 
	 * This method returns {@code null} immediately.
	 * 
	 * @param httpRequestBase
	 * 			the {@link HttpRequestBase} to be executed
	 * 
	 * @param config
	 * 			the {@link ProxyInvocationConfiguration} associated with 
	 * 			the current request
	 * 
	 * @since 1.1.0
	 */
	@Override
	public HttpResponse execute(final HttpRequestBase httpRequestBase, final ProxyInvocationConfiguration config)
	throws RequestExecutionException {

		ASYNC_EXECUTOR_SERVICE.execute(new Runnable() {
			
			@SuppressWarnings("unchecked") //type-safe cast from object to AsyncHandler
			@Override
			public void run() {
		
				String errorContext = "Asynchronous request execution failed. ";

				HttpResponse httpResponse;
				
				try {
				
					httpResponse = MultiThreadedHttpClient.INSTANCE.executeRequest(httpRequestBase);
				} 
				catch (ClientProtocolException cpe) {

					LogFactory.getLog(MultiThreadedHttpClient.class)
								.error(errorContext + "Protocol cannot be resolved.", cpe);
					
					return;
				} 
				catch (IOException ioe) {

					LogFactory.getLog(MultiThreadedHttpClient.class)
								.error(errorContext + "IO failure.", ioe);
					
					return;
				}  
					
				AsyncHandler<Object> asyncHandler = null;
					
				for (Object object : config.getRequestArgs()) { //find the provided AsyncHandler (if any)
						
					if(object instanceof AsyncHandler)
						asyncHandler = AsyncHandler.class.cast(object);
				}
				
				if(asyncHandler != null) { //response handling has to commence
					
					Object reponseEntity = ResponseHandlers.BASIC.handle(httpResponse, config);
						
					if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
						asyncHandler.onSuccess(httpResponse, reponseEntity); 
						
					else 
						asyncHandler.onFailure(httpResponse, reponseEntity);
				}
			}
		});
		
		return null;
	}
}
