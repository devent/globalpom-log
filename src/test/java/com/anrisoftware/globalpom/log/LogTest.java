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

import static com.anrisoftware.globalpom.utils.TestUtils.reserialize;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Test the abstract logger factory.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.4
 */
public class LogTest {

	@Test
	public void serialize_and_deserialize_logger() {
		Logger log = injector.getInstance(Logger.class);
		log = (Logger) reserialize(log);
		log.logInfo();
	}

	@Test
	public void log_info() {
		Logger log = injector.getInstance(Logger.class);
		log.logInfo();
	}

	@Test(expected = NullPointerException.class)
	public void log_exception() {
		Logger log = injector.getInstance(Logger.class);
		log.logException();
	}

	private static Injector injector;

	@BeforeClass
	public static void createInjector() {
		injector = Guice.createInjector();
	}
}
