package dr.sens.dental.clinic.controllers;

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
			return "redirect:/";
		}

		PatientForm patientForm = (PatientForm) sessionManagerService.getSessionAttribute(session, "patientForm");

		if (patientForm == null) {
			patientForm = new PatientForm();
		}

		String phoneNumber = (String) sessionManagerService.getSessionAttribute(session, "phoneNumber");
		if (StringUtils.isNotBlank(phoneNumber)) {
			sessionManagerService.removeSessionAttribute(session, "phoneNumber");
			PatientInfo patientInfo = patientInfoService.getPatientInfoByPhoneNumber(phoneNumber);
			poulatePatientForm(patientForm, patientInfo);
		}

		addToModel(model, "patientForm", patientForm);
		return "patientForm";
	}

	@GetMapping("/polulatePatientForm")
	public String getPolulatedPatientForm(Model model, HttpSession session, @RequestParam String phoneNumber) {
		if (!sessionManagerService.isValidSession(session)) {
			return "redirect:/";
		}

		if (StringUtils.isNotBlank(phoneNumber)) {
			sessionManagerService.setSessionAttribute(session, "phoneNumber", phoneNumber);
		}

		return "redirect:/patientForm";
	}

	@PostMapping("/reviewPatientForm")
	public String reviewPatientForm(@ModelAttribute("patientForm") PatientForm patientForm, Model model,
			HttpSession session) {
		if (!sessionManagerService.isValidSession(session)) {
			return "redirect:/";
		}
		try {
			DentalClinicValidationUtils.validatePatientForm(patientForm);
		} catch (DentalClinicValidationException e) {
			if (StringUtils.isNotBlank(e.getErrorField())) {
				model.addAttribute(e.getErrorField(), e.getMessage());
			}
			return "patientForm";
		}

		DentalClinicTransformerUtils.removeEmptyItems(patientForm);
		sessionManagerService.setSessionAttribute(session, "patientForm", patientForm);
		return "reviewPatientForm";
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
