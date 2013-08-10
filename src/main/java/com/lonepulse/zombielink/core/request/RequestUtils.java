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


import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.SerializableEntity;
import org.apache.http.entity.StringEntity;

import com.lonepulse.zombielink.core.annotation.Entity;
import com.lonepulse.zombielink.core.annotation.Param;
import com.lonepulse.zombielink.core.annotation.QueryParam;
import com.lonepulse.zombielink.core.annotation.Request;
import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;
import com.lonepulse.zombielink.rest.annotation.Rest;

/**
 * <p>This utility class offers some common operations which are used in building requests - most commonly 
 * using the information contained within a {@link ProxyInvocationConfiguration}.
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @category utility
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class RequestUtils {
	
	
	/**
	 * <p>Constructor visibility restricted. Instantiation is nonsensical. 
	 */
	private RequestUtils() {}
	
	
	/**
	 * <p>Finds all <b><i>constant</i> request parameters</b> in the given {@link ProxyInvocationConfiguration}.</p> 
	 * <p>Constant request parameters are introduced with @{@link Request.Param} at <b>request level</b> using 
	 * either the @{@link Request} or the @{@link Rest} annotation.</p>
	 *
	 * @param config
	 * 			the {@link ProxyInvocationConfiguration} from which all {@link Request.Param} annotations applied 
	 * 			on the endpoint method will be extracted
	 * 
	 * @return an <b>unmodifiable</b> {@link List} which aggregates all the @{@link Request.Param} annotations found 
	 * 		   on the {@link Request} or {@link Rest}ful request 
	 * 
	 * @throws IllegalArgumentException
	 * 			if the supplied {@link ProxyInvocationConfiguration} was null
	 * 
	 * @since 1.2.4
	 */
	public static List<Request.Param> findConstantRequestParams(ProxyInvocationConfiguration config) {
		
		if(config == null) {
			
			new IllegalArgumentException("The supplied Proxy Invocation Configuration cannot be <null>.");
		}
		
		Method request = config.getRequest();
		
		Request webRequest = request.getAnnotation(Request.class);
		Rest restfulRequest = request.getAnnotation(Rest.class);
		
		Request.Param[] requestParams = (webRequest != null)? webRequest.params() :restfulRequest.params();
		
		return Collections.unmodifiableList(Arrays.asList(requestParams));
	}
	
	/**
	 * <p>Finds all <b>query parameters</b> in the given {@link ProxyInvocationConfiguration}.</p> 
	 * 
	 * <p>Query parameters are introduced with @{@link QueryParam} on the arguments to a request method.</p>
	 *
	 * @param config
	 * 			the {@link ProxyInvocationConfiguration} from which all @{@link QueryParam} annotations applied 
	 * 			on the endpoint method arguments will be extracted
	 * 
	 * @return an <b>unmodifiable</b> {@link Map} in the form {@code Map<name, value>} which aggregates all the 
	 * 		   param names coupled with the value of the linked method argument
	 * 
	 * @throws IllegalArgumentException
	 * 			if the supplied {@link ProxyInvocationConfiguration} was null 
	 * 
	 * @since 1.2.4
	 */
	public static Map<String, Object> findQueryParams(ProxyInvocationConfiguration config) {
		
		if(config == null) {
			
			new IllegalArgumentException("The supplied Proxy Invocation Configuration cannot be <null>.");
		}
		
		Map<String, Object> paramMap = new LinkedHashMap<String, Object>(); 
		
		Method request = config.getRequest();
		Object[] paramValues = config.getRequestArgs();
		
		Annotation[][] annotationsForAllParams = request.getParameterAnnotations();
		
		for (int i = 0; i < annotationsForAllParams.length; i++) {
			
			for (Annotation annotation : annotationsForAllParams[i]) {
				
				if(QueryParam.class.isAssignableFrom(annotation.getClass())) {
					
					paramMap.put(((Param)annotation).value(), paramValues[i]);
					break; //only one @QueryParam annotation is expected per endpoint method argument
				}
			}
		}
		
		return Collections.unmodifiableMap(paramMap);
	}
	
	/**
	 * <p>Finds all <b>request parameters</b> in the given {@link ProxyInvocationConfiguration}.</p> 
	 * 
	 * <p>Request parameters are introduced with @{@link Param} on arguments to an endpoint request method.</p>
	 *
	 * @param config
	 * 			the {@link ProxyInvocationConfiguration} from which all {@link Param} annotations applied 
	 * 			on the endpoint method arguments will be extracted
	 * 
	 * @return an <b>unmodifiable</b> {@link Map} in the form {@code Map<name, value>} which aggregates all the 
	 * 		   param names coupled with the value of the linked method argument
	 * 
	 * @throws IllegalArgumentException
	 * 			if the supplied {@link ProxyInvocationConfiguration} was null 
	 * 
	 * @since 1.2.4
	 */
	@Deprecated //TODO remove RequestUtils#findRequestParams(cofig) after the deprecation of @Param on API
	public static Map<String, Object> findRequestParams(ProxyInvocationConfiguration config) {
		
		if(config == null) {
			
			new IllegalArgumentException("The supplied Proxy Invocation Configuration cannot be <null>.");
		}
		
		Map<String, Object> paramMap = new LinkedHashMap<String, Object>(); 
		
		Method request = config.getRequest();
		Object[] paramValues = config.getRequestArgs();
		
		Annotation[][] annotationsForAllParams = request.getParameterAnnotations();
		
		for (int i = 0; i < annotationsForAllParams.length; i++) {
			
			for (Annotation annotation : annotationsForAllParams[i]) {
				
				if(Param.class.isAssignableFrom(annotation.getClass())) {
					
					paramMap.put(((Param)annotation).value(), paramValues[i]);
					break; //only one @Param annotation is expected per endpoint method argument
				}
			}
		}
		
		return Collections.unmodifiableMap(paramMap);
	}
	
	/**
	 * <p>Discovers which concrete implementation of {@link HttpEntity} is suitable for wrapping the given object. 
	 * This discovery proceeds in the following order by checking the runtime-type of the generic object:</p> 
	 *
	 * <ol>
	 * 	<li>org.apache.http.{@link HttpEntity} --&gt; returned as-is.</li> 
	 * 	<li>{@code byte[]}, {@link Byte}[] --&gt; {@link ByteArrayEntity}</li> 
	 *  <li>java.io.{@link File} --&gt; {@link FileEntity}</li>
	 * 	<li>java.io.{@link InputStream} --&gt; {@link BasicHttpEntity}</li>
	 * 	<li>{@link CharSequence} --&gt; {@link StringEntity}</li>
	 * 	<li>java.io.{@link Serializable} --&gt; {@link SerializableEntity} (with an internal buffer)</li>
	 * </ol>
	 *
	 * @param genericEntity
	 * 			a generic reference to an object whose concrete {@link HttpEntity} is to be resolved 
	 * 
	 * @return the resolved concrete {@link HttpEntity} implementation
	 * 
	 * @throws IllegalArgumentException
	 * 			if the supplied generic entity was null
	 * 
	 * @throws EntityResolutionFailedException
	 * 			if a specific {@link HttpEntity} implementation failed to be resolved
	 * 
	 * @since 1.2.4
	 */
	public static HttpEntity resolveHttpEntity(Object genericEntity) {
		
		if(genericEntity == null) {
			
			new IllegalArgumentException("The supplied generic entity cannot be <null>.");
		}
		
		try {
		
			if(genericEntity instanceof HttpEntity) {
				
				return (HttpEntity)genericEntity;
			}
			else if(byte[].class.isAssignableFrom(genericEntity.getClass())) {
				
				return new ByteArrayEntity(((byte[])genericEntity));
			}
			else if(Byte[].class.isAssignableFrom(genericEntity.getClass())) {
				
				Byte[] wrapperBytes = (Byte[])genericEntity;
				byte[] primitiveBytes = new byte[wrapperBytes.length];
				
				for (int i = 0; i < wrapperBytes.length; i++) {
					
					primitiveBytes[i] = wrapperBytes[i].byteValue();
				}
				
				return new ByteArrayEntity(primitiveBytes);
			}
			else if(genericEntity instanceof File) {
				
				return new FileEntity((File)genericEntity);
			}
			else if(genericEntity instanceof InputStream) {
				
				BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
				basicHttpEntity.setContent((InputStream)genericEntity);
				
				return basicHttpEntity;
			}
			else if(genericEntity instanceof CharSequence) {
				
				return new StringEntity(((CharSequence)genericEntity).toString());
			}
			else if(genericEntity instanceof Serializable) {
				
				return new SerializableEntity((Serializable)genericEntity, true);
			}
			else {
				
				throw new EntityResolutionFailedException(genericEntity);
			}
		}
		catch(Exception e) {

			throw (e instanceof EntityResolutionFailedException)?
					(EntityResolutionFailedException)e :new EntityResolutionFailedException(genericEntity, e);
		}
	}
	
	/**
	 * <p>Finds the single <b>entity parameter</b> in the given {@link ProxyInvocationConfiguration} which 
	 * is identified by placing an @{@link Entity} annotation on an endpoint method argument. If found, the 
	 * corresponding {@link HttpEntity} is resolved via {@link #resolveHttpEntity(Object)} and returned.</p> 
	 * 
	 * <p>Only one such entity is expected to be found, if multiple @{@link Entity} annotations are discovered 
	 * a {@link MultipleEntityException} is thrown. If no @{@link Entity} annotation is discovered a
	 * {@link MissingEntityException} is thrown. These might be caught and recovered from if preferred.</p>
	 *
	 * @param config
	 * 			the {@link ProxyInvocationConfiguration} whose single entity is to be discovered and resolved
	 * 
	 * @return the resolved {@link HttpEntity} which was discovered as an argument annotated with @{@link Entity}
	 * 
	 * @throws IllegalArgumentException
	 * 			if the {@link ProxyInvocationConfiguration} was {@code null}
	 * 
	 * @throws MultipleEntityException
	 * 			if many arguments were found to be annotated with @{@link Entity} 
	 * 
	 * @throws MissingEntityException
	 * 			if no arguments were found to be annotated with @{@link Entity}
	 * 
	 * @throws EntityResolutionFailedException
	 * 			if a specific {@link HttpEntity} implementation failed to be resolved
	 * 
	 * @since 1.2.4
	 */
	public static HttpEntity findAndResolveEntity(ProxyInvocationConfiguration config) {
		
		Object[] paramValues = config.getRequestArgs();
		Annotation[][] annotationsForAllParams = config.getRequest().getParameterAnnotations();
		
		List<Object> entities = new ArrayList<Object>();
		
		for (int i = 0; i < annotationsForAllParams.length; i++) {
			
			for (Annotation annotation : annotationsForAllParams[i]) {

				if(Entity.class.isAssignableFrom(annotation.getClass())) {
					
					entities.add(paramValues[i]);
					break; //only one @Entity annotation is expected per endpoint method argument
				}
			}
		}
		
		if(entities.isEmpty()) {
			
			throw new MissingEntityException(config);
		}
		
		if(entities.size() > 1) {
			
			throw new MultipleEntityException(config);
		}
		
		return RequestUtils.resolveHttpEntity(entities.get(0));
	}
}
