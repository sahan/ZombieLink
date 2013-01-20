package com.lonepulse.zombielink.test.model;

import java.io.Serializable;
import java.util.List;

import com.lonepulse.zombielink.test.endpoint.ICNDBEndpoint;

/**
 * <p>This entity represents a <b>response</b> which is recieved from the 
 * ICNDB API via the {@link ICNDBEndpoint}.
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ICNDBResponse implements Serializable {


	private static final long serialVersionUID = -1575987881009903297L;

	/**
	 * <p>An indication as to whether the request was executed successfully.</p>
	 * <p>A value of {@code success} implies a successful execution.</p>
	 */
	private String type;
	
	/**
	 * <p>The {@link NorrisJoke} which was requested. 
	 */
	private NorrisJoke value;
	
	/**
	 * <p>The categories, such as <b>nerdy</b> and <b>explicit</b>, to which 
	 * this {@link NorrisJoke} belongs.
	 */
	private List<String> categories;


	/**
	 * <p>Accessor for type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * <p>Mutator for type.
	 *
	 * @param type 
	 *			the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * <p>Accessor for value.
	 *
	 * @return the value
	 */
	public NorrisJoke getValue() {
		return value;
	}

	/**
	 * <p>Mutator for value.
	 *
	 * @param value 
	 *			the value to set
	 */
	public void setValue(NorrisJoke value) {
		this.value = value;
	}

	/**
	 * <p>Accessor for categories.
	 *
	 * @return the categories
	 */
	public List<String> getCategories() {
		return categories;
	}

	/**
	 * <p>Mutator for categories.
	 *
	 * @param categories 
	 *			the categories to set
	 */
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("ICNDBResponse [type=");
		builder.append(type);
		builder.append(", value=");
		builder.append(value);
		builder.append(", categories=");
		builder.append(categories);
		builder.append("]");
		
		return builder.toString();
	}
}
