/*
 * Copyright 2011-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of globalpom-log.
 *
 * globalpom-log is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * globalpom-log is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with globalpom-log. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.globalpom.log;

import static java.lang.String.format;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for a {@link Logger}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public abstract class AbstractLogger implements Externalizable {

	protected transient Logger log;

	private Class<?> contextClass;

	/**
	 * For serialization.
	 */
	public AbstractLogger() {
	}

	/**
	 * Creates a new {@link Logger} for the given context {@link Class}.
	 */
	protected AbstractLogger(@SuppressWarnings("rawtypes") Class contextClass) {
		this.log = LoggerFactory.getLogger(contextClass);
		this.contextClass = contextClass;
	}

	/**
	 * Log the specified exception.
	 * 
	 * @param ex
	 *            the {@link Exception}.
	 * 
	 * @param message
	 *            the message of the exception.
	 * 
	 * @param args
	 *            optional arguments for the message.
	 * 
	 * @param <T>
	 *            the exception type.
	 * 
	 * @return the {@link Exception}.
	 * 
	 * @since 1.10
	 */
	protected <T extends Exception> T logException(T ex, String message,
			Object... args) {
		if (log.isTraceEnabled()) {
			log.error("", ex);
		} else if (log.isDebugEnabled()) {
			log.debug(ex.getMessage());
		} else {
			log.error(format(message, args));
		}
		return ex;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(contextClass);
		out.flush();
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		contextClass = (Class<?>) in.readObject();
		log = LoggerFactory.getLogger(contextClass);
	}

}
