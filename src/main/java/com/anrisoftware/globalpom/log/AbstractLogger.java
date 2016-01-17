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
package com.anrisoftware.globalpom.log;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for a {@link Logger}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public abstract class AbstractLogger implements Externalizable {

    protected transient Logger log;

    private Class<?> contextClass;

    /**
     * For serialization.
     */
    public AbstractLogger() {
    }

    /**
     * Creates a new {@link Logger} for the given context {@link Class}.
     */
    protected AbstractLogger(@SuppressWarnings("rawtypes") Class contextClass) {
        this.log = LoggerFactory.getLogger(contextClass);
        this.contextClass = contextClass;
    }

    /**
     * Log the specified exception.
     *
     * @param ex
     *            the {@link Throwable}.
     *
     * @param message
     *            the message of the exception.
     *
     * @param args
     *            optional arguments for the message.
     *
     * @param <T>
     *            the exception type.
     *
     * @return the {@link Throwable}.
     *
     * @since 1.12
     */
    protected <T extends Throwable> T logException(T ex, String message,
            Object... args) {
        if (log.isDebugEnabled()) {
            log.error(ex.getMessage(), ex);
        } else {
            log.error(message, args);
        }
        return ex;
    }

    /**
     * @see #logException(Throwable, String, Object...)
     *
     * @since 1.13.1
     */
    protected <T extends Throwable> T logException(T ex, Object message,
            Object... args) {
        return logException(ex, message.toString(), args);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(contextClass);
        out.flush();
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        contextClass = (Class<?>) in.readObject();
        log = LoggerFactory.getLogger(contextClass);
    }

    /**
     * Returns the parent logger.
     *
     * @return the {@link Logger}.
     *
     * @since 1.19
     */
    public Logger getLog() {
        return log;
    }

    /**
     * @see Logger#isTraceEnabled()
     *
     * @since 1.14
     */
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    /**
     * @see Logger#trace(String, Object...)
     *
     * @since 1.14
     */
    public void trace(Object format, Object... arguments) {
        if (log.isTraceEnabled()) {
            log.trace(format.toString(), arguments);
        }
    }

    /**
     * @see Logger#isDebugEnabled()
     *
     * @since 1.14
     */
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    /**
     * @see Logger#debug(String, Object...)
     *
     * @since 1.14
     */
    public void debug(Object format, Object... arguments) {
        if (log.isDebugEnabled()) {
            log.debug(format.toString(), arguments);
        }
    }

    /**
     * @see Logger#isInfoEnabled()
     *
     * @since 1.14
     */
    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    /**
     * @see Logger#info(String, Object...)
     *
     * @since 1.14
     */
    public void info(Object format, Object... arguments) {
        if (log.isInfoEnabled()) {
            log.info(format.toString(), arguments);
        }
    }

    /**
     * @see Logger#isWarnEnabled()
     *
     * @since 1.14
     */
    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    /**
     * @see Logger#warn(String, Object...)
     *
     * @since 1.14
     */
    public void warn(Object format, Object... arguments) {
        if (log.isWarnEnabled()) {
            log.warn(format.toString(), arguments);
        }
    }

    /**
     * @see Logger#isErrorEnabled()
     *
     * @since 1.14
     */
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    /**
     * @see Logger#error(String, Object...)
     *
     * @since 1.14
     */
    public void error(Object format, Object... arguments) {
        if (log.isErrorEnabled()) {
            log.error(format.toString(), arguments);
        }
    }

}
