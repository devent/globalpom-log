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
