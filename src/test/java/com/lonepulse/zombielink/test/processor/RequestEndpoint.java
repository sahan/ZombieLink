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


import com.lonepulse.zombielink.annotation.Endpoint;
import com.lonepulse.zombielink.annotation.Parser;
import com.lonepulse.zombielink.annotation.Parser.ParserType;
import com.lonepulse.zombielink.annotation.QueryParam;
import com.lonepulse.zombielink.annotation.Request;
import com.lonepulse.zombielink.annotation.Stateful;

/**
 * <p>An interface which represents a dummy endpoint with request method definitions.
 * 
 * @category test
 * <br><br> 
 * @version 1.1.1
 * <br><br> 
 * @since 1.2.4
 * <br><br> 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Stateful
@Parser(ParserType.STRING)
@Endpoint(host = "0.0.0.0", port = "8080")
public interface RequestEndpoint {
	
	/**
	 * <p>Sends a request with the given query parameters.
	 * 
	 * @param firstName
	 * 			the first query param
	 * 
	 * @param lastName
	 * 			the second query param
	 * 
	 * @return a response for the request with query params
	 * 
	 * @since 1.2.4
	 */
	@Request(path = "/subpathwithparams")
	public String subpathWithParams(@QueryParam("firstName") String firstName, 
									@QueryParam("lastName") String lastName);
	
	/**
	 * <p>Sends a request with a set of constant query paramers.
	 * 
	 * @return a response for the request with constant params
	 * 
	 * @since 1.2.4
	 */
	@Request(path = "/subpathwithconstparams", params = {@Request.Param(name = "firstName", value = "Doctor"), 
										 				 @Request.Param(name = "lastName", value = "Who")})
	public String subpathWithConstParams();
}
