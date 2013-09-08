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
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.lonepulse.zombielink.annotation.Bite;
import com.lonepulse.zombielink.annotation.QueryParam;
import com.lonepulse.zombielink.annotation.Request;
import com.lonepulse.zombielink.inject.Zombie;

/**
 * <p>Performs <b>Unit Testing</b> on the proxy of {@link RequestEndpoint}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.1
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class RequestEndpointTest {

	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();
	
	@Bite
	private RequestEndpoint requestEndpoint;
	
	
	/**
	 * <p>Sets up the test case by performing endpoint injection on {@link #requestEndpoint}.
	 * 
	 * @throws java.lang.Exception
	 * 			if the test case setup or endpoint injection failed
	 */
	@Before
	public void setUp() throws Exception {
		
		Zombie.infect(this);
	}
	
	/**
	 * <p>Test for a {@link Request} with a subpath having {@link QueryParam}s.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testSubpathWithParams() {
		
		String subpath = "/subpathwithparams\\?\\S+", body = "hello", 
			   firstName = "Doctor", lastName = "Who",
			   url = "/subpathwithparams?firstName=" + firstName + "&lastName=" + lastName;
		
		stubFor(get(urlMatching(subpath))
				.willReturn(aResponse()
				.withStatus(200)
				.withBody(body)));
		
		assertEquals(body, requestEndpoint.subpathWithParams(firstName, lastName));
		verify(getRequestedFor(urlEqualTo(url)));
	}

	/**
	 * <p>Test for a {@link Request} with a subpath having constant {@link Request.Param}s.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testSubpathWithConstParams() {
		
		String subpath = "/subpathwithconstparams\\?\\S+", body = "hello", 
			   firstName = "Doctor", lastName = "Who",
			   url = "/subpathwithconstparams?firstName=" + firstName + "&lastName=" + lastName;
		
		stubFor(get(urlMatching(subpath))
				.willReturn(aResponse()
				.withStatus(200)
				.withBody(body)));
		
		assertEquals(body, requestEndpoint.subpathWithConstParams());
		verify(getRequestedFor(urlEqualTo(url)));
	}
}
