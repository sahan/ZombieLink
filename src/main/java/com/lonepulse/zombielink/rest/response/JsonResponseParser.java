/**
 * 
 */
package com.lonepulse.zombielink.rest.response;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.lonepulse.zombielink.core.processor.AbstractResponseParser;

/**
 * <p>This is an extension of {@link AbstractResponseParser} which allows the parsing 
 * of JSON strings into its entity-object counterpart.</p>
 * 
 * <p>This parser uses the <a href="http://code.google.com/p/google-gson/">GSON Library</a> 
 * for converting JSON strings to their respective entity objects automatically.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class JsonResponseParser extends AbstractResponseParser<Object> {

	/**
     * <p> Parses the JSON String in the {@link HttpResponse} via the <b>GSON library</b> 
     * and returns the entity representing the JSON data.
	 */
	@Override
	protected Object processResponse(HttpResponse httpResponse) throws Exception {
		
		String jsonString = EntityUtils.toString(httpResponse.getEntity());
		return new Gson().fromJson(jsonString, getRequestReturnType());
		
		//TODO what about JSON arrays
	}

	/**
	 * @see com.lonepulse.zombielink.core.processor.AbstractResponseParser#getType()
	 */
	@Override
	protected Class<Object> getType() {
		
		return Object.class;
	}
}
