package dr.sens.dental.clinic.services.impl;

import static dr.sens.dental.clinic.constants.ClinicManagementConstants.INVOICE_ID;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.PATIENT_ID;
import static dr.sens.dental.clinic.utils.DentalClinicTransformerUtils.transformToConsultation;
import static dr.sens.dental.clinic.utils.DentalClinicTransformerUtils.transformToPersonalInfo;
import static dr.sens.dental.clinic.utils.DentalClinicValidationUtils.validateInvoiceTotalAmount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import dr.sens.dental.clinic.documents.Consultation;
import dr.sens.dental.clinic.documents.PatientInfo;
import dr.sens.dental.clinic.documents.PersonalInfo;
import dr.sens.dental.clinic.models.InvoiceForm;
import dr.sens.dental.clinic.models.PatientForm;
import dr.sens.dental.clinic.models.PatientSearchResult;
import dr.sens.dental.clinic.models.QueryContent;
import dr.sens.dental.clinic.repository.PatientInfoRepository;
import dr.sens.dental.clinic.services.PatientInfoService;

@Service
public class PatientInfoServiceImpl implements PatientInfoService {

	@Autowired
	private PatientInfoRepository patientInfoRepository;

	@Override
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

	@Override
	public PatientInfo getPatientInfoByPatientId(String patientId) {
		return patientInfoRepository.findByPatientIdAndOrPhoneNumber(patientId, null);
	}

	@Override
	public PatientInfo getPatientInfoByPhoneNumber(String phoneNumber) {
		return patientInfoRepository.findByPatientIdAndOrPhoneNumber(null, phoneNumber);
	}

	@Override
	public List<PatientSearchResult> searchRecords(QueryContent queryContent) {
		return transformToSearchResults(patientInfoRepository.findByQueryContent(queryContent));
	}

	private PatientInfo buildNewPatientInfo(PersonalInfo personalInfo, Consultation consultation) {
		List<Consultation> consultations = new ArrayList<>();
		consultations.add(consultation);
		return new PatientInfo(personalInfo, consultations);
	}

	private List<PatientSearchResult> transformToSearchResults(List<PatientInfo> patientInfos) {
		List<PatientSearchResult> patientSearchResults = new ArrayList<>();
		patientInfos.stream().forEach(patientInfo -> {
			List<Consultation> consultations = patientInfo.getConsultations();
			if (CollectionUtils.isEmpty(consultations)) {
				PatientSearchResult patientSearchResult = new PatientSearchResult();
				patientSearchResult.setPatientId(patientInfo.getPatientId());
				populatePersonalInfo(patientSearchResult, patientInfo.getPersonalInfo());
				patientSearchResults.add(patientSearchResult);
			} else {
				consultations.stream().forEach(consultation -> {
					PatientSearchResult patientSearchResult = new PatientSearchResult();
					patientSearchResult.setPatientId(patientInfo.getPatientId());
					populatePersonalInfo(patientSearchResult, patientInfo.getPersonalInfo());
					patientSearchResult.setDateOfVisit(consultation.getDateOfVisit().toString());
					if (Objects.nonNull(consultation.getNextAppointmentDate())) {
						patientSearchResult.setNextAppointmentDate(consultation.getNextAppointmentDate().toString());
					}
					patientSearchResults.add(patientSearchResult);
				});
			}

		});
		return patientSearchResults;
	}

	private void populatePersonalInfo(PatientSearchResult patientSearchResult, PersonalInfo personalInfo) {
		patientSearchResult.setAge(String.valueOf(personalInfo.getAge()));
		patientSearchResult.setEmailId(personalInfo.getEmailId());
		patientSearchResult.setFullName(personalInfo.getFullName());
		patientSearchResult.setGender(personalInfo.getGender().getValue());
		patientSearchResult.setPhoneNumber(personalInfo.getPhoneNumber());
	}

}
