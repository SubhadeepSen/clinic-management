package dr.sens.dental.clinic.servcies;

import static dr.sens.dental.clinic.constants.ClinicManagementConstants.INVOICE_ID;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.PATIENT_ID;
import static dr.sens.dental.clinic.utils.DentalClinicTransformerUtils.transformToConsultation;
import static dr.sens.dental.clinic.utils.DentalClinicTransformerUtils.transformToPersonalInfo;
import static dr.sens.dental.clinic.utils.DentalClinicValidationUtils.validateInvoiceTotalAmount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dr.sens.dental.clinic.documents.Consultation;
import dr.sens.dental.clinic.documents.PatientInfo;
import dr.sens.dental.clinic.documents.PersonalInfo;
import dr.sens.dental.clinic.models.InvoiceForm;
import dr.sens.dental.clinic.models.PatientForm;
import dr.sens.dental.clinic.models.QueryContent;
import dr.sens.dental.clinic.repository.PatientInfoRepository;

@Service
public class PatientInfoService {

	@Autowired
	private PatientInfoRepository patientInfoRepository;

	public Map<String, String> insertOrUpdate(PatientForm patientForm, InvoiceForm invoiceForm) {
		validateInvoiceTotalAmount(invoiceForm.getWorkDoneAmounts(), invoiceForm.getTotalAmount());

		PersonalInfo personalInfo = transformToPersonalInfo(patientForm, invoiceForm);
		Consultation consultation = transformToConsultation(patientForm, invoiceForm);
		String patientId = patientInfoRepository.getPatientIdIfExits(personalInfo.getPhoneNumber());
		Map<String, String> responseIds = new HashMap<>();

		if (StringUtils.isBlank(patientId)) {
			PatientInfo newPatientInfo = patientInfoRepository
					.insertNewPatientInfo(buildNewPatientInfo(personalInfo, consultation));
			patientId = newPatientInfo.getPatientId();
		} else {
			patientInfoRepository.updateExistingPatientInfoConsultations(patientId, consultation);
		}

		responseIds.put(PATIENT_ID, patientId);
		responseIds.put(INVOICE_ID, consultation.getInvoice().getInvoiceId());
		return responseIds;
	}

	public PatientInfo getPatientInfoByPatientId(String patientId) {
		return patientInfoRepository.findByPatientIdAndOrPhoneNumber(patientId, null);
	}

	public PatientInfo getPatientInfoByPhoneNumber(String phoneNumber) {
		return patientInfoRepository.findByPatientIdAndOrPhoneNumber(null, phoneNumber);
	}

	public List<PatientInfo> searchRecords(QueryContent queryContent) {
		return patientInfoRepository.findByQueryContent(queryContent);
	}

	private PatientInfo buildNewPatientInfo(PersonalInfo personalInfo, Consultation consultation) {
		List<Consultation> consultations = new ArrayList<>();
		consultations.add(consultation);
		return new PatientInfo(personalInfo, consultations);
	}

}
