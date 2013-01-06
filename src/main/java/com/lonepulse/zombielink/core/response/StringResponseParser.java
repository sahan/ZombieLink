/**
 * 
 */
package com.lonepulse.zombielink.core.response;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.lonepulse.zombielink.core.processor.AbstractResponseParser;

/**
 * <p>This is an extension of {@link AbstractResponseParser} which allows the parsing 
 * of character data. 
 * 
 * @version 1.1.4
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class StringResponseParser extends AbstractResponseParser<CharSequence> {

	/**
	 * <p> Parses the content in the {@link HttpResponse} to any type which is 
	 * assignable to a {@link CharSequence}.
	 * 
	 * @see AbstractResponseParser#parse(HttpResponse, com.lonepulse.zombielink.core.processor.ProxyInvocationConfiguration)
	 */
	@Override
	public CharSequence processResponse(HttpResponse httpResponse) throws Exception {

		String responseString = EntityUtils.toString(httpResponse.getEntity());
		return responseString.subSequence(0, responseString.length());
	}

	/**
	 * @see com.lonepulse.zombielink.core.processor.AbstractResponseParser#getType()
	 */
	@Override
	public Class<CharSequence> getType() {

		return CharSequence.class;
	}
}
