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

package com.anrisoftware.globalpom.log;

/**
 * Contains log messages.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.10
 */
class Logger extends AbstractLogger {

    private static final String CONTEXT_ERROR_MESSAGE = "Context error {}";

    private static final String CONTEXT_ERROR = "Context error";

    private static final String NPE_ERROR_MESSAGE = "Some NPE error in %s.";

    private static final String NPE_ERROR = "NPE error";

    private static final String MORE_CONTEXT_ERROR_MESSAGE = "Context error a {}, b {}.";

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
        throw logException(new NullPointerException(NPE_ERROR), NPE_ERROR_MESSAGE, "Foo");
    }

    void logContextException(Exception e, Object value) {
        throw logException(new RuntimeException(CONTEXT_ERROR, e), CONTEXT_ERROR_MESSAGE, value);
    }

    void logMoreContextException(Exception e, Object valueA, Object valueB) {
        throw logException(new RuntimeException(CONTEXT_ERROR, e), MORE_CONTEXT_ERROR_MESSAGE, valueA, valueB);
    }
}
