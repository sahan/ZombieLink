package com.lonepulse.zombielink.test.service;

import com.lonepulse.zombielink.core.annotation.Bite;
import com.lonepulse.zombielink.core.inject.Zombie;
import com.lonepulse.zombielink.test.endpoint.ICNDBEndpoint;

/**
 * <p>Emulates a service which works with an {@link ICNDBEndpoint}.</p>
 * 
 * <p>Contains serveral {@link ICNDBEndpoint} members with various 
 * accessibility qualifiers to test the {@link Zombie}'s property injection 
 * capabilities.</p>
 * 
 * <p>Also contains a mutator and a parameterized contructor to test 
 * setter and contructor injection.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ICNDBService {
	
	
	/** <p>A public instance of {@link ICNDBEndpoint} */
	@Bite
	public ICNDBEndpoint publicICNDDBEndpoint;
	
	/** <p>A protected instance of {@link ICNDBEndpoint} */
	@Bite
	protected ICNDBEndpoint protectedICNDDBEndpoint;
	
	/** <p>A package private instance of {@link ICNDBEndpoint} */
	@Bite
	ICNDBEndpoint defaultICNDDBEndpoint;
	
	/** 
	 * <p>A private instance of {@link ICNDBEndpoint} which is 
	 * to be injected via it's mutator.
	 */
	@Bite
	private ICNDBEndpoint privateICNDDBEndpoint;
	
	/**  
	 * <p>A private instance of {@link ICNDBEndpoint} which is 
	 * to be injected via the constructor.
	 */
	@Bite
	private ICNDBEndpoint constructedICNDBEndpoint;
	
	/**  
	 * <p>A private instance of {@link ICNDBEndpoint} which is 
	 * to be injected by changing the <b>accessibility properties</b>.
	 */
	@Bite
	private ICNDBEndpoint forcedPrivateICNDBEndpoint;
	
	
	/**
	 * <p>Default constructor.
	 */
	public ICNDBService() {}
	
	/**
	 * <p>Facilitates constructor injection. 
	 */
	public ICNDBService(ICNDBEndpoint icndbEndpoint) {
		
		this.constructedICNDBEndpoint = icndbEndpoint;
	}
	
	/**
	 * <p>Accessor for privateICNDDBEndpoint.
	 *
	 * @return the privateICNDDBEndpoint
	 */
	public ICNDBEndpoint getPrivateICNDDBEndpoint() {
		return privateICNDDBEndpoint;
	}

	/**
	 * <p>Mutator for privateICNDDBEndpoint.
	 *
	 * @param privateICNDDBEndpoint 
	 *			the privateICNDDBEndpoint to set
	 */
	public void setPrivateICNDDBEndpoint(ICNDBEndpoint privateICNDDBEndpoint) {
		this.privateICNDDBEndpoint = privateICNDDBEndpoint;
	}

	/**
	 * <p>Accessor for publicICNDDBEndpoint.
	 *
	 * @return the publicICNDDBEndpoint
	 */
	public ICNDBEndpoint getPublicICNDDBEndpoint() {
		return publicICNDDBEndpoint;
	}

	/**
	 * <p>Accessor for protectedICNDDBEndpoint.
	 *
	 * @return the protectedICNDDBEndpoint
	 */
	public ICNDBEndpoint getProtectedICNDDBEndpoint() {
		return protectedICNDDBEndpoint;
	}

	/**
	 * <p>Accessor for defaultICNDDBEndpoint.
	 *
	 * @return the defaultICNDDBEndpoint
	 */
	public ICNDBEndpoint getDefaultICNDDBEndpoint() {
		return defaultICNDDBEndpoint;
	}

	/**
	 * <p>Accessor for constructedICNDBEndpoint.
	 *
	 * @return the constructedICNDBEndpoint
	 */
	public ICNDBEndpoint getConstructedICNDBEndpoint() {
		return constructedICNDBEndpoint;
	}

	/**
	 * <p>Accessor for forcedPrivateICNDBEndpoint.
	 *
	 * @return the forcedPrivateICNDBEndpoint
	 */
	public ICNDBEndpoint getForcedPrivateICNDBEndpoint() {
		return forcedPrivateICNDBEndpoint;
	}
}
