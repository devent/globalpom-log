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
