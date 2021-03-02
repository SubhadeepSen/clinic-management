package dr.sens.dental.clinic.controllers;

import static dr.sens.dental.clinic.constants.ClinicManagementConstants.PathMapping.REDIRECT_TO_LOGIN;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.PathMapping.REDIRECT_TO_PATIENT_FORM;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.SessionAttributes.PHONE_NUMBER;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.Views.PATIENT_FORM_PAGE;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.Views.REVIEW_PATIENT_FORM_PAGE;
import static dr.sens.dental.clinic.utils.DentalClinicUtils.addToModel;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dr.sens.dental.clinic.constants.ClinicManagementConstants.ModelAttributes;
import dr.sens.dental.clinic.constants.ClinicManagementConstants.SessionAttributes;
import dr.sens.dental.clinic.documents.PatientInfo;
import dr.sens.dental.clinic.documents.PersonalInfo;
import dr.sens.dental.clinic.exceptions.DentalClinicValidationException;
import dr.sens.dental.clinic.models.PatientForm;
import dr.sens.dental.clinic.servcies.PatientInfoService;
import dr.sens.dental.clinic.servcies.SessionManagerService;
import dr.sens.dental.clinic.utils.DentalClinicTransformerUtils;
import dr.sens.dental.clinic.utils.DentalClinicValidationUtils;

@Controller
public class PatientController {

	@Autowired
	private SessionManagerService sessionManagerService;

	@Autowired
	private PatientInfoService patientInfoService;

	@GetMapping("/patientForm")
	public String getPatientForm(Model model, HttpSession session) {
		if (!sessionManagerService.isValidSession(session)) {
			return REDIRECT_TO_LOGIN;
		}

		PatientForm patientForm = (PatientForm) sessionManagerService.getSessionAttribute(session,
				SessionAttributes.PATIENT_FORM);

		if (patientForm == null) {
			patientForm = new PatientForm();
		}

		String phoneNumber = (String) sessionManagerService.getSessionAttribute(session, PHONE_NUMBER);
		if (StringUtils.isNotBlank(phoneNumber)) {
			sessionManagerService.removeSessionAttribute(session, PHONE_NUMBER);
			PatientInfo patientInfo = patientInfoService.getPatientInfoByPhoneNumber(phoneNumber);
			poulatePatientForm(patientForm, patientInfo);
		}

		addToModel(model, ModelAttributes.PATIENT_FORM, patientForm);
		return PATIENT_FORM_PAGE;
	}

	@GetMapping("/polulatePatientForm")
	public String getPolulatedPatientForm(Model model, HttpSession session, @RequestParam String phoneNumber) {
		if (!sessionManagerService.isValidSession(session)) {
			return REDIRECT_TO_LOGIN;
		}

		if (StringUtils.isNotBlank(phoneNumber)) {
			sessionManagerService.setSessionAttribute(session, PHONE_NUMBER, phoneNumber);
		}

		return REDIRECT_TO_PATIENT_FORM;
	}

	@PostMapping("/reviewPatientForm")
	public String reviewPatientForm(@ModelAttribute(ModelAttributes.PATIENT_FORM) PatientForm patientForm, Model model,
			HttpSession session) {
		if (!sessionManagerService.isValidSession(session)) {
			return REDIRECT_TO_LOGIN;
		}
		try {
			DentalClinicValidationUtils.validatePatientForm(patientForm);
		} catch (DentalClinicValidationException e) {
			if (StringUtils.isNotBlank(e.getErrorField())) {
				model.addAttribute(e.getErrorField(), e.getMessage());
			}
			return PATIENT_FORM_PAGE;
		}

		DentalClinicTransformerUtils.removeEmptyItems(patientForm);
		sessionManagerService.setSessionAttribute(session, SessionAttributes.PATIENT_FORM, patientForm);
		return REVIEW_PATIENT_FORM_PAGE;
	}

	private void poulatePatientForm(PatientForm patientForm, PatientInfo patientInfo) {
		if (Objects.nonNull(patientInfo)) {
			PersonalInfo personalInfo = patientInfo.getPersonalInfo();
			patientForm.setFullName(personalInfo.getFullName());
			patientForm.setAddress(personalInfo.getAddress());
			patientForm.setAge(String.valueOf(personalInfo.getAge()));
			patientForm.setGender(personalInfo.getGender().getValue());
			patientForm.setEmailId(personalInfo.getEmailId());
			patientForm.setPhoneNumber(personalInfo.getPhoneNumber());
			patientForm.setOccupation(personalInfo.getOccupation());
		}
	}
}
