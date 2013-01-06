/**
 * 
 */
package com.lonepulse.zombielink.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Identifies requests which should be executed <b>asynchronously</b>.</p>
 * 
 * <b>Usage:</b>
 * <br>
 * <br>
 * <ol>
 * <li>
 * <p>At <b>type-level</b> on an endpoint <i>interface</i>; marks all requests as asynchronous.<br>
 * <code>
 * <pre>@Endpoint<br><b>@Asynchronous</b><br>public interface TwitterEndpoint {<br>}</pre>
 * </code>
 * </li>
 *  
 * <li>
 * <p>At <b>method-level</b> on an endpoint <i>request</i>.</p><br>
 * <code>
 * <pre>@Request<br><b>@Asynchronous</b><br>public Set&lt;Mention&gt; getMentions();</pre>
 * </code>
 * </li>
 * </ol>
 * </p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Asynchronous {}
