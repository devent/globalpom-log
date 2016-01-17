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
 * Dummy texts resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.14
 */
public class Texts {

	/**
	 * Dummy text resource.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 1.14
	 */
	public static class TextResource {

		public String getText() {
			return null;
		}

	}

	public TextResource getResource(String name) {
		return new TextResource();
	}

}
