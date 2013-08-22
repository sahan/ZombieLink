package com.lonepulse.zombielink.core.processor;

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

import com.lonepulse.zombielink.core.ZombieLinkRuntimeException;

/**
 * <p>This root {@link RuntimeException} is thrown due to an error in creating or executing an 
 * implementation of {@link AbstractProcessorChain}.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ProcessorChainException extends ZombieLinkRuntimeException {
	
	
	private static final long serialVersionUID = -6017337334495169769L;
	

	/**
	 * See {@link ZombieLinkRuntimeException#ZombieLinkRuntimeException()}.
	 */
	public ProcessorChainException() {}

	/**
	 * See {@link ZombieLinkRuntimeException#ZombieLinkRuntimeException(String)}.
	 */
	public ProcessorChainException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link ZombieLinkRuntimeException#ZombieLinkRuntimeException(Throwable)}.
	 */
	public ProcessorChainException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link ZombieLinkRuntimeException#ZombieLinkRuntimeException(String, Throwable)}.
	 */
	public ProcessorChainException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}