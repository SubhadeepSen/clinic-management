package dr.sens.dental.clinic.constants;

public final class ClinicManagementConstants {

	private ClinicManagementConstants() {
	}

	public static final String INVOICE_ID = "invoiceId";
	public static final String PATIENT_ID = "patientId";

	public static class SessionAttributes {
		private SessionAttributes() {
		}

		public static final String USERNAME = "username";
		public static final String PATIENT_FORM = "patientForm";
		public static final String INVOICE_FORM = "invoiceForm";
		public static final String PHONE_NUMBER = "phoneNumber";
	}

	public static class ModelAttributes {
		private ModelAttributes() {
		}

		public static final String INVOICE_FORM = "invoiceForm";
		public static final String LOGIN_FORM = "loginForm";
		public static final String PATIENT_FORM = "patientForm";
		public static final String QUERY_CONTENT = "queryContent";
		public static final String PATIENT_INFO = "patientInfo";
		public static final String PATIENT_SEARCH_RESULTS = "patientSearchResults";
	}

	public static class PathMapping {
		private PathMapping() {
		}

		public static final String REDIRECT_TO_HOME = "redirect:/home";
		public static final String REDIRECT_TO_LOGIN = "redirect:/";
		public static final String REDIRECT_TO_PATIENT_FORM = "redirect:/patientForm";

	}

	public static class Views {
		private Views() {
		}

		public static final String LOGIN_PAGE = "login";
		public static final String HOME_PAGE = "home";
		public static final String PATIENT_FORM_PAGE = "patientForm";
		public static final String REVIEW_PATIENT_FORM_PAGE = "reviewPatientForm";
		public static final String INVOICE_FORM_PAGE = "invoiceForm";
		public static final String REVIEW_INVOICE_FORM_PAGE = "reviewInvoiceForm";
		public static final String CONFIRMATION_PAGE = "confirmation";
		public static final String PATIENT_DETAILS_PAGE = "patientDetails";
		public static final String SEARCH_RECORDS_PAGE = "searchRecords";
	}

}
