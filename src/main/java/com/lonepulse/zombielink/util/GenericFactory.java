package com.lonepulse.zombielink.util;

/**
 * <p>This contract specifies the policy for a generic entity factory.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface GenericFactory<Input extends Object, Output extends Object> {

	/**
	 * <p>Takes an input as raw material or a key and builds 
	 * the output which is designated as this factory's product.
	 * 
	 * @param input
	 * 			the raw material or key from which the product 
	 * 			is created 
	 * 
	 * @param inputs
	 * 			further raw materials from which the product 
	 * 			is created
	 * 			
	 * @return a <b>new instance</b> of the the product
	 * 
	 * @since 1.1.0
	 */
	public abstract Output newInstance(Input input, Input... inputs);
	
	/**
	 * <p>Builds a new instance of the output product. 
	 * 			
	 * @return a <b>new instance</b> of the the product
	 * 
	 * @since 1.1.0
	 */
	public abstract Output newInstance();
}
