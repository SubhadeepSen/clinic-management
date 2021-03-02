package dr.sens.dental.clinic.controllers;

import static dr.sens.dental.clinic.constants.ClinicManagementConstants.ModelAttributes.PATIENT_INFO;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.ModelAttributes.PATIENT_SEARCH_RESULTS;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.ModelAttributes.QUERY_CONTENT;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.PathMapping.REDIRECT_TO_LOGIN;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.Views.PATIENT_DETAILS_PAGE;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.Views.SEARCH_RECORDS_PAGE;
import static dr.sens.dental.clinic.utils.DentalClinicUtils.addToModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import dr.sens.dental.clinic.documents.Consultation;
import dr.sens.dental.clinic.documents.PatientInfo;
import dr.sens.dental.clinic.documents.PersonalInfo;
import dr.sens.dental.clinic.models.PatientSearchResult;
import dr.sens.dental.clinic.models.QueryContent;
import dr.sens.dental.clinic.servcies.PatientInfoService;
import dr.sens.dental.clinic.servcies.SessionManagerService;

@Controller
public class QueryController {

	@Autowired
	private SessionManagerService sessionManagerService;

	@Autowired
	private PatientInfoService patientInfoService;

	@GetMapping("/searchRecords")
	public String getSearchRecordsPage(@ModelAttribute(QUERY_CONTENT) QueryContent queryContent, Model model,
			HttpSession session) {
		if (!sessionManagerService.isValidSession(session)) {
			return REDIRECT_TO_LOGIN;
		}

		List<PatientInfo> patientInfos = patientInfoService.searchRecords(queryContent);
		List<PatientSearchResult> patientSearchResults = transformToSearchResults(patientInfos);

		addToModel(model, QUERY_CONTENT, new QueryContent());
		addToModel(model, PATIENT_SEARCH_RESULTS, patientSearchResults);

		return SEARCH_RECORDS_PAGE;
	}

	@GetMapping("/patientDetails")
	public String getPatientDetailsById(@RequestParam String patientId, Model model, HttpSession session) {
		if (!sessionManagerService.isValidSession(session)) {
			return REDIRECT_TO_LOGIN;
		}

		PatientInfo patientInfo = patientInfoService.getPatientInfoByPatientId(patientId);
		addToModel(model, PATIENT_INFO, patientInfo);

		return PATIENT_DETAILS_PAGE;
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
