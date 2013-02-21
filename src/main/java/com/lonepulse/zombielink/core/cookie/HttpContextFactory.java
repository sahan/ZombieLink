package com.lonepulse.zombielink.core.cookie;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.lonepulse.zombielink.util.GenericFactory;

/**
 * <p>Follows the {@link GenericFactory} policy to create {@link HttpContext}s 
 * for a given endpoint {@link Class}.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum HttpContextFactory implements GenericFactory<Void, HttpContext> {

	
	/**
	 * <p>The single instance of the {@link HttpContextFactory}.
	 * 
	 * @since 1.1.0
	 */
	INSTANCE;

	
	/**
	 * See {@link GenericFactory#newInstance(Object, Object...)}.
	 */
	@Override
	public HttpContext newInstance(Void input, Void... inputs) {
		
		StringBuilder builder = new StringBuilder()
		.append(getClass().getName())
		.append(".newInstance(Void, Void...) is unsupported. Use ")
		.append(getClass().getSimpleName())
		.append(".newInstance() instead");
		
		throw new UnsupportedOperationException(builder.toString());
	}
	
	/**
	 * See {@link GenericFactory#newInstance()}.
	 */
	@Override
	public HttpContext newInstance() {
		
		CookieStore cookieStore = new BasicCookieStore();
		HttpContext httpContext = new BasicHttpContext();
		
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		
		return httpContext;
	}
}
