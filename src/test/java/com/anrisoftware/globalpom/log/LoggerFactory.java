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

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new {@link Logger}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.4
 */
interface LoggerFactory {

	/**
	 * create a new {@link Logger} for the given context {@link Class}.
	 */
	Logger create(@Assisted Class<?> clazz);

	/**
	 * Contains log messages.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 1.4
	 */
	class Logger extends AbstractLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		void logInfo() {
			log.info("text");
		}

	}
}
