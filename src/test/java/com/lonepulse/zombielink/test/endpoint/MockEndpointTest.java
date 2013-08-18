package com.lonepulse.zombielink.test.endpoint;

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
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.findAll;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.putRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import com.lonepulse.zombielink.core.annotation.Asynchronous;
import com.lonepulse.zombielink.core.annotation.Bite;
import com.lonepulse.zombielink.core.annotation.Header;
import com.lonepulse.zombielink.core.annotation.HeaderSet;
import com.lonepulse.zombielink.core.annotation.Param;
import com.lonepulse.zombielink.core.annotation.PathParam;
import com.lonepulse.zombielink.core.annotation.Request;
import com.lonepulse.zombielink.core.annotation.Stateful;
import com.lonepulse.zombielink.core.inject.Zombie;
import com.lonepulse.zombielink.core.response.AsyncHandler;

/**
 * <p>Performs <b>Unit Testing</b> on the proxy of {@link MockEndpoint}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.1
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class MockEndpointTest {

	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();
	
	@Bite
	private MockEndpoint mockEndpoint;
	
	
	/**
	 * <p>Sets up the test case by performing endpoint injection on {@link #mockEndpoint}.
	 * 
	 * @throws java.lang.Exception
	 * 			if the test case setup or endpoint injection failed
	 */
	@Before
	public void setUp() throws Exception {
		
		Zombie.infect(this);
	}
	
	/**
	 * <p>Test for a {@link Request} with a subpath.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testSubpath() {
		
		String subpath = "/subpath", body = "hello";
		
		stubFor(get(urlEqualTo(subpath))
				.willReturn(aResponse()
				.withStatus(200)
				.withBody(body)));
		
		assertEquals(body, mockEndpoint.subpath());
		verify(getRequestedFor(urlMatching(subpath)));
	}
	
	/**
	 * <p>Test for a {@link Request} with a subpath having {@link Param}s.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testSubpathWithParams() {
		
		String subpath = "/subpathwithparams\\?\\S+", body = "hello", 
			   firstName = "Doctor", lastName = "Who",
			   url = "/subpathwithparams?lastName=" + lastName + "&firstName=" + firstName;
		
		stubFor(get(urlMatching(subpath))
				.willReturn(aResponse()
				.withStatus(200)
				.withBody(body)));
		
		assertEquals(body, mockEndpoint.subpathWithParams(firstName, lastName));
		verify(getRequestedFor(urlEqualTo(url)));
	}
	
	/**
	 * <p>Test for a {@link Rest} request with a subpath.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testRestfulSubpath() {
		
		String subpath = "/restfulsubpath", body = "hello";
		
		stubFor(get(urlEqualTo(subpath))
				.willReturn(aResponse()
				.withStatus(200)
				.withBody(body)));
		
		assertEquals(body, mockEndpoint.restfulSubpath());
		verify(getRequestedFor(urlMatching(subpath)));
	}
	
	/**
	 * <p>Test for a {@link Rest} request with a subpath having {@link PathParam}s.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testRestfulSubpathWithParams() {
		
		String subpath = "/restfulsubpathwithparam/\\S+", body = "hello", 
			   id = "doctorwho", url = "/restfulsubpathwithparam/" + id;
		
		stubFor(get(urlMatching(subpath))
				.willReturn(aResponse()
				.withStatus(200)
				.withBody(body)));
		
		assertEquals(body, mockEndpoint.restfulSubpathWithParam(id));
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
		
		assertEquals(body, mockEndpoint.subpathWithConstParams());
		verify(getRequestedFor(urlEqualTo(url)));
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
		
		String result = mockEndpoint.async(new AsyncHandler<String>() {
			
			@Override
			public void onSuccess(HttpResponse httpResponse, String body) {
			
				assertTrue(body != null);
				assertTrue(body.equals("body"));
			}
		});
		
		assertNull(result);
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
		
		assertEquals(mockEndpoint.responseHeader(responseHeader), body);
		
		String server = responseHeader.toString();
		assertTrue(server != null);
		assertTrue(server.equals(headerValue));
	}
	
	/**
	 * <p>Test for the request method POST.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testPostMethod() {
		
		String path = "/postrequest";
		
		stubFor(post(urlEqualTo(path)).willReturn(aResponse().withStatus(200)));
		
		String name = "DoctorWho", age = "953", location = "Tardis";
		
		mockEndpoint.postRequest(name, age, location);
		
		List<LoggedRequest> requests = findAll(postRequestedFor(urlMatching(path)));
		assertFalse(requests == null);
		assertFalse(requests.isEmpty());
		
		LoggedRequest request = requests.get(0);
		assertTrue(request.getMethod().equals(RequestMethod.POST));
		
		String body = request.getBodyAsString();
		assertTrue(body.contains("name=" + name));
		assertTrue(body.contains("age=" + age));
		assertTrue(body.contains("location=" + location));
	}
	
	/**
	 * <p>Test for the request method PUT.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testPutMethod() {
		
		String path = "/putrequest";
		
		stubFor(put(urlEqualTo(path))
				.willReturn(aResponse()
				.withStatus(200)));
		
		String name = "DoctorWho", age = "953", location = "Tardis";
		
		mockEndpoint.putRequest(name, age, location);
		
		List<LoggedRequest> requests = findAll(putRequestedFor(urlMatching(path)));
		assertFalse(requests == null);
		assertFalse(requests.isEmpty());
		
		LoggedRequest request = requests.get(0);
		assertTrue(request.getMethod().equals(RequestMethod.PUT));
		
		String body = request.getBodyAsString();
		assertTrue(body.contains("name=" + name));
		assertTrue(body.contains("age=" + age));
		assertTrue(body.contains("location=" + location));
	}
	
	/**
	 * <p>Test for the request method DELETE.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testDeleteMethod() {
		
		String path = "/deleterequest";
		
		stubFor(delete(urlEqualTo(path))
				.willReturn(aResponse()
				.withStatus(200)));
		
		mockEndpoint.deleteRequest();
		
		List<LoggedRequest> requests = findAll(deleteRequestedFor(urlMatching(path)));
		assertFalse(requests == null);
		assertFalse(requests.isEmpty());
		
		LoggedRequest request = requests.get(0);
		assertTrue(request.getMethod().equals(RequestMethod.DELETE));
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
		
		mockEndpoint.requestHeader(new StringBuilder(header));
		
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
		
		mockEndpoint.headerSet();
		
		verify(getRequestedFor(urlMatching("/headerset"))
				.withHeader("Accept", matching("application/json"))
				.withHeader("Accept-Charset", matching("utf-8")));
	}
	
	/**
	 * <p>Test for {@link Stateful}.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testState() {
		
		String cookie = "JSESSIONID=1111";
		
		stubFor(get(urlEqualTo("/stateful"))
				.willReturn(aResponse()
				.withStatus(200)
				.withHeader("Set-Cookie", cookie)
				.withBody("empty")));
		
		StringBuilder cookieHeader = new StringBuilder();
		
		mockEndpoint.stateful(cookieHeader);
		assertEquals(cookie, cookieHeader.toString()); //the server should return a cookie to store 
		
		mockEndpoint.stateful(new StringBuilder()); //a second request should submit the cookie
		
		verify(getRequestedFor(urlMatching("/stateful"))
			   .withHeader("Cookie", matching(cookie)));
	}
}
