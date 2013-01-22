package com.lonepulse.zombielink.test.endpoint;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.lonepulse.zombielink.core.annotation.Endpoint;
import com.lonepulse.zombielink.core.annotation.Request;
import com.lonepulse.zombielink.core.inject.Zombie;
import com.lonepulse.zombielink.test.model.ICNDBResponse;
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
	private ICNDBEndpoint icndbEndpoint;
	
	
	/**
	 * <p>Sets up the test case by performing endpoint injection on 
	 * {@link #icndbEndpoint} and {@link #icndbServiceInstantiated}.
	 * 
	 * @throws java.lang.Exception
	 * 			if the endpoint injection failed
	 */
	@Before
	public void setUp() throws Exception {
		
		icndbEndpoint = Zombie.infect(ICNDBService.class).getConstructedICNDBEndpoint();
	}

	/**
	 * <p>Test method for {@link Endpoint#path()} and {@link Request#path()}.
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
		
		String firstName = "Lahiru";
		String lastName = "Sahan J.";
		
		NorrisJoke norrisJoke = icndbEndpoint.random(firstName, lastName).getValue();

		assertTrue(norrisJoke.getJoke().contains(firstName));
		assertTrue(norrisJoke.getJoke().contains(lastName));
	}
}
