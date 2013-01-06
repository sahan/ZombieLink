/**
 * 
 */
package com.lonepulse.zombielink.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.lonepulse.zombielink.core.request.RequestMethod;

/**
 * <p>Marks the policy of a <b>remote endpoint</b>. For example, an interface which 
 * mirrors the method signatures of a web service contract.</p>
 * <br>
 * <p>By default, all the methods in the interface are considered to be web requests 
 * (without any explicit annotations) having a default <b>HTTP</b> method type of 
 * {@link RequestMethod#HTTP_GET}.</p>
 *
 * <b>Usage:</b>
 * <br>
 * <br>
 * <p>
 * <code>
 * <pre><b>@Endpoint("api.twitter.com")</b><br>public interface TwitterEndpoint {<br>}</pre>
 * </code>
 * </p>
 * 
 * @version 1.1.2
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Endpoint {

	/**
	 * <p>The scheme of the protocol used for communication. The default 
	 * value for the scheme is <code>http</code>.</p>
	 * 
	 * @return the protocol scheme
	 * <br><br>
	 * @since 1.1.2
	 */
	public String scheme() default "http";
	
	/**
	 * <p>The hostname of the endpoint.</p> 
	 * 
	 * @return the hostname of the endpoint
	 * <br><br>
	 * @since 1.1.2
	 */
	public String value();
	
	/**
	 * <p>The <b>port</b> through which a channel is opened for communication 
	 * with the endpoint.</p> 
	 * 
	 * <p>Leaving this blank allows the URI builder to assume the default port 
	 * associated with the scheme. For example, port <code>80</code> for <code>
	 * HTTP</code> or port <code>443</code> for <code>HTTPS</code>.</p>  
	 * 
	 * @return the port through which communication occurs
	 * <br><br>
	 * @since 1.1.2
	 */
	public String port() default "";

	/**
	 * <p>The path hierarchy of the <b>URI</b> on which the resource is located. 
	 * If no path is given, the resource is assumed to be found at the root 
	 * (host).</p>    
	 * 
	 * @return the path from the host at which the resource is located
	 * <br><br>
	 * @since 1.1.2
	 */
	public String path() default "";
}
