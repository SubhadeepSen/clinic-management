package dr.sens.dental.clinic.exceptions;

public class InvalidInvoiceAmountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3964847984448997070L;

	public InvalidInvoiceAmountException(String message) {
		super(message);
	}

	public InvalidInvoiceAmountException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
