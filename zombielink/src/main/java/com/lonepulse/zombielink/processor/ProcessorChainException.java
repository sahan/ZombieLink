package com.lonepulse.zombielink.processor;

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

import com.lonepulse.zombielink.ZombieLinkRuntimeException;

/**
 * <p>This root {@link RuntimeException} is thrown due to an error in creating or executing an 
 * implementation of {@link AbstractProcessorChain}.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.3.0
 * <br><br>
 * @author <a href="http://sahan.me">Lahiru Sahan Jayasinghe</a>
 */
class ProcessorChainException extends ZombieLinkRuntimeException {
	
	
	private static final long serialVersionUID = -6017337334495169769L;
	

	/**
	 * See {@link ZombieLinkRuntimeException#ZombieLinkRuntimeException()}.
	 * <br><br>
	 * @since 1.3.0
	 */
	public ProcessorChainException() {}

	/**
	 * See {@link ZombieLinkRuntimeException#ZombieLinkRuntimeException(String)}.
	 * <br><br>
	 * @since 1.3.0
	 */
	public ProcessorChainException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link ZombieLinkRuntimeException#ZombieLinkRuntimeException(Throwable)}.
	 * <br><br>
	 * @since 1.3.0
	 */
	public ProcessorChainException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link ZombieLinkRuntimeException#ZombieLinkRuntimeException(String, Throwable)}.
	 * <br><br>
	 * @since 1.3.0
	 */
	public ProcessorChainException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
