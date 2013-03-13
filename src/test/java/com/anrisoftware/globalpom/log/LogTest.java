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

import com.anrisoftware.globalpom.log.SerializedLoggerFactory.Logger;
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
		SerializedLoggerFactory.Logger log = serializedFactory
				.create(LogTest.class);
		log = (Logger) reserialize(log);
		log.logInfo();
	}

	@Test
	public void not_serializable_logger() {
		LoggerFactory.Logger log = loggerFactory.create(LogTest.class);
		log.logInfo();
	}

	private static Injector injector;

	private static SerializedLoggerFactory serializedFactory;

	private static LoggerFactory loggerFactory;

	@BeforeClass
	public static void createFactories() {
		injector = Guice.createInjector(new LoggerModule());
		serializedFactory = injector.getInstance(SerializedLoggerFactory.class);
		loggerFactory = injector.getInstance(LoggerFactory.class);
	}
}
