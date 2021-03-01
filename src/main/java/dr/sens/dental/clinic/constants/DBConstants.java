package dr.sens.dental.clinic.constants;

public final class DBConstants {

	private DBConstants() {
	}

	public static class QueryAttributes {
		private QueryAttributes() {
		}

		public static final String NEXT_APPOINTMENT_DATE = "nextAppointmentDate";
		public static final String INVOICE_ID = "invoice.invoiceId";
		public static final String FULL_NAME = "personalInfo.fullName";
		public static final String CONSULTATIONS = "consultations";
		public static final String EMAIL_ID = "personalInfo.emailId";
		public static final String DATE_OF_VISIT = "dateOfVisit";
		public static final String PHONE_NUMBER = "personalInfo.phoneNumber";
		public static final String PATIENT_ID = "_id";
		public static final String PASSWORD = "password";
		public static final String USERNAME = "username";
	}
}
