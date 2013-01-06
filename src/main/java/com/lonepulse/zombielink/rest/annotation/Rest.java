/**
 * 
 */
package com.lonepulse.zombielink.rest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.lonepulse.zombielink.core.annotation.Endpoint;
import com.lonepulse.zombielink.core.annotation.Param;
import com.lonepulse.zombielink.core.request.RequestMethod;

/**
 * <p>This annotation is used to tag a method which initiates a <i>RESTFul request</i>.
 * 
 * The {@link Rest#path()} may be marked with parameter place-holders having the
 * format <i>:<parameter_name></i> as shown below:</p>
 * 
 * <p><code>@Rest(path = "/:artist/:song")</code></p>
 * 
 * <p>The interface method parameters may then be annotated with {@link Param} and
 * the {@link Param#value()} given the same value as those in the place-holders:</p>
 * <p><code>String getPhoto(@Param(name = "artist"), @Param(name = "song"))</p>
 * 
 * <p><b>Parameter values that never change could be coded into the path itself.</b></p>  
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Rest {
	
	/**
	 * <p>The type of the HTTP request which can be indicated using 
	 * {@link RequestMethod}.</p>
	 * <br>
	 * <p>The default method type is {@link RequestMethod#HTTP_GET}.
	 * 
	 * @return the type of the HTTP request
	 * <br><br>
	 * @since 1.1.1
	 */
	public RequestMethod method() default RequestMethod.HTTP_GET;
	
	/**
	 * <p>A sub-path which which continues from the root hierarchy of the uri 
	 * If no sub path is given, resource is assumed to be found at the root 
	 * path given on the {@link Endpoint}.    
	 * 
	 * @return the sub-path on which the resource is located 
	 * <br><br>
	 * @since 1.1.2
	 */
	public String path() default "";
}
