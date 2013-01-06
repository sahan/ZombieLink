/**
 * 
 */
package com.lonepulse.zombielink.core.request;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import com.lonepulse.zombielink.core.annotation.Param;
import com.lonepulse.zombielink.core.annotation.Request;
import com.lonepulse.zombielink.core.processor.AnnotationExtractor;
import com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration;


/**
 * <p>This is an implementation of {@link AbstractRequestBuilder} which handles the 
 * request creation for the basic HTTP method types GET, POST, PUT, DELETE.</p>
 * 
 * @version 1.1.3
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class BasicRequestBuilder extends AbstractRequestBuilder {

	
	/* TODO Rationale: static final class HttpParamBuilder
	 * 
	 * Decoupled into an inner class and exposed with a public access modifier to allow reuse 
	 * in other {@link AbstractRequestBuilder}s which require HTTP parameter population.
	 * 
	 * Q: So why not decouple into an entirely separate utility class? 
	 */
	
	/**
	 * <p>Responsible for populating the parameters for an {@link HttpRequest} depending 
	 * on the <i>request method</i>.</p>
	 * 
	 * @version 1.1.1
	 * <br><br>
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public static final class HttpParamBuilder {
		
		
		/**
		 * <p>Takes the base URI from a {@link URIBuilder} and parameters from the 
		 * {@link ProxyInvocationConfiguration} to create a {@link HttpRequestBase} 
		 * of the designated HTTP method type.</p>
		 * 
		 * @param uri
		 * 			the {@link URI} which locates the target of the request
		 * 
		 * @param config
		 * 			the {@link ProxyInvocationConfiguration} which supplies the parameters
		 * <br><br>
		 * @return the created {@link HttpRequestBase} with the request parameters populated
		 * <br><br>
		 * @since 1.1.4
		 */
		public static HttpRequestBase build(URI uri, ProxyInvocationConfiguration config) {
		
			Method request = config.getRequest();
			Request webRequest = request.getAnnotation(Request.class); //annotation may not exist
			
			Map<Object, Param> argParams = AnnotationExtractor.<Param>extractWithParameterValues(Param.class, request, config.getRequestArgs());
			List<Request.Param> methodParams = Arrays.asList(webRequest.params());
			
			RequestMethod httpMethod = (webRequest == null)? RequestMethod.HTTP_GET :webRequest.method();

			URIBuilder uriBuilder = new URIBuilder(uri);
			
			try {
				
				switch (httpMethod) { //TODO follow the proper builder pattern!!
				
					case HTTP_GET: {
						
						return populateGetParameters(uriBuilder, argParams, methodParams);
					}
					case HTTP_POST: {
						
						return populatePostParameters(uriBuilder, argParams, methodParams);
					}
					case HTTP_PUT: { 
						
						return populatePutParameters(uriBuilder, argParams, methodParams);
					}
					case HTTP_DELETE: { 
						
						return populateDeleteParameters(uriBuilder, argParams, methodParams);
					}
					
					default: {
						
						return null;
					}
				}
			} 
			catch (Throwable t) {

				throw new ParamPopulatorException(HttpParamBuilder.class, config, t);
			}
		}
		
		/**
		 * <p>Takes the extracted base URI and parameters from the {@link RequestConfig} consolidated 
		 * parameter type and creates a {@link HttpRequestBase} of the designated method type.</p>
		 * 
		 * @param uriBuilder
		 * 				the instance of {@link URIBuilder} which is used to create the root {@link URI}
		 * 
		 * @param annotatedParams
		 * 				the list of {@link Param}s and the parameter objects which were annotated 
		 * 				with them
		 * 
		 * @param staticParams
		 * 				the list of {@link Request.Param}s and the parameter objects which were 
		 * 				annotated with them
		 * <br><br>
		 * @return the created {@link HttpRequestBase} which is an instance of {@link HttpGet}
		 * <br><br>
		 * @throws Throwable
		 * 				when the {@link HttpRequestBase} could not be created due to an exception
		 * <br><br>
		 * @since 1.1.2
		 */
		private static HttpRequestBase populateGetParameters(URIBuilder uriBuilder, 
														     Map<Object, Param> annotatedParams, 
														     List<Request.Param> staticParams) throws Throwable {
			
			for (Request.Param param : staticParams)
				uriBuilder.setParameter(param.name(), param.value());
			
			Set<Object> methodParams = annotatedParams.keySet();
			
			for (Object object : methodParams) {
				
				if(!(object instanceof String)) {
				
					StringBuilder message = new StringBuilder();

					message.append("Parameters for GET requests can only be of type ");
					message.append(String.class.getName());
					message.append("\nIf it is a complex type, consider overriding toString() " + 
								   "and providing a meaningful String representation");
					
					throw new IllegalArgumentException(message.toString());
				}
			
				uriBuilder.setParameter(annotatedParams.get(object).value(), (String)object);
			}
			
			return new HttpGet(uriBuilder.build());
		}
		
		/**
		 * <p>Takes the extracted base URI and parameters from the {@link RequestConfig} consolidated parameter 
		 * type and creates a {@link HttpRequestBase} of the designated method type.
		 * 
		 * @param uriBuilder
		 * 				the instance of {@link URIBuilder} which is used to create the root {@link URI}
		 * 
		 * @param annotatedParams
		 * 				the list of {@link Param}s and the parameter objects which were annotated with them; 
		 * 				<b>Complex objects should supply a formatted version of their String representation via 
		 * 				{@link Object#toString()}</b>
		 * 
		 * @param staticParams
		 * 				the list of {@link Request.Param}s and the parameter objects which were annotated
		 * 				with them
		 * <br><br>
		 * @return the created {@link HttpRequestBase} which is an instance of {@link HttpPost}
		 * <br><br>
		 * @throws Throwable
		 * 				when the {@link HttpRequestBase} could not be created due to an exception
		 * <br><br>
		 * @since 1.1.2
		 */
		private static HttpRequestBase populatePostParameters(URIBuilder uriBuilder, 
															  Map<Object, Param> annotatedParams, 
															  List<Request.Param> staticParams) throws Throwable {
			
			List <NameValuePair> nameValuePairs = new ArrayList <NameValuePair>();
			
			for (Request.Param param : staticParams)
				nameValuePairs.add(new BasicNameValuePair(param.name(), param.value()));
			
			Set<Object> methodParams = annotatedParams.keySet();
			
			for (Object object : methodParams)
				nameValuePairs.add(new BasicNameValuePair(annotatedParams.get(object).value(), object.toString()));
			
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs);
			urlEncodedFormEntity.setContentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
			
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			httpPost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			return httpPost;
		}
		
		/**
		 * <p>Takes the extracted base URI and parameters from the {@link RequestConfig} consolidated 
		 * parameter type and creates a {@link HttpRequestBase} of the designated method type.
		 * 
		 * @param uriBuilder
		 * 				the instance of {@link URIBuilder} which is used to create the root {@link URI}
		 * 
		 * @param annotatedParams
		 * 				the list of {@link Param}s and the parameter objects which were annotated with 
		 * 				them; <b>Complex objects should supply a formatted version of their String 
		 * 				representation via {@link Object#toString()}</b>
		 * 
		 * @param staticParams
		 * 				the list of {@link Request.Param}s and the parameter objects which were annotated 
		 * 				with them
		 * <br><br>
		 * @return the created {@link HttpRequestBase} which is an instance of {@link HttpPost}
		 * <br><br>
		 * @throws Throwable
		 * 				when the {@link HttpRequestBase} could not be created due to an exception
		 * <br><br>
		 * @since 1.1.3
		 */
		private static HttpRequestBase populatePutParameters(URIBuilder uriBuilder, 
													  Map<Object, Param> annotatedParams, 
													  List<Request.Param> staticParams) throws Throwable {
			
			List <NameValuePair> nameValuePairs = new ArrayList <NameValuePair>();
			
			for (Request.Param param : staticParams)
				nameValuePairs.add(new BasicNameValuePair(param.name(), param.value()));
			
			Set<Object> methodParams = annotatedParams.keySet();
			
			for (Object object : methodParams)
				nameValuePairs.add(new BasicNameValuePair(annotatedParams.get(object).value(), object.toString()));
			
			
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs);
			urlEncodedFormEntity.setContentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
			
			HttpPut httpPut = new HttpPut(uriBuilder.build());
			httpPut.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
			httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			return httpPut;
		}
		
		/**
		 * <p>Takes the extracted base URI and parameters from the {@link RequestConfig} consolidated 
		 * parameter type and creates a {@link HttpRequestBase} of the designated method type.</p>
		 * 
		 * @param uriBuilder
		 * 				the instance of {@link URIBuilder} which is used to create the root {@link URI}
		 * 
		 * @param annotatedParams
		 * 				the list of {@link Param}s and the parameter objects which were annotated with them; 
		 * 				<b>Complex objects should supply a formatted version of their String representation 
		 * 				via {@link Object#toString()}</b>
		 * 
		 * @param staticParams
		 * 				the list of {@link Request.Param}s and the parameter objects which were annotated 
		 * 				with them
		 * <br><br>
		 * @return the created {@link HttpRequestBase} which is an instance of {@link HttpPost}
		 * <br><br>
		 * @throws Throwable
		 * 				when the {@link HttpRequestBase} could not be created due to an exception
		 * <br><br>
		 * @since 1.1.3
		 */
		
		private static HttpRequestBase populateDeleteParameters(URIBuilder uriBuilder, 
														        Map<Object, Param> annotatedParams, 
														        List<Request.Param> staticParams) throws Throwable {
			
			HttpParams httpParams = new BasicHttpParams();
			
			for (Request.Param param : staticParams)
				httpParams.setParameter(param.name(), param.value());
			
			Set<Object> methodParams = annotatedParams.keySet();
			
			for (Object object : methodParams)
				httpParams.setParameter(annotatedParams.get(object).value(), object.toString());
			
			HttpDelete httpDelete = new HttpDelete(uriBuilder.build());
			httpDelete.setParams(httpParams);
			
			return httpDelete;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected URI buildPath(ProxyInvocationConfiguration config) throws Exception {
		
		Method request = config.getRequest();
		Request webRequest = request.getAnnotation(Request.class); //annotation may not exist
		
		String subPath = (webRequest == null)? "" :webRequest.path();
		URI uri =  config.getUri();
		URIBuilder uriBuilder = new URIBuilder(uri);
		uriBuilder.setPath(uriBuilder.getPath() + subPath);
		
		return uriBuilder.build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HttpRequestBase buildRequestWithParameters(URI uri, ProxyInvocationConfiguration config) throws Exception {
		
		return BasicRequestBuilder.HttpParamBuilder.build(uri, config);
	}
}
