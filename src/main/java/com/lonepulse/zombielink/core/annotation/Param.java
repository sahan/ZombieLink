/**
 * 
 */
package com.lonepulse.zombielink.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Marks a parameter to be sent with the request.</p> 
 * 
 * <b>Usage:</b>
 * <br>
 * <br>
 * <p>
 * <code>
 * <pre>public abstract String search(<b>@Param("query")</b> String searchTerm);</pre>
 * </code>
 * </p>
 * 
 * @version 1.1.3
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {
	
	
	/**
	 * <p>The name of the request parameter.</p>
	 * 
	 * @return the name of the request parameter
	 * <br><br>
	 * @since 1.1.1
	 */
	public String value();
	
	/**
	 * <p>The <i>serialized {@link String} representation</i> of 
	 * a default value.</p>
	 * 
	 * @return a default value for the parameter (as a serialized 
	 * 		   {@link String})
	 * <br><br>
	 * @since 1.1.2
	 */
	public String defaultValue() default "";
}
