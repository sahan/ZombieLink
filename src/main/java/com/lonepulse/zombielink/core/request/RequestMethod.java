package com.lonepulse.zombielink.core.request;

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

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;

import com.lonepulse.zombielink.core.annotation.Request;
import com.lonepulse.zombielink.rest.annotation.Rest;

/**
 * <p>This enum is used to identify the request types as specified in <a href="">Section 9</a> of the HTTP 
 * 1.1 RFC. These request methods are common for both {@link Request}s and {@link Rest}ful requests.
 * 
 * @version 1.1.4
 * <br><br>
 * @since 1.1.0
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum RequestMethod {

	/**
	 * <p>Identifies an {@link HttpGet} request.
	 * 
	 * @since 1.1.0
	 */
	GET,
	
	/**
	 * <p>Identifies an {@link HttpPost} request.
	 * 
	 * @since 1.1.0
	 */
	POST,
	
	/**
	 * <p>Identifies an {@link HttpPut} request.
	 * 
	 * @since 1.1.0
	 */
	PUT,
	
	/**
	 * <p>Identifies an {@link HttpDelete} request.
	 * 
	 * @since 1.1.0
	 */
	DELETE,
	
	/**
	 * <p>Identifies an {@link HttpHead} request.
	 * 
	 * @since 1.1.0
	 */
	HEAD,
	
	/**
	 * <p>Identifies an {@link HttpTrace} request.
	 * 
	 * @since 1.1.0
	 */
	TRACE,
	
	/**
	 * <p>Identifies an {@link HttpOptions} request.
	 * 
	 * @since 1.1.0
	 */
	OPTIONS;
}
