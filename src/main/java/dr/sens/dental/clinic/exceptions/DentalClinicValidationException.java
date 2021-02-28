package dr.sens.dental.clinic.exceptions;

public class DentalClinicValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8643628297174507794L;

	private String errorField;

	public DentalClinicValidationException(String message) {
		super(message);
	}

	public DentalClinicValidationException(String errorField, String message) {
		super(message);
		this.errorField = errorField;
	}

	public DentalClinicValidationException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public String getErrorField() {
		return errorField;
	}

}
