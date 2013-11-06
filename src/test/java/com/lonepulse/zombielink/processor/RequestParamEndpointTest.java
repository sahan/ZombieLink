package com.lonepulse.zombielink.processor;

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
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.putRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.SerializableEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.lonepulse.zombielink.annotation.Bite;
import com.lonepulse.zombielink.annotation.QueryParam;
import com.lonepulse.zombielink.annotation.QueryParams;
import com.lonepulse.zombielink.annotation.Request;
import com.lonepulse.zombielink.inject.Zombie;
import com.lonepulse.zombielink.model.User;

/**
 * <p>Performs <b>Unit Testing</b> on the proxy of {@link RequestParamEndpoint}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.1
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class RequestParamEndpointTest {

	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Bite
	private RequestParamEndpoint requestEndpoint;
	
	
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
	public final void testQueryParams() {
		
		String subpath = "/queryparams\\?\\S+",
			   firstName = "Doctor", lastName = "Who",
			   url = "/queryparams?firstName=" + firstName + "&lastName=" + lastName;
		
		stubFor(get(urlMatching(subpath))
				.willReturn(aResponse()
				.withStatus(200)));

		requestEndpoint.queryParams(firstName, lastName);
		
		verify(getRequestedFor(urlEqualTo(url)));
	}
	
	/**
	 * <p>Test for a {@link Request} with a subpath having batch {@link QueryParams}.</p>
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testQueryParamsBatch() {
		
		String subpath = "/queryparamsbatch\\?\\S+",
			   fnKey = "firstName", lnKey = "lastName",
			   firstName = "Bucky", lastName = "Barnes",
			   url = "/queryparamsbatch?" + fnKey + "=" + firstName + "&" + lnKey + "=" + lastName;
		
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put(fnKey, firstName); 
		params.put(lnKey, lastName); 
		
		stubFor(get(urlMatching(subpath))
				.willReturn(aResponse()
				.withStatus(200)));
		
		requestEndpoint.queryParamsBatch(params);
		
		verify(getRequestedFor(urlEqualTo(url)));
	}
	
	/**
	 * <p>Test for a {@link Request} with a subpath having batch {@link QueryParams}.</p>
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testFormParamsBatch() {
		
		String subpath = "/formparamsbatch",
			   fnKey = "firstName", lnKey = "lastName",
			   firstName = "Franklin", lastName = "Richards",
			   body = fnKey + "=" + firstName + "&" + lnKey + "=" + lastName;
		
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put(fnKey, firstName); 
		params.put(lnKey, lastName); 
		
		stubFor(post(urlMatching(subpath))
				.willReturn(aResponse()
				.withBody(body)
				.withStatus(200)));
		
		requestEndpoint.formParamsBatch(params);
		
		verify(postRequestedFor(urlEqualTo(subpath))
			  .withRequestBody(equalTo(body)));
	}

	/**
	 * <p>Test for a {@link Request} with a subpath having constant query parameters.</p>
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testConstantQueryParams() {
		
		String subpath = "/constantqueryparams\\?\\S+", 
			   firstName = "Doctor", lastName = "Who",
			   url = "/constantqueryparams?firstName=" + firstName + "&lastName=" + lastName;
		
		stubFor(get(urlMatching(subpath))
				.willReturn(aResponse()
				.withStatus(200)));
		
		requestEndpoint.constantQueryParams();
		
		verify(getRequestedFor(urlEqualTo(url)));
	}
	
	/**
	 * <p>Test for a {@link Request} with a subpath having constant form parameters.</p>
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testConstantFormParams() {
		
		String subpath = "/constantformparams", 
			   firstName = "Beta-Ray", lastName = "Bill",
			   body = "firstName=" + firstName + "&lastName=" + lastName;
		
		stubFor(post(urlMatching(subpath))
				.willReturn(aResponse()
				.withBody(body)
				.withStatus(200)));
		
		requestEndpoint.constantFormParams();
		
		verify(postRequestedFor(urlEqualTo(subpath))
			  .withRequestBody(equalTo(body)));
	}
	
	/**
	 * <p>Test for a {@link Request} with a {@code byte[]} entity.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testPrimitiveByteArrayEntity() throws ParseException, IOException {
		
		String subpath = "/primitivebytearrayentity";
		byte[] bytes = new byte[] {1, 1, 1, 1, 1, 1, 1, 1};
		ByteArrayEntity bae = new ByteArrayEntity(bytes);
		
		stubFor(put(urlEqualTo(subpath))
				.willReturn(aResponse()
				.withStatus(200)));
		
		requestEndpoint.primitiveByteArrayEntity(bytes);
		
		verify(putRequestedFor(urlEqualTo(subpath))
			   .withRequestBody(equalTo(EntityUtils.toString(bae))));
	}
	
	/**
	 * <p>Test for a {@link Request} with a {@code Byte}[] entity.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testWrapperByteArrayEntity() throws ParseException, IOException {
		
		String subpath = "/wrapperbytearrayentity";
		Byte[] bytes = new Byte[] {1, 1, 1, 1, 1, 1, 1, 1};
		ByteArrayEntity bae = new ByteArrayEntity(new byte[] {1, 1, 1, 1, 1, 1, 1, 1});
		
		stubFor(put(urlEqualTo(subpath))
				.willReturn(aResponse()
				.withStatus(200)));
		
		requestEndpoint.wrapperByteArrayEntity(bytes);
		
		verify(putRequestedFor(urlEqualTo(subpath))
				.withRequestBody(equalTo(EntityUtils.toString(bae))));
	}
	
	/**
	 * <p>Test for a {@link Request} with a {@link File} entity.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testFileEntity() throws ParseException, IOException, URISyntaxException {
		
		String subpath = "/fileentity";
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File file = new File(classLoader.getResource("LICENSE.txt").toURI());
		FileEntity fe = new FileEntity(file);
		
		stubFor(put(urlEqualTo(subpath))
				.willReturn(aResponse()
				.withStatus(200)));
		
		requestEndpoint.fileEntity(file);
		
		verify(putRequestedFor(urlEqualTo(subpath))
			   .withRequestBody(equalTo(EntityUtils.toString(fe))));
	}
	
	/**
	 * <p>Test for a {@link Request} with a <b>buffered</b> entity.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testBufferedHttpEntity() throws ParseException, IOException {
		
		String subpath = "/bufferedhttpentity";
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("LICENSE.txt");
		InputStream parallelInputStream = classLoader.getResourceAsStream("LICENSE.txt");
		BasicHttpEntity bhe = new BasicHttpEntity();
		bhe.setContent(parallelInputStream);
		
		stubFor(put(urlEqualTo(subpath))
				.willReturn(aResponse()
				.withStatus(200)));
		
		requestEndpoint.bufferedHttpEntity(inputStream);
		
		verify(putRequestedFor(urlEqualTo(subpath))
			   .withRequestBody(equalTo(EntityUtils.toString(bhe))));
	}
	
	/**
	 * <p>Test for a {@link Request} with a {@link String} entity.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testStringEntity() throws ParseException, IOException {
		
		String subpath = "/stringentity";
		String entity = "haganenorenkinjutsushi";
		StringEntity se = new StringEntity(entity);
		
		stubFor(put(urlEqualTo(subpath))
				.willReturn(aResponse()
				.withStatus(200)));
		
		requestEndpoint.stringEntity(entity);
		
		verify(putRequestedFor(urlEqualTo(subpath))
			   .withRequestBody(equalTo(EntityUtils.toString(se))));
	}
	
	/**
	 * <p>Test for a {@link Request} with a {@link Serializable} entity.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testSerializableEntity() throws ParseException, IOException {
		
		String subpath = "/serializableentity";
		User entity = new User(1L, "Eren", "Yeager", 15, false);
		SerializableEntity se = new SerializableEntity(entity, true);
		
		stubFor(put(urlEqualTo(subpath))
				.willReturn(aResponse()
				.withStatus(200)));
		
		requestEndpoint.serializableEntity(entity);
		
		verify(putRequestedFor(urlEqualTo(subpath))
			   .withRequestBody(equalTo(EntityUtils.toString(se))));
	}

	/**
	 * <p>Test for a non-POST entity-enclosing request without a supplied entity.</p>
	 * 
	 * @since 1.2.4
	 */
	@Test @SuppressWarnings("unchecked") //safe cast to Class<Throwable>
	public final void testMissingEntity() throws ClassNotFoundException {
		
		expectedException.expectCause(Is.isA((Class<Throwable>)
			Class.forName("com.lonepulse.zombielink.request.RequestProcessorException")));
		
		requestEndpoint.missingEntity();
	}
	
	/**
	 * <p>Test for a multiple entities in an entity-enclosing request.</p>
	 * 
	 * @since 1.2.4
	 */
	@Test @SuppressWarnings("unchecked") //safe cast to Class<Throwable>
	public final void testMultipleEntity() throws ClassNotFoundException {
		
		expectedException.expectCause(Is.isA((Class<Throwable>)
			Class.forName("com.lonepulse.zombielink.request.RequestProcessorException")));
		
		requestEndpoint.multipleEntity("entity1", "entity2");
	}
	
	/**
	 * <p>Test for an unresolvable entity in an entity-enclosing request.</p>
	 * 
	 * @since 1.2.4
	 */
	@Test @SuppressWarnings("unchecked") //safe cast to Class<Throwable>
	public final void testResolutionFailedEntity() throws IOException, ClassNotFoundException {
		
		expectedException.expectCause(Is.isA((Class<Throwable>)
			Class.forName("com.lonepulse.zombielink.request.RequestProcessorException")));
		
		requestEndpoint.resolutionFailedEntity(new Object());
	}
}
