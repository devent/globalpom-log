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


/**
 * Contains log messages.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.10
 */
class Logger extends AbstractLogger {

	private static final String NPE_ERROR_MESSAGE = "Some NPE error in %s.";

	private static final String NPE_ERROR = "NPE error";

	/**
	 * For serialization.
	 */
	public Logger() {
		super(Logger.class);
	}

	void logInfo() {
		log.info("text");
	}

	void logException() {
		throw logException(new NullPointerException(NPE_ERROR),
				NPE_ERROR_MESSAGE, "Foo");
	}
}