package dr.sens.dental.clinic.utils;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dr.sens.dental.clinic.exceptions.DentalClinicValidationException;
import dr.sens.dental.clinic.models.InvoiceForm;
import dr.sens.dental.clinic.models.PatientForm;
import dr.sens.dental.clinic.models.WorkDoneAmount;

public class DentalClinicValidationUtils {

	private DentalClinicValidationUtils() {
	}

	public static void validatePatientForm(PatientForm patientForm) {
		checkIfEmpty(patientForm.getFullName(), "fullNameError", "Full Name is required");
		checkIfEmpty(patientForm.getAge(), "ageError", "Age is required");
		checkIfEmpty(patientForm.getGender(), "genderError", "Gender is required");
		checkIfEmpty(patientForm.getPhoneNumber(), "phoneNumberError", "Phone number is required");
		checkIfEmpty(patientForm.getDateOfVisit(), "dateOfVisitError", "Date of visit is required");

		checkAllowedValueFormat("^[a-z A-Z]+", patientForm.getFullName(), "fullNameError",
				"Full Name can contain only alphabets");
		checkAllowedValueFormat("^\\d{2}$", patientForm.getAge(), "ageError", "Age should be numeric and 2 digits");
		checkAllowedValueFormat("^[a-z A-Z]+", patientForm.getGender(), "genderError",
				"Gender can contain only alphabets");
		checkAllowedValueFormat("^\\d{10}$", patientForm.getPhoneNumber(), "phoneNumberError",
				"Phone number should be numeric and 10 digits");

		validateDateOfVisit(patientForm.getDateOfVisit());
		validateNextAppointmentDate(patientForm.getNextAppointmentDate());
		validateEmailId(patientForm.getEmailId());
	}

	public static void validateInvoiceForm(InvoiceForm invoiceForm) {

	}
	
	public static void validateInvoiceTotalAmount(List<WorkDoneAmount> workDoneAmounts, String totalAmount) {
		
	}

	private static void checkIfEmpty(String value, String errorField, String errorMessage) {
		if (StringUtils.isBlank(value)) {
			throw new DentalClinicValidationException(errorField, errorMessage);
		}
	}

	private static void checkAllowedValueFormat(String regex, String value, String errorField, String errorMessage) {
		if (!value.matches(regex)) {
			throw new DentalClinicValidationException(errorField, errorMessage);
		}
	}

	private static void validateEmailId(String emailId) {
		if (StringUtils.isNotBlank(emailId) && !emailId.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
			throw new DentalClinicValidationException("emailIdError",
					"Invalid emailId, emailId must be in abcdef@xyz.com format");
		}
	}

	private static void validateDateOfVisit(String dateOfVisit) {
		if (!LocalDate.parse(dateOfVisit).equals(LocalDate.now())) {
			throw new DentalClinicValidationException("dateOfVisitError",
					"Date of visit can be today only, i.e. " + LocalDate.now());
		}
	}

	private static void validateNextAppointmentDate(String nextAppointmentDate) {
		if (StringUtils.isNotBlank(nextAppointmentDate)
				&& LocalDate.parse(nextAppointmentDate).compareTo(LocalDate.now()) < 0) {
			throw new DentalClinicValidationException("nextAppointmentDateError",
					"Next appointment date cannot be past date");
		}
	}
}
