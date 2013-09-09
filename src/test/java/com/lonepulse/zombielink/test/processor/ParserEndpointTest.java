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

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.lonepulse.zombielink.annotation.Bite;
import com.lonepulse.zombielink.inject.Zombie;
import com.lonepulse.zombielink.response.ResponseParsers;
import com.lonepulse.zombielink.test.model.User;

/**
 * <p>Performs <b>Unit Testing</b> on the proxy of {@link ParserEndpoint}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.1
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ParserEndpointTest {

	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();
	
	@Bite
	private ParserEndpoint parserEndpoint;
	
	
	/**
	 * <p>Sets up the test case by performing endpoint injection on {@link #parserEndpoint}.
	 * 
	 * @throws java.lang.Exception
	 * 			if the test case setup or endpoint injection failed
	 */
	@Before
	public void setUp() throws Exception {
		
		Zombie.infect(this);
	}
	
	/**
	 * <p>Test for {@link ResponseParsers#JSON}.
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testParseJson() {
		
		User user = new User(1, "Tenzen", "Yakushiji", 300, true);
		
		stubFor(get(urlEqualTo("/json"))
				.willReturn(aResponse()
				.withStatus(200)
				.withBody(user.toString())));
		
		User parsedUser = parserEndpoint.parseJson();
		
		verify(getRequestedFor(urlMatching("/json")));
		
		assertEquals(user.getId(), parsedUser.getId());
		assertEquals(user.getFirstName(), parsedUser.getFirstName());
		assertEquals(user.getLastName(), parsedUser.getLastName());
		assertEquals(user.getAge(), parsedUser.getAge());
		assertEquals(user.isImmortal(), parsedUser.isImmortal());
	}
	
	/**
	 * <p>Test for {@link ResponseParsers#XML}.
	 * 
	 * @throws Exception
	 * 			if the test terminated with an error
	 * 
	 * @since 1.2.4
	 */
	@Test
	public final void testParseXml() throws Exception {
		
		User user = new User(1, "Shiro", "Wretched-Egg", 17, true);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		jaxbMarshaller.marshal(user, baos);
		
		stubFor(get(urlEqualTo("/xml"))
				.willReturn(aResponse()
				.withStatus(200)
				.withBody(baos.toString())));
		
		User parsedUser = parserEndpoint.parseXml();
		
		verify(getRequestedFor(urlMatching("/xml")));
		
		assertEquals(user.getId(), parsedUser.getId());
		assertEquals(user.getFirstName(), parsedUser.getFirstName());
		assertEquals(user.getLastName(), parsedUser.getLastName());
		assertEquals(user.getAge(), parsedUser.getAge());
		assertEquals(user.isImmortal(), parsedUser.isImmortal());
	}
}