/*
 * Copyright 2011-2012 Erwin Müller <erwin.mueller@deventm.org>
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
 * The base class of an {@link Logger} that can be serialized.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public abstract class AbstractSerializedLogger implements Externalizable {

	protected transient Logger log;

	private Class<?> contextClass;

	/**
	 * For serialization.
	 */
	@Deprecated
	protected AbstractSerializedLogger() {
	}

	/**
	 * Creates a new {@link Logger} for the given context {@link Class}.
	 */
	protected AbstractSerializedLogger(Class<?> contextClass) {
		this.contextClass = contextClass;
		this.log = LoggerFactory.getLogger(contextClass);
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
}
