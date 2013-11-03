package com.lonepulse.zombielink.annotation;


/**
 * <p>Allows a basic name and value pair to be provided as annotated metadata.</p>
 * 
 * @version 1.1.0
 * <br><br> 
 * @since 1.2.4
 * <br><br> 
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public @interface Param { 
	
	/**
	 * <p>The <b>name</b> of the name and value pair.</p>  
	 * 
	 * @return the name of the name and value pair
	 * <br><br>
	 * @since 1.2.4
	 */
	public String name();
	
	/**
	 * <p>The <b>value</b> of the name and value pair.</p>  
	 * 
	 * @return the value of the name and value pair
	 * <br><br>
	 * @since 1.2.4
	 */
	public String value();
}