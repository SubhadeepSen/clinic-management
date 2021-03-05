package dr.sens.dental.clinic.utils;

import static java.util.Objects.isNull;

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
		if (isNull(patientForm)) {
			throw new DentalClinicValidationException("Non null patient form is required");
		}
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
		if (isNull(invoiceForm)) {
			throw new DentalClinicValidationException("Non null invoice form is required");
		}
		List<WorkDoneAmount> workDoneAmounts = invoiceForm.getWorkDoneAmounts();
		WorkDoneAmount wda = null;
		double totalAmount = 0.0;
		double formTotalAmount = 0.0;

		for (int index = 0; index < workDoneAmounts.size(); index++) {
			wda = workDoneAmounts.get(index);
			checkIfEmpty(wda.getWorkDone(), String.format("workDoneError", index), "work done information is required");
			checkIfEmpty(wda.getAmount(), String.format("amountError", index),
					String.format("amount is required for %s", wda.getWorkDone()));
			try {
				totalAmount = totalAmount + Double.parseDouble(wda.getAmount());
			} catch (NumberFormatException e) {
				throw new DentalClinicValidationException(String.format("amountError", index),
						String.format("Invalid amount %s for %s", wda.getAmount(), wda.getWorkDone()));
			}
		}

		try {
			formTotalAmount = Double.parseDouble(invoiceForm.getTotalAmount());
		} catch (NumberFormatException e) {
			throw new DentalClinicValidationException("totalAmountError",
					String.format("Invalid total amount %s", wda.getAmount()));
		}

		if (totalAmount != formTotalAmount) {
			throw new DentalClinicValidationException("totalAmountError", String.format(
					"Mismatch found in total amount, calculated total amount is %s but display total amount is %s",
					totalAmount, formTotalAmount));
		}
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
