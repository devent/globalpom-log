/*
 * Copyright 2011-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.globalpom.exceptions;

import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Context for an exception.
 * <p>
 * The default message of the exception will output the context:
 * 
 * <pre>
 * Message of the exception, context:
 * Aaa := bbb
 * Ccc := ddd
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.15
 */
public class Context<T extends Exception> {

    private static final String ENTRY_SEP = " := ";

    private static final String LINE_SEP = System.getProperty("line.separator");

    private final Map<String, Object> context;

    private final T exception;

    /**
     * Sets the exception of the context.
     * 
     * @param exception
     *            the {@link Exception}.
     */
    public Context(T exception) {
        this.exception = exception;
        this.context = new HashMap<String, Object>();
    }

    /**
     * Adds the context with the specified name.
     * 
     * @param name
     *            the name of the context.
     * 
     * @param value
     *            the context value.
     * 
     * @return the context {@link Exception}.
     */
    public T addContext(String name, Object value) {
        context.put(name, value);
        return exception;
    }

    /**
     * Returns the context of the exception.
     * 
     * @return an unmodifiable {@link Map} with the context.
     */
    public Map<String, Object> getContext() {
        return unmodifiableMap(context);
    }

    /**
     * Returns the exception message and the context:
     * 
     * <pre>
     * Message of the exception, context:
     * Aaa := bbb
     * Ccc := ddd
     * </pre>
     */
    public String message(String message) {
        StringBuilder str = new StringBuilder();
        str.append(message);
        appendContext(context, str);
        return str.toString();
    }

    /**
     * Output the exception message and the context:
     * 
     * <pre>
     * Message of the exception, context:
     * Aaa := bbb
     * Ccc := ddd
     * </pre>
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(exception.getMessage());
        appendContext(context, str);
        return str.toString();
    }

    private void appendContext(Map<String, Object> context, StringBuilder str) {
        if (context.size() > 0) {
            str.append(", context:");
            str.append(LINE_SEP);
            for (Map.Entry<String, Object> entry : context.entrySet()) {
                str.append(entry.getKey()).append(ENTRY_SEP)
                        .append(entry.getValue());
            }
        }
    }

}
