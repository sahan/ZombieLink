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


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.http.HttpResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lonepulse.zombielink.core.ZombieLinkRuntimeException;
import com.lonepulse.zombielink.core.annotation.Asynchronous;
import com.lonepulse.zombielink.core.annotation.Endpoint;
import com.lonepulse.zombielink.core.annotation.Param;
import com.lonepulse.zombielink.core.annotation.Request;
import com.lonepulse.zombielink.core.inject.Zombie;
import com.lonepulse.zombielink.core.request.RequestMethod;
import com.lonepulse.zombielink.core.response.AsyncHandler;
import com.lonepulse.zombielink.rest.annotation.PathParam;
import com.lonepulse.zombielink.rest.annotation.Rest;
import com.lonepulse.zombielink.rest.response.parser.JsonResponseParser;
import com.lonepulse.zombielink.test.model.ICNDBResponse;
import com.lonepulse.zombielink.test.model.ICNDBResponseArray;
import com.lonepulse.zombielink.test.model.NorrisJoke;
import com.lonepulse.zombielink.test.service.ICNDBService;

/**
 * <p>Performs <b>Unit Testing</b> on the proxy of {@link ICNDBEndpoint}.
 * 
 * @category test
 * <br><br>
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ICNDBEndpointTest {

	
	/**
	 * <p>A typical {@link ICNDBEndpoint} instance.
	 */
	private static ICNDBEndpoint icndbEndpoint;
	
	
	/**
	 * <p>Sets up the test case by performing endpoint injection on 
	 * {@link #icndbEndpoint} and {@link #icndbServiceInstantiated}.
	 * 
	 * @throws java.lang.Exception
	 * 			if the endpoint injection failed
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		
		icndbEndpoint = Zombie.infect(ICNDBService.class).getConstructedICNDBEndpoint();
	}

	/**
	 * <p>Test method for {@link Endpoint#path()} and {@link Request#path()} 
	 * with {@link JsonResponseParser}.
	 */
	@Test
	public final void testPath() {
		
		ICNDBResponse icndbResponse = icndbEndpoint.random();
		
		assertNotNull(icndbResponse);
		assertNotNull(icndbResponse.getValue());
	}
	
	/**
	 * <p>Test method for {@link Param} with {@link RequestMethod#HTTP_GET}.
	 */
	@Test
	public final void testGETParams() {
		
		String firstName = "John";
		String lastName = "Doe";
		
		NorrisJoke norrisJoke = icndbEndpoint.random(firstName, lastName).getValue();

		assertTrue(norrisJoke.getJoke().contains(firstName));
		assertTrue(norrisJoke.getJoke().contains(lastName));
	}
	
	/**
	 * <p>Test method for {@link Request.Param}.
	 */
	@Test
	public final void testConstantParams() {
		
		NorrisJoke norrisJoke = icndbEndpoint.randomJohnDoeJoke().getValue();
		
		assertTrue(norrisJoke.getJoke().contains("John"));
		assertTrue(norrisJoke.getJoke().contains("Doe"));
	}
	
	/**
	 * <p>Test method for {@link Rest} and {@link PathParam}.
	 */
	@Test
	public final void testPathParam() {
		
		ICNDBResponseArray icndbResponse = icndbEndpoint.random("4");
		
		assertNotNull(icndbResponse);
		assertTrue(icndbResponse.getValue().size() == 4);
	}
	
	/**
	 * <p>Test method for {@link Asynchronous} and {@link AsyncHandler}.
	 */
	@Test
	public final void testAsyncRequest() {
		
		ICNDBResponseArray synchronousResult = icndbEndpoint.randomAsync(new AsyncHandler<ICNDBResponseArray>() {

			@Override
			public void onSuccess(HttpResponse httpResponse, ICNDBResponseArray icndbResponseArray) 
			throws ZombieLinkRuntimeException {

				assertNotNull(icndbResponseArray);
				assertNotNull(icndbResponseArray.getValue());
				assertTrue(icndbResponseArray.getValue().size() == 10);
			}
		});
		
		assertNull(synchronousResult);
	}
}
