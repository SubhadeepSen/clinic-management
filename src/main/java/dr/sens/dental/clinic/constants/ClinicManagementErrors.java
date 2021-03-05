package dr.sens.dental.clinic.constants;

public final class ClinicManagementErrors {

	private ClinicManagementErrors() {
	}

	public static class Fields {
		private Fields() {
		}

		public static final String NEXT_APPOINTMENT_DATE_ERROR = "nextAppointmentDateError";
		public static final String EMAIL_ID_ERROR = "emailIdError";
		public static final String TOTAL_AMOUNT_ERROR = "totalAmountError";
		public static final String WORK_DONE_ERROR = "workDoneError";
		public static final String AMOUNT_ERROR = "amountError";
		public static final String DATE_OF_VISIT_ERROR = "dateOfVisitError";
		public static final String PHONE_NUMBER_ERROR = "phoneNumberError";
		public static final String GENDER_ERROR = "genderError";
		public static final String AGE_ERROR = "ageError";
		public static final String FULL_NAME_ERROR = "fullNameError";

	}

	public static class Messages {
		private Messages() {
		}

		public static final String NULL_OBJECT_ERR_MSG = "Non null %s is required in order to transform";
		public static final String NULL_OBJECTS_ERR_MSG = "Non null %s and %s are required in order to transform";
		public static final String NEXT_APPOINTMENT_DATE_ERR_MSG = "Next appointment date cannot be past date";
		public static final String INVALID_DATE_OF_VISIT_ERR_MSG = "Date of visit can be today only, i.e. %s";
		public static final String EMAIL_REGEX_ERR_MSG = "Invalid emailId, emailId must be in abcdef@xyz.com format";
		public static final String TOTAL_AMOUNT_MISMATCH_ERR_MSG = "Mismatch found in total amount, calculated total amount is %s but display total amount is %s";
		public static final String TOTAL_AMOUNT_ERR_MSG = "Invalid total amount %s";
		public static final String AMOUNT_ERR_MSG = "amount is required for %s";
		public static final String WORK_DONE_ERR_MSG = "work done information is required";
		public static final String PHONE_NUMBER_REGEX_ERR_MSG = "Phone number should be numeric and 10 digits";
		public static final String GENDER_REGEX_ERR_MSG = "Gender can contain only alphabets";
		public static final String AGE_REGEX_ERR_MSG = "Age should be numeric and 2 digits";
		public static final String FULL_NAME_REGEX_ERR_MSG = "Full Name can contain only alphabets";
		public static final String DATE_OF_VISIT_ERR_MSG = "Date of visit is required";
		public static final String PHONE_NUMBER_ERR_MSG = "Phone number is required";
		public static final String GENDER_ERR_MSG = "Gender is required";
		public static final String AGE_ERR_MSG = "Age is required";
		public static final String FULL_NAME_ERR_MSG = "Full Name is required";
	}

}
