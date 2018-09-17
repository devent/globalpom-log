package com.anrisoftware.globalpom.log;

/*-
 * #%L
 * Global POM Logging
 * %%
 * Copyright (C) 2011 - 2018 Advanced Natural Research Institute
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

    @Test(expected = RuntimeException.class)
    public void log_context_exception() {
        Logger log = injector.getInstance(Logger.class);
        String s = "cause";
        log.logContextException(new NullPointerException(s), "foo");
    }

    @Test(expected = RuntimeException.class)
    public void log_more_context_exception() {
        Logger log = injector.getInstance(Logger.class);
        String s = "cause";
        log.logMoreContextException(new NullPointerException(s), "foo", "bar");
    }

	private static Injector injector;

	@BeforeClass
	public static void createInjector() {
		injector = Guice.createInjector();
	}
}
