package dr.sens.dental.clinic.exceptions;

public class UnsupportedGenderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2003974477955424101L;

	public UnsupportedGenderException(String message) {
		super(message);
	}

	public UnsupportedGenderException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
