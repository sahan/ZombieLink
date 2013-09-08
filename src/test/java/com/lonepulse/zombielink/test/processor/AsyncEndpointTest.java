package com.lonepulse.zombielink.test.processor;

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

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.lonepulse.zombielink.annotation.Asynchronous;
import com.lonepulse.zombielink.annotation.Bite;
import com.lonepulse.zombielink.inject.Zombie;
import com.lonepulse.zombielink.response.AsyncHandler;

/**
 * <p>Performs <b>Unit Testing</b> on the proxy of {@link AsyncEndpoint}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.1
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class AsyncEndpointTest {

	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();
	
	@Bite
	private AsyncEndpoint asyncEndpoint;
	
	
	/**
	 * <p>Sets up the test case by performing endpoint injection on {@link #asyncEndpoint}.
	 * 
	 * @throws java.lang.Exception
	 * 			if the test case setup or endpoint injection failed
	 */
	@Before
	public void setUp() throws Exception {
		
		Zombie.infect(this);
	}
	
	/**
	 * <p>Tests asynchronous request execution with {@link Asynchronous} and {@link AsyncHandler}. 
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testAsync() {
		
		String subpath = "/async", body = "hello";
		
		stubFor(get(urlMatching(subpath))
				.willReturn(aResponse()
				.withStatus(200)
				.withBody(body)));
		
		String result = asyncEndpoint.async(new AsyncHandler<String>() {
			
			@Override
			public void onSuccess(HttpResponse httpResponse, String body) {
			
				assertTrue(body != null);
				assertTrue(body.equals("body"));
			}
		});
		
		assertNull(result);
	}
}
