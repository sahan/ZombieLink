package com.lonepulse.zombielink.test.model;

import java.io.Serializable;

import com.lonepulse.zombielink.test.endpoint.ICNDBEndpoint;

/**
 * <p>This entity represents a <b>Chuck Norris Joke</b> retrieved from 
 * the ICNDB API via the {@link ICNDBEndpoint}.
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class NorrisJoke implements Serializable {

	
	private static final long serialVersionUID = -6967272216854601497L;

	private long id;
	private String joke;
	
	
	/**
	 * <p>Accessor for id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * <p>Mutator for id.
	 *
	 * @param id 
	 *			the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * <p>Accessor for joke.
	 *
	 * @return the joke
	 */
	public String getJoke() {
		return joke;
	}
	
	/**
	 * <p>Mutator for joke.
	 *
	 * @param joke 
	 *			the joke to set
	 */
	public void setJoke(String joke) {
		this.joke = joke;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NorrisJoke other = (NorrisJoke) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("NorrisJoke [id=");
		builder.append(id);
		builder.append(", joke=");
		builder.append(joke);
		builder.append("]");

		return builder.toString();
	}
}
