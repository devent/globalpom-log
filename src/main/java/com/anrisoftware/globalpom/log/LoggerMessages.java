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

/**
 * Logger messages template.
 * <p>
 * <h2>Notes</h2>
 * <p>
 * Needs to be at least package private so static imports of enumerations can be
 * used.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.14
 */
enum LoggerMessages {

	message("message");

	/**
	 * Retrieves the text resources for the logging messages.
	 * 
	 * @param texts
	 *            the texts {@link Texts} resources.
	 */
	public static void retrieveResources(Texts texts) {
		for (LoggerMessages value : values()) {
			value.setText(texts);
		}
	}

	private String name;

	private String text;

	private LoggerMessages(String name) {
		this.name = name;
	}

	/**
	 * Retrieve the text resource for the logging message.
	 * 
	 * @param texts
	 *            the texts {@link Texts} resources.
	 */
	public void setText(Texts texts) {
		this.text = texts.getResource(name).getText();
	}

	@Override
	public String toString() {
		return text;
	}
}
