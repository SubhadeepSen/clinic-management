package dr.sens.dental.clinic.utils;

import static dr.sens.dental.clinic.constants.ClinicManagementErrors.Messages.NULL_OBJECTS_ERR_MSG;
import static dr.sens.dental.clinic.constants.ClinicManagementErrors.Messages.NULL_OBJECT_ERR_MSG;
import static java.util.Objects.isNull;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;

import dr.sens.dental.clinic.documents.Consultation;
import dr.sens.dental.clinic.documents.Gender;
import dr.sens.dental.clinic.documents.Invoice;
import dr.sens.dental.clinic.documents.PersonalInfo;
import dr.sens.dental.clinic.exceptions.DentalClinicOperationException;
import dr.sens.dental.clinic.models.InvoiceForm;
import dr.sens.dental.clinic.models.PatientForm;

public class DentalClinicTransformerUtils {

	private static final String INVOICE_FORM = "invoice form";
	private static final String PATIENT_FORM = "patient form";

	private DentalClinicTransformerUtils() {
	}

	public static void removeEmptyItems(PatientForm patientForm) {
		if (isNull(patientForm)) {
			throw new DentalClinicOperationException(String.format(NULL_OBJECT_ERR_MSG, PATIENT_FORM));
		}
		patientForm.getAdvices().removeIf(StringUtils::isBlank);
		patientForm.getChiefComplaints().removeIf(StringUtils::isBlank);
		patientForm.getInvestigations().removeIf(StringUtils::isBlank);
		patientForm.getMedicalHistories().removeIf(StringUtils::isBlank);
		patientForm.getMedicines().removeIf(StringUtils::isBlank);
		patientForm.getOnExaminations().removeIf(StringUtils::isBlank);
		patientForm.getWorkDones().removeIf(StringUtils::isBlank);
	}

	public static void removeEmptyItems(InvoiceForm invoiceForm) {
		if (isNull(invoiceForm)) {
			throw new DentalClinicOperationException(String.format(NULL_OBJECT_ERR_MSG, INVOICE_FORM));
		}
		invoiceForm.getWorkDoneAmounts()
				.removeIf(wda -> StringUtils.isBlank(wda.getWorkDone()) && StringUtils.isBlank(wda.getAmount()));
	}

	public static PersonalInfo transformToPersonalInfo(PatientForm patientForm, InvoiceForm invoiceForm) {
		if (isNull(patientForm) || isNull(invoiceForm)) {
			throw new DentalClinicOperationException(String.format(NULL_OBJECTS_ERR_MSG, PATIENT_FORM, INVOICE_FORM));
		}
		PersonalInfo personalInfo = new PersonalInfo();
		personalInfo.setAddress(patientForm.getAddress());
		personalInfo.setAge(Integer.valueOf(patientForm.getAge()));
		personalInfo.setEmailId(patientForm.getEmailId());
		personalInfo.setFullName(patientForm.getFullName());
		personalInfo.setOccupation(patientForm.getOccupation());
		personalInfo.setPhoneNumber(patientForm.getPhoneNumber());
		personalInfo.setGender(Gender.fromValue(patientForm.getGender()));
		return personalInfo;
	}

	public static Consultation transformToConsultation(PatientForm patientForm, InvoiceForm invoiceForm) {
		if (isNull(patientForm) || isNull(invoiceForm)) {
			throw new DentalClinicOperationException(String.format(NULL_OBJECTS_ERR_MSG, PATIENT_FORM, INVOICE_FORM));
		}
		Consultation consultation = new Consultation();
		consultation.setAdvices(patientForm.getAdvices());
		consultation.setChiefComplaints(patientForm.getChiefComplaints());
		consultation.setDateOfVisit(LocalDate.parse(patientForm.getDateOfVisit()));
		consultation.setInvestigations(patientForm.getInvestigations());
		consultation.setInvoice(buildInvoice(invoiceForm));
		consultation.setMedicalHistories(patientForm.getMedicalHistories());
		consultation.setMedicines(patientForm.getMedicines());
		if (StringUtils.isNotBlank(patientForm.getNextAppointmentDate())) {
			consultation.setNextAppointmentDate(LocalDate.parse(patientForm.getNextAppointmentDate()));
		}
		consultation.setOnExaminations(patientForm.getOnExaminations());
		consultation.setWorkDones(patientForm.getWorkDones());
		return consultation;
	}

	private static Invoice buildInvoice(InvoiceForm invoiceForm) {
		Invoice invoice = new Invoice();
		invoice.setWorkDoneAmounts(invoiceForm.getWorkDoneAmounts());
		invoice.setTotalAmount(invoiceForm.getTotalAmount());
		return invoice;
	}
}
