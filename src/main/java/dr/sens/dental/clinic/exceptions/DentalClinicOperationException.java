package dr.sens.dental.clinic.exceptions;

public class DentalClinicOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8481727917139238207L;

	public DentalClinicOperationException(String message) {
		super(message);
	}

	public DentalClinicOperationException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
