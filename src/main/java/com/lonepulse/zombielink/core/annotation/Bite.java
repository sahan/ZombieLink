/**
 * 
 */
package com.lonepulse.zombielink.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Marks an endpoint reference whose proxy is to be created and injected.</p>
 * 
 * <b>Usage:</b>
 * <br>
 * <br>
 * <p>
 * <code>
 * <pre><b>@Bite</b><br>private TwitterEndpoint twitterEndpoint;</pre>
 * </code>
 * </p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bite {}
