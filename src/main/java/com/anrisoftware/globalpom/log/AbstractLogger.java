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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for a {@link Logger}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public abstract class AbstractLogger {

	protected transient Logger log;

	/**
	 * Needs to set the logger.
	 */
	public AbstractLogger() {
	}

	/**
	 * Creates a new {@link Logger} for the given context {@link Class}.
	 */
	protected AbstractLogger(@SuppressWarnings("rawtypes") Class contextClass) {
		this.log = LoggerFactory.getLogger(contextClass);
	}

	/**
	 * Log the specified exception.
	 * 
	 * @param message
	 *            the message of the exception.
	 * 
	 * @param ex
	 *            the {@link Exception}.
	 */
	protected void logException(String message, Exception ex) {
		if (log.isDebugEnabled()) {
			log.debug(ex.getMessage());
		} else if (log.isTraceEnabled()) {
			log.error("", ex);
		} else {
			log.error(message);
		}
	}

}
