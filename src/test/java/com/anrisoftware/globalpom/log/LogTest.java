/*
 * Copyright 2011-2025 Erwin Müller <erwin.mueller@anrisoftware.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anrisoftware.globalpom.log;

import static com.anrisoftware.globalpom.utils.TestUtils.reserialize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        Logger logB = (Logger) reserialize(log);
        logB.logInfo();
        assertEquals(log.getLog().getName(), logB.getLog().getName());
    }

    @Test
    public void log_info() {
        Logger log = injector.getInstance(Logger.class);
        log.logInfo();
        assertEquals("com.anrisoftware.globalpom.log.Logger", log.getLog().getName());
    }

    @Test
    public void log_exception() {
        assertThrows(NullPointerException.class, () -> {
            Logger log = injector.getInstance(Logger.class);
            log.logException();
        });
    }

    @Test
    public void log_context_exception() {
        assertThrows(RuntimeException.class, () -> {
            Logger log = injector.getInstance(Logger.class);
            String s = "cause";
            log.logContextException(new NullPointerException(s), "foo");
        });
    }

    @Test
    public void log_more_context_exception() {
        assertThrows(RuntimeException.class, () -> {
            Logger log = injector.getInstance(Logger.class);
            String s = "cause";
            log.logMoreContextException(new NullPointerException(s), "foo", "bar");
        });
    }

    private static Injector injector;

    @BeforeAll
    public static void createInjector() {
        injector = Guice.createInjector();
    }
}
