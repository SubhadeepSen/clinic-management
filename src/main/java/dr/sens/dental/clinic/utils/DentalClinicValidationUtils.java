package dr.sens.dental.clinic.utils;

import static java.util.Objects.isNull;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.Regex.*;
import static dr.sens.dental.clinic.constants.ClinicManagementErrors.Fields.*;
import static dr.sens.dental.clinic.constants.ClinicManagementErrors.Messages.*;

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
		checkIfEmpty(patientForm.getFullName(), FULL_NAME_ERROR, FULL_NAME_ERR_MSG);
		checkIfEmpty(patientForm.getAge(), AGE_ERROR, AGE_ERR_MSG);
		checkIfEmpty(patientForm.getGender(), GENDER_ERROR, GENDER_ERR_MSG);
		checkIfEmpty(patientForm.getPhoneNumber(), PHONE_NUMBER_ERROR, PHONE_NUMBER_ERR_MSG);
		checkIfEmpty(patientForm.getDateOfVisit(), DATE_OF_VISIT_ERROR, DATE_OF_VISIT_ERR_MSG);

		checkAllowedValueFormat(ALPHABETS_REGEX, patientForm.getFullName(), FULL_NAME_ERROR, FULL_NAME_REGEX_ERR_MSG);
		checkAllowedValueFormat(AGE_REGEX, patientForm.getAge(), AGE_ERROR, AGE_REGEX_ERR_MSG);
		checkAllowedValueFormat(ALPHABETS_REGEX, patientForm.getGender(), GENDER_ERROR, GENDER_REGEX_ERR_MSG);
		checkAllowedValueFormat(PHONE_NUMBER_REGEX, patientForm.getPhoneNumber(), PHONE_NUMBER_ERROR,
				PHONE_NUMBER_REGEX_ERR_MSG);

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
			checkIfEmpty(wda.getWorkDone(), String.format(WORK_DONE_ERROR, index), WORK_DONE_ERR_MSG);
			checkIfEmpty(wda.getAmount(), String.format(AMOUNT_ERROR, index),
					String.format(AMOUNT_ERR_MSG, wda.getWorkDone()));
			try {
				totalAmount = totalAmount + Double.parseDouble(wda.getAmount());
			} catch (NumberFormatException e) {
				throw new DentalClinicValidationException(String.format(AMOUNT_ERROR, index),
						String.format("Invalid amount %s for %s", wda.getAmount(), wda.getWorkDone()));
			}
		}

		try {
			formTotalAmount = Double.parseDouble(invoiceForm.getTotalAmount());
		} catch (NumberFormatException e) {
			throw new DentalClinicValidationException(TOTAL_AMOUNT_ERROR,
					String.format(TOTAL_AMOUNT_ERR_MSG, wda.getAmount()));
		}

		if (totalAmount != formTotalAmount) {
			throw new DentalClinicValidationException(TOTAL_AMOUNT_ERROR,
					String.format(TOTAL_AMOUNT_MISMATCH_ERR_MSG, totalAmount, formTotalAmount));
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
		if (StringUtils.isNotBlank(emailId) && !emailId.matches(EMAIL_REGEX)) {
			throw new DentalClinicValidationException(EMAIL_ID_ERROR, EMAIL_REGEX_ERR_MSG);
		}
	}

	private static void validateDateOfVisit(String dateOfVisit) {
		if (!LocalDate.parse(dateOfVisit).equals(LocalDate.now())) {
			throw new DentalClinicValidationException(DATE_OF_VISIT_ERROR,
					String.format(INVALID_DATE_OF_VISIT_ERR_MSG, LocalDate.now()));
		}
	}

	private static void validateNextAppointmentDate(String nextAppointmentDate) {
		if (StringUtils.isNotBlank(nextAppointmentDate)
				&& LocalDate.parse(nextAppointmentDate).compareTo(LocalDate.now()) < 0) {
			throw new DentalClinicValidationException(NEXT_APPOINTMENT_DATE_ERROR, NEXT_APPOINTMENT_DATE_ERR_MSG);
		}
	}
}
