package com.lonepulse.zombielink.test.endpoint;

import com.lonepulse.zombielink.core.annotation.Endpoint;
import com.lonepulse.zombielink.core.annotation.Param;
import com.lonepulse.zombielink.core.annotation.Parser;
import com.lonepulse.zombielink.core.annotation.Parser.PARSER_TYPE;
import com.lonepulse.zombielink.core.annotation.Request;
import com.lonepulse.zombielink.test.model.ICNDBResponse;
import com.lonepulse.zombielink.test.model.NorrisJoke;

/**
 * <p>An endpoint interface which defines the contract to the 
 * <a href="http://www.icndb.com/api/">ICNDB API</a>.
 * 
 * @version 1.0.0
 * <br><br> 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Endpoint("http://api.icndb.com/jokes/")
@Parser(PARSER_TYPE.JSON)
public interface ICNDBEndpoint {
	
	/**
	 * <p>Retrieves a random {@link NorrisJoke}.
	 * 
	 * @return a random {@link NorrisJoke}.
	 * 
	 * @since 1.0.0
	 */
	@Request(path = "/random")
	public ICNDBResponse random();
	
	/**
	 * <p>Retrieves a random {@link NorrisJoke} with the name 
	 * <i>Chuck Norris</i> replaced by the given parameters.
	 * 
	 * @param firstName
	 * 			the name to replace <i>Chuck</i>
	 * 
	 * @param lastName
	 * 			the name to replace <i>Norris</i>
	 * 
	 * @return a random {@link NorrisJoke}
	 * 
	 * @since 1.0.0
	 */
	@Request(path = "/random")
	public ICNDBResponse random(@Param("firstName") String firstName, 
								@Param("lastName") String lastName);
}
