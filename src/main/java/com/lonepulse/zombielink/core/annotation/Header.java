/**
 * 
 */
package com.lonepulse.zombielink.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Marks a header parameter which is likely to change at runtime.</p>
 * 
 * <b>Usage:</b>
 * <br>
 * <br>
 * <p>
 * <code>
 * <pre>public abstract String getRSSFeed(<b>@Header</b>("Accept-Charset") String charSet);</pre>
 * </code>
 * </p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Header {
	
	/**
	 * <p>The name of the header parameter.</p>
	 * 
	 * @return the name of the header parameter
	 * <br><br>
	 * @since 1.1.1
	 */
	public String value();
}
