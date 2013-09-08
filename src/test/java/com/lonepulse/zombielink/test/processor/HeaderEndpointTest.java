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
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.lonepulse.zombielink.annotation.Bite;
import com.lonepulse.zombielink.annotation.Header;
import com.lonepulse.zombielink.annotation.HeaderSet;
import com.lonepulse.zombielink.inject.Zombie;

/**
 * <p>Performs <b>Unit Testing</b> on the proxy of {@link HeaderEndpoint}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.1
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class HeaderEndpointTest {

	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();
	
	@Bite
	private HeaderEndpoint headerEndpoint;
	
	
	/**
	 * <p>Sets up the test case by performing endpoint injection on {@link #headerEndpoint}.
	 * 
	 * @throws java.lang.Exception
	 * 			if the test case setup or endpoint injection failed
	 */
	@Before
	public void setUp() throws Exception {
		
		Zombie.infect(this);
	}
	
	/**
	 * <p>Tests response header retrieval with {@link Header}. 
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testResponseHeader() {
	
		String subpath = "/responseheader", body = "hello", 
			   header = "Server", headerValue = "Jetty(9.0.x)";
		
		stubFor(get(urlMatching(subpath))
				.willReturn(aResponse()
				.withStatus(200)
				.withHeader(header, headerValue)
				.withBody(body)));
		
		StringBuilder responseHeader = new StringBuilder();
		
		assertEquals(headerEndpoint.responseHeader(responseHeader), body);
		
		String server = responseHeader.toString();
		assertTrue(server != null);
		assertTrue(server.equals(headerValue));
	}
	
	/**
	 * <p>Test for a request {@link Header}.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testRequestHeader() {
		
		stubFor(get(urlEqualTo("/requestheader"))
				.willReturn(aResponse()
				.withStatus(200)));
		
		String header = "mobile";
		
		headerEndpoint.requestHeader(new StringBuilder(header));
		
		verify(getRequestedFor(urlMatching("/requestheader"))
				.withHeader("User-Agent", matching(header)));
	}
	
	/**
	 * <p>Test for {@link HeaderSet} and {@link HeaderSet.Header}.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testHeaderSet() {
		
		stubFor(get(urlEqualTo("/headerset"))
				.willReturn(aResponse()
				.withStatus(200)));
		
		headerEndpoint.headerSet();
		
		verify(getRequestedFor(urlMatching("/headerset"))
				.withHeader("Accept", matching("application/json"))
				.withHeader("Accept-Charset", matching("utf-8")));
	}
}
