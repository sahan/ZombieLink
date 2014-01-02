package com.lonepulse.zombielink.executor;

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

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

import com.lonepulse.zombielink.proxy.Zombie;
import com.lonepulse.zombielink.proxy.Zombie.Configuration;

/**
 * <p>This is a concrete implementation of {@link ConfigurationManager} which manages request execution 
 * configurations defined as instances of {@link Zombie.Configuration}.</p> 
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.3.0
 * <br><br>
 * @author <a href="http://sahan.me">Lahiru Sahan Jayasinghe</a>
 */
final class ConfigurationService implements ConfigurationManager {

	
	/**
	 * <p>The <i>out-of-the-box</i> configuration for an instance of {@link HttpClient} which will be used 
	 * for executing all endpoint requests. Below is a detailed description of all configured properties.</p> 
	 * <br>
	 * <ul>
	 * <li>
	 * <p><b>HttpClient</b></p>
	 * <br>
	 * <p>It registers two {@link Scheme}s:</p>
	 * <br>
	 * <ol>
	 * 	<li><b>HTTP</b> on port <b>80</b> using sockets from {@link PlainSocketFactory#getSocketFactory}</li>
	 * 	<li><b>HTTPS</b> on port <b>443</b> using sockets from {@link SSLSocketFactory#getSocketFactory}</li>
	 * </ol>
	 * 
	 * <p>It uses a {@link PoolingClientConnectionManager} with the maximum number of client connections 
	 * per route set to <b>4</b> and the total set to <b>128</b>.</p>
	 * </li>
	 * </ul>
	 * @return the instance of {@link HttpClient} which will be used for request execution
	 * <br><br>
	 * @since 1.3.0
	 */
	@Override
	public Configuration getDefault() {
		
		return new Configuration() {

			@Override
			public HttpClient httpClient() {
				
				try {
				
					SchemeRegistry schemeRegistry = new SchemeRegistry();
					schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
					schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
					
					PoolingClientConnectionManager pccm = new PoolingClientConnectionManager(schemeRegistry);
					pccm.setMaxTotal(128);
					pccm.setDefaultMaxPerRoute(4);
					
					return new DefaultHttpClient(pccm);
				}
				catch(Exception e) {
					
					throw new ConfigurationFailedException(e);
				}
			}
		};
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Configuration register(Class<?> endpointClass) {
		
		try {
			
			if(endpointClass.isAnnotationPresent(com.lonepulse.zombielink.annotation.Config.class)) {
				
				Configuration configuration = endpointClass.getAnnotation(
					com.lonepulse.zombielink.annotation.Config.class).value().newInstance();
				
				HttpClient httpClient = configuration.httpClient();
				HttpClientDirectory.INSTANCE.bind(endpointClass, httpClient); //currently the only configurable property
				
				return configuration;
			}
			else {
				
				HttpClientDirectory.INSTANCE.bind(endpointClass, HttpClientDirectory.DEFAULT);
				
				return new Configuration(){};
			}
		}
		catch(Exception e) {
			
			throw new ConfigurationFailedException(endpointClass, e);
		}
	}
}
