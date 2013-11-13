package com.lonepulse.zombielink.response;

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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.lonepulse.zombielink.inject.InvocationContext;

/**
 * <p>An abstract implementation of {@link Deserializer} which directs <i>deserialization</i> for 
 * all {@link Deserializer}s. To create a custom {@link Deserializer} extend this class and override 
 * {@link #deserialize(HttpResponse, InvocationContext)}.</p> 
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class AbstractDeserializer<OUTPUT> implements Deserializer<OUTPUT> {

	
	private Class<OUTPUT> outputType;
	
	
	/**
	 * <p>Initializes a new {@link AbstractDeserializer} with the given {@link Class} which 
	 * represents the output of this deserializer.
	 *
	 * @param outputType
	 * 			the {@link Class} type of the entity which is produced by this deserializer
	 *
	 * @since 1.2.4
	 */
	public AbstractDeserializer(Class<OUTPUT> outputType) {
		
		this.outputType = outputType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final OUTPUT run(HttpResponse httpResponse, InvocationContext config) {
		
		Class<?> requestReturnType = config.getRequest().getReturnType();
		
		try {
			
			throwIfNotAssignable(requestReturnType);
			return deserialize(httpResponse, config);
		}
		catch(Exception e) {
		
			throw new DeserializerException(e);
		}
	}
	
	/**
	 * <p>Checks if the desired request return type can be instantiated from an instance of the 
	 * deserializer's output type.</p>
	 * 
	 * @param requestReturnType
	 * 			the {@link Class} of the request return type
	 */
	private void throwIfNotAssignable(Class<? extends Object> requestReturnType) {
		
		if(!void.class.isAssignableFrom(requestReturnType)
		   && !Void.class.isAssignableFrom(requestReturnType)
		   && !outputType.isAssignableFrom(requestReturnType)) {
			
			throw new DeserializerNotAssignableException(outputType, requestReturnType);
		}
	}
	
	/**

	 * 
	 * <p><b>Note</b> that all <i>output</i>s are translated to a corresponding {@link HttpEntity} for 
	 * enclosure within an {@link HttpRequest}. Therefore, all output instances should comply to a type 
	 * specified at {@link Entities#resolve(Object)}.</p> 
	 * 
	 * @param input
	 * 			the <i>input</i> model to be serialized to a transmittable format
	 * 
	 * @param context
	 * 			the {@link InvocationContext} which supplies information on the proxy invocation
     * <br><br>
	 * @return the serialized <i>output</i> which will be translated to an {@link HttpEntity}   
	 * <br><br>
	 * @throws Exception 
	 * 			any errors which might occur during serialization are caught, wrapped in an instance of 
	 * 			{@link SerializerException} (cause preserved) and allowed to bubble up
	 * <br><br>
	 * @since 1.2.4
	 */
	/**
	 * <p>Takes an {@link HttpResponse} which resulted from a successful request execution and deserializes 
	 * its content to an instance of the output type.</p>
	 * 
	 * <p>The response {@link HttpEntity} can be obtained using {@link HttpResponse#getEntity()} and it's 
	 * content can be <i>stringified</i> with {@link EntityUtils#toString(HttpEntity)}.</p>
	 * 
	 * <p><b>Note</b> that certain {@link HttpResponse}s may not contain an {@link HttpEntity} which areturn {@code null} up#getEntity()}
	 * 
	 * @param httpResponse
	 * 				the {@link HttpResponse} of a successful request execution
	 * <br><br>
	 * @param config
	 * 				the {@link InvocationContext} which supplies all information 
	 * 				regarding the request and it's invocation
     * <br><br>
	 * @return the entity which is created after deserializing the output
	 * <br><br>
	 * @throws Exception 
	 * 				deserialization failures may occur due to many reasons
	 * <br><br>
	 * @since 1.1.4
	 */
	protected abstract OUTPUT deserialize(HttpResponse httpResponse, InvocationContext config) 
	throws Exception;
}
