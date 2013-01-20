package com.lonepulse.zombielink.core.inject;

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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lonepulse.zombielink.core.annotation.Bite;
import com.lonepulse.zombielink.core.processor.EndpointProxyFactory;

/**
 * <p>Injects instance variables with the appropriate endpoint proxy. This hides 
 * the use of the {@link EndpointProxyFactory} and allows for a easy code integration.
 * 
 * @version 1.1.2
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class Zombie {
	
	
	/**
	 * <p>Takes an object and scans it for {@link Bite} annotations. If found, 
	 * the <b>singleton</b> for the annotated endpoint interface implementation will be 
	 * injected.</p>
	 * <br>
	 * <b>Usage:</b>
	 * <br><br>
	 * <ul>
	 * <li>
	 * <h5>Property Injection</h5>
	 * <pre>
	 * <code>@Bite
	 * TwitterEndpoint twitterEndpoint;
	 * {
	 * &nbsp; &nbsp; Zombie.infect(this);
	 * }
	 * </code>
	 * </pre>
	 * </li>
	 * <li>
	 * <h5>Setter Injection</h5>
	 * <pre>
	 * <code>
	 * private TwitterEndpoint twitterEndpoint;
	 * </code>
	 * <code>@Bite
	 * public void setTwitterEndpoint(TwitterEndpoint twitterEndpoint) {
	 * 
	 * &nbsp; &nbsp; this.twitterEndpoint = twitterEndpoint;
	 * }
	 * <br><br>
	 * <i>Instantiation:</i><br><br>
	 * TwitterService twitterService = Zombie.infect(new TwitterService());
	 * </code>
	 * </pre>
	 * </li>
	 * </ul>
	 * 
	 * @param injectee
	 * 			the object to which the endpoint must be injected
	 * <br><br>
	 * @since 1.1.1
	 */
	public static void infect(Object injectee) {
		
		Class<?> injecteeClass = injectee.getClass();
		Field[] fields = injecteeClass.getDeclaredFields();
		
		Class<?> endpointInterface = null;
		
		for (Field field : fields) {
			
			try {
				
				if(field.isAnnotationPresent(Bite.class)) {
					
					endpointInterface = field.getType();
					
					EndpointDirectory.INSTANCE.put(endpointInterface,
							   EndpointProxyFactory.newInstance().create(endpointInterface));
					
					Object proxyInstance = EndpointDirectory.INSTANCE.get(endpointInterface);
					
					try { //1.Simple Property Injection 
						
						field.set(injectee, proxyInstance);
					}
					catch (IllegalAccessException iae) { //2.Setter Injection 
						
						String fieldName = field.getName();
						String mutatorName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1); 
						
						try {
						
							Method mutator = injecteeClass.getDeclaredMethod(mutatorName, endpointInterface);
							mutator.invoke(injectee, proxyInstance);
						}
						catch (NoSuchMethodException nsme) { //3.Forced Property Injection
							
							field.setAccessible(true);
							field.set(injectee, proxyInstance);
						}
					}
				}
			} 
			catch (Exception e) {
				
				StringBuilder stringBuilder = new StringBuilder();
				
				stringBuilder.append("Failed to inject the endpoint proxy instance of type ");
				stringBuilder.append(endpointInterface.getName());
				stringBuilder.append(" on property ");
				stringBuilder.append(field.getName());
				stringBuilder.append(" at ");
				stringBuilder.append(injecteeClass.getName());
				stringBuilder.append(". ");
				
				Logger.getLogger(Zombie.class.getName()).log(Level.SEVERE, stringBuilder.toString(), e);
			}
		}
	}
	
	/**
	 * <p>Takes the {@link Class} structure of the injectee and uses the designated 
	 * parameterized constructor to create a new instance via <i>constructor injection</i>.</p>
	 * <br>
	 * <b>Usage:</b>
	 * <br><br>
	 * <ul>
	 * <li>
	 * <h5>Constructor Injection</h5>
	 * <br>
	 * <p>The implication is that endpoint <b>instantiation</b> is delegated to the {@link Zombie}.</p> 
	 * <pre>
	 * <code>@Bite
	 * public TwitterService(TwitterEndpoint twitterEndpoint) {
	 * 
	 * &nbsp; &nbsp; this.twitterEndpoint = twitterEndpoint;
	 * } 
	 * 
	 * <i>Instantiation:</i>
	 * 
	 * TwitterService twitterService = Zombie.infect(TwitterService.class);
	 * </code>
	 * </pre>
	 * </li>
	 * </ul>
	 * 
	 * @param injectee
	 * 			the {@link Class} structure of the object to be created with the constructor injection endpoint
	 * 
	 * @return a <b>new instance</b> of the injectee or {@code null} if constructor injection failed
	 * <br><br>
	 * @since 1.1.1
	 */
	public static <T extends Object> T infect(Class<T> injectee) {

		Class<?> endpointInterface = null;
		Constructor<?>[] constructors = injectee.getConstructors();

		for (Constructor<?> constructor : constructors) { //inject with the first annotated constructor

			if (constructor.isAnnotationPresent(Bite.class)) {

				Class<?>[] constructorParameters = constructor.getParameterTypes();

				if (constructorParameters.length > 0) {

					try {

						endpointInterface = constructorParameters[0];
						EndpointDirectory.INSTANCE.put(injectee, EndpointProxyFactory.newInstance().create(endpointInterface));

						return injectee.cast(constructor.newInstance(EndpointDirectory.INSTANCE.get(injectee)));
						
					} 
					catch (Exception e) {
						
						StringBuilder stringBuilder = new StringBuilder();
						
						stringBuilder.append("Failed to inject the endpoint proxy instance of type ");
						stringBuilder.append(endpointInterface.getName());
						stringBuilder.append(" on constructor ");
						stringBuilder.append(constructor.getName());
						stringBuilder.append(" at ");
						stringBuilder.append(injectee.getName());
						stringBuilder.append(". ");
						
						Logger.getLogger(Zombie.class.getName()).log(Level.SEVERE, stringBuilder.toString(), e);
					}
				}
			}
		}

		try { //constructor injection failed, attempt instantiation without injection
			
			T instance = injectee.newInstance();
			
			StringBuilder stringBuilder = new StringBuilder();
			
			stringBuilder.append(injectee.getName());
			stringBuilder.append(" instance created without constructor injection. ");

			Logger.getLogger(Zombie.class.getName()).log(Level.INFO, stringBuilder.toString());
			
			return instance; 
		}
		catch (Exception e) {

			StringBuilder stringBuilder = new StringBuilder();
			
			stringBuilder.append("Failed to create an instance of  ");
			stringBuilder.append(injectee.getName());
			stringBuilder.append(" with constructor injection. ");
			
			Logger.getLogger(Zombie.class.getName()).log(Level.SEVERE, stringBuilder.toString(), e);
			
			return null;
		}
	}
}
