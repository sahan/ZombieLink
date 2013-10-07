package com.lonepulse.zombielink.executor;

/*
 * #%L
 * ZombieLink
 * %%
 * Copyright (C) 2013 Lonepulse
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import com.lonepulse.zombielink.annotation.Configuration;
import com.lonepulse.zombielink.annotation.Endpoint;
import com.lonepulse.zombielink.annotation.Parser;
import com.lonepulse.zombielink.annotation.Parser.ParserType;
import com.lonepulse.zombielink.annotation.Request;
import com.lonepulse.zombielink.inject.Zombie;

/**
 * <p>An interface which represents a dummy endpoint that uses a custom {@link Zombie.Configuration}.
 * 
 * @version 1.1.1
 * <br><br> 
 * @since 1.2.4
 * <br><br> 
 * @category test
 * <br><br> 
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Parser(ParserType.STRING)
@Configuration(ZombieConfig.class)
@Endpoint(host = "0.0.0.0", port = "8080")
public interface ConfigEndpoint {
	
	
	@Request(path = "/timeout")
	public void timeout();
}
