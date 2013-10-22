package com.lonepulse.zombielink.inject;

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

import org.junit.BeforeClass;
import org.junit.Test;



/**
 * <p>Performs <b>Unit Testing</b> on the {@link Zombie} and its injection capabilities.
 * 
 * @category test
 * <br><br>
 * @version 1.2.0
 * <br><br>
 * @since 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ZombieTest {
	

	/**
	 * <p>An instance of {@link BasicMockService} whose endpoint dependencies are satisfied 
	 * by injection.
	 */
	private static BasicMockService propertyInjectedService;
	
	
	/**
	 * <p>Sets up the test case by performing endpoint injection on {@link #propertyInjectedService} 
	 * and {@link #constructorInjectedService}.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error 
	 */
	@BeforeClass
	public static void setUp() throws Exception {

		//perform field and setter injection
		propertyInjectedService = new BasicMockService();
		Zombie.infect(propertyInjectedService);
	}
	
	/**
	 * Test method for {@link com.lonepulse.zombielink.inject.Zombie#infect(Object)}.
	 */
	@Test
	public final void testPropertyInjection() {
		
		assertNotNull(propertyInjectedService.getConstructedMockEndpoint());
		assertNotNull(propertyInjectedService.getDefaultMockEndpoint());
		assertNotNull(propertyInjectedService.getForcedPrivateMockEndpoint());
		assertNotNull(propertyInjectedService.getPrivateMockEndpoint());
		assertNotNull(propertyInjectedService.getProtectedMockEndpoint());
		assertNotNull(propertyInjectedService.getPublicMockEndpoint());
	}
}
