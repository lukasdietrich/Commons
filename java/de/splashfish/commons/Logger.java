package de.splashfish.commons;

import java.io.PrintStream;

public class Logger {

	private static Logger instance;
	/**
	 * get the global instance of {@link Logger}
	 * @return
	 */
	public static Logger get() {
		if(instance == null)
			instance = new Logger();
		return instance;
	}
	
	private PrintStream out;
	private PrintStream err;
	
	private Logger() {
		this.out = System.out;
		this.err = System.err;
	}
	
	/**
	 * logs a simple message.
	 * source is used to identify, which class logged what.
	 * 
	 * basic usage would look like: <code>{@link Logger}.get().log("Hi!", this);</code>
	 * 
	 * @param message
	 * @param source
	 */
	public void log(String message, Object source) {
		log(out, message, source);
	}
	
	/**
	 * logs a simple error-message, whereas the {@link Exception}'s localized message is the message.
	 * source is used to identify, which class logged what.
	 * 
	 * basic usage would look like: <code>{@link Logger}.get().log(e, this);</code>
	 * 
	 * <i>this is equal to: <code>{@link Logger}.get().err(e.getClass().getSimpleName() +": "+ e.getLocalizedMessage(), this);</code></i>
	 * 
	 * @param e
	 * @param source
	 */
	public void err(Exception e, Object source) {
		log(err, e.getClass().getSimpleName() +": "+ e.getLocalizedMessage(), source);
	}
	
	/**
	 * logs a simple error-message.
	 * source is used to identify, which class logged what.
	 * 
	 * basic usage would look like: <code>{@link Logger}.get().err("Hi!", this);</code>
	 * 
	 * @param e
	 * @param source
	 */
	public void err(String message, Object source) {
		log(err, message, source);
	}
	
	private void log(PrintStream stream, String message, Object source) {
		if(source != null) {
			message = "["+ source.getClass().getSimpleName() +"] " + message;
		} else {
			message = " " + message;
		}
		
		stream.println(DateTime.format("[%d.%M.%y %h:%m:%s.%n]") + message);
	}
	
}
