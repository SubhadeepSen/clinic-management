package dr.sens.dental.clinic.controllers;

import static dr.sens.dental.clinic.constants.ClinicManagementConstants.ModelAttributes.PATIENT_INFO;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.ModelAttributes.PATIENT_SEARCH_RESULTS;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.ModelAttributes.QUERY_CONTENT;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.PathMapping.REDIRECT_TO_LOGIN;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.Views.PATIENT_DETAILS_PAGE;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.Views.SEARCH_RECORDS_PAGE;
import static dr.sens.dental.clinic.utils.DentalClinicUtils.addToModel;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import dr.sens.dental.clinic.documents.PatientInfo;
import dr.sens.dental.clinic.models.PatientSearchResult;
import dr.sens.dental.clinic.models.QueryContent;
import dr.sens.dental.clinic.services.PatientInfoService;
import dr.sens.dental.clinic.services.SessionManagerService;

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

		List<PatientSearchResult> patientSearchResults = patientInfoService.searchRecords(queryContent);

		addToModel(model, QUERY_CONTENT, new QueryContent());
		addToModel(model, PATIENT_SEARCH_RESULTS, patientSearchResults);

		return SEARCH_RECORDS_PAGE;
	}

	@GetMapping("/patientDetails")
	public String getPatientDetailsById(@RequestParam String patientId, @RequestParam String dateOfVisit, Model model,
			HttpSession session) {
		if (!sessionManagerService.isValidSession(session)) {
			return REDIRECT_TO_LOGIN;
		}

		PatientInfo patientInfo = patientInfoService.getPatientInfoByPatientId(patientId);
		patientInfo.getConsultations()
				.removeIf(consultation -> consultation.getDateOfVisit().compareTo(LocalDate.parse(dateOfVisit)) != 0);
		addToModel(model, PATIENT_INFO, patientInfo);

		return PATIENT_DETAILS_PAGE;
	}
}
