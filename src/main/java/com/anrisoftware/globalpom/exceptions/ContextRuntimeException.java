/*
 * Copyright 2011-2016 Erwin Müller <erwin.mueller@deventm.org>
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

import java.util.Map;

/**
 * {@link RuntimeException} with a context.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.15
 */
@SuppressWarnings("serial")
public class ContextRuntimeException extends RuntimeException {

    private final Context<ContextRuntimeException> context;

    /**
     * @see RuntimeException#RuntimeException(String, Throwable)
     */
    public ContextRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.context = new Context<ContextRuntimeException>(this);
    }

    /**
     * @see RuntimeException#RuntimeException(String)
     */
    public ContextRuntimeException(String message) {
        super(message);
        this.context = new Context<ContextRuntimeException>(this);
    }

    /**
     * @see RuntimeException#RuntimeException(String, Throwable)
     */
    public ContextRuntimeException(Object message, Throwable cause) {
        super(message.toString(), cause);
        this.context = new Context<ContextRuntimeException>(this);
    }

    /**
     * @see RuntimeException#RuntimeException(String)
     */
    public ContextRuntimeException(Object message) {
        super(message.toString());
        this.context = new Context<ContextRuntimeException>(this);
    }

    /**
     * @see Context#addContext(String, Object)
     */
    public ContextRuntimeException add(String name, Object value) {
        context.addContext(name, value);
        return this;
    }

    /**
     * @see Context#addContext(String, Object)
     */
    public ContextRuntimeException add(Object name, Object value) {
        context.addContext(name.toString(), value);
        return this;
    }

    /**
     * @see Context#getContext()
     */
    public Map<String, Object> getContext() {
        return context.getContext();
    }

    @Override
    public String getMessage() {
        return context.message(super.getMessage());
    }

    @Override
    public String getLocalizedMessage() {
        return context.localizedMessage(super.getMessage());
    }

    @Override
    public String toString() {
        return context.toString();
    }
}
