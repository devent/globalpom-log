/*
 * Copyright 2011-2023 Erwin Müller <erwin.mueller@anrisoftware.com>
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
			value.setTexts(texts);
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
	void setTexts(Texts texts) {
		this.text = texts.getResource(name).getText();
	}

	@Override
	public String toString() {
		return text;
	}
}
