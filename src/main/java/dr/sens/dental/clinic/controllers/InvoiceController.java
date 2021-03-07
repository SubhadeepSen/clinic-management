package dr.sens.dental.clinic.controllers;

import static dr.sens.dental.clinic.constants.ClinicManagementConstants.ModelAttributes.INVOICE_FORM;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.PathMapping.REDIRECT_TO_LOGIN;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.Views.CONFIRMATION_PAGE;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.Views.INVOICE_FORM_PAGE;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.Views.REVIEW_INVOICE_FORM_PAGE;
import static dr.sens.dental.clinic.utils.DentalClinicUtils.addToModel;

import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

import dr.sens.dental.clinic.constants.ClinicManagementConstants.SessionAttributes;
import dr.sens.dental.clinic.exceptions.DentalClinicValidationException;
import dr.sens.dental.clinic.models.InvoiceForm;
import dr.sens.dental.clinic.models.PatientForm;
import dr.sens.dental.clinic.services.PatientInfoService;
import dr.sens.dental.clinic.services.SessionManagerService;
import dr.sens.dental.clinic.utils.DentalClinicTransformerUtils;
import dr.sens.dental.clinic.utils.DentalClinicValidationUtils;

@Controller
public class InvoiceController {

	@Autowired
	private PatientInfoService patientInfoService;

	@Autowired
	private SessionManagerService sessionManagerService;

	@GetMapping("/invoiceForm")
	public String getInvoiceForm(Model model, HttpSession session) throws JsonProcessingException {
		if (!sessionManagerService.isValidSession(session)) {
			return REDIRECT_TO_LOGIN;
		}

		InvoiceForm invoiceForm = (InvoiceForm) sessionManagerService.getSessionAttribute(session,
				SessionAttributes.INVOICE_FORM);
		PatientForm patientForm = (PatientForm) sessionManagerService.getSessionAttribute(session,
				SessionAttributes.PATIENT_FORM);

		if (invoiceForm == null) {
			invoiceForm = buildNewInvoiceForm(patientForm);
		}

		addToModel(model, INVOICE_FORM, invoiceForm);

		return INVOICE_FORM_PAGE;
	}

	@PostMapping("/reviewInvoiceForm")
	public String reviewInvoiceForm(@ModelAttribute(INVOICE_FORM) InvoiceForm invoiceForm, Model model,
			HttpSession session) {
		if (!sessionManagerService.isValidSession(session)) {
			return REDIRECT_TO_LOGIN;
		}

		try {
			DentalClinicValidationUtils.validateInvoiceForm(invoiceForm);
		} catch (DentalClinicValidationException e) {
			if (StringUtils.isNotBlank(e.getErrorField())) {
				model.addAttribute(e.getErrorField(), e.getMessage());
			}
			return INVOICE_FORM_PAGE;
		}

		DentalClinicTransformerUtils.removeEmptyItems(invoiceForm);

		PatientForm patientForm = (PatientForm) sessionManagerService.getSessionAttribute(session,
				SessionAttributes.PATIENT_FORM);

		sessionManagerService.setSessionAttribute(session, SessionAttributes.INVOICE_FORM,
				updateInvoiceForm(invoiceForm, patientForm));

		return REVIEW_INVOICE_FORM_PAGE;
	}

	@PostMapping("/confirmation")
	public String submitForms(Model model, HttpSession session, HttpServletResponse response) {
		if (!sessionManagerService.isValidSession(session)) {
			return REDIRECT_TO_LOGIN;
		}

		PatientForm patientForm = (PatientForm) sessionManagerService.getSessionAttribute(session,
				SessionAttributes.PATIENT_FORM);
		InvoiceForm invoiceForm = (InvoiceForm) sessionManagerService.getSessionAttribute(session,
				SessionAttributes.INVOICE_FORM);

		if (Objects.isNull(patientForm) && Objects.isNull(invoiceForm)) {
			return REDIRECT_TO_LOGIN;
		}

		sessionManagerService.clearSessionData(session);

		Map<String, String> ids = patientInfoService.insertOrUpdate(patientForm, invoiceForm);
		for (Entry<String, String> id : ids.entrySet()) {
			model.addAttribute(id.getKey(), id.getValue());
		}
		model.addAttribute("dateOfVisit", patientForm.getDateOfVisit());

		return CONFIRMATION_PAGE;
	}

	private InvoiceForm buildNewInvoiceForm(PatientForm patientForm) {
		InvoiceForm newInvoiceForm = new InvoiceForm();
		newInvoiceForm.setAddress(patientForm.getAddress());
		newInvoiceForm.setAge(patientForm.getAge());
		newInvoiceForm.setDateOfVisit(patientForm.getDateOfVisit());
		newInvoiceForm.setEmailId(patientForm.getEmailId());
		newInvoiceForm.setFullName(patientForm.getFullName());
		newInvoiceForm.setOccupation(patientForm.getOccupation());
		newInvoiceForm.setPhoneNumber(patientForm.getPhoneNumber());
		newInvoiceForm.setGender(patientForm.getGender());
		return newInvoiceForm;
	}

	private InvoiceForm updateInvoiceForm(InvoiceForm invoiceForm, PatientForm patientForm) {
		invoiceForm.setAddress(patientForm.getAddress());
		invoiceForm.setAge(patientForm.getAge());
		invoiceForm.setDateOfVisit(patientForm.getDateOfVisit());
		invoiceForm.setEmailId(patientForm.getEmailId());
		invoiceForm.setFullName(patientForm.getFullName());
		invoiceForm.setOccupation(patientForm.getOccupation());
		invoiceForm.setPhoneNumber(patientForm.getPhoneNumber());
		invoiceForm.setGender(patientForm.getGender());
		return invoiceForm;
	}
}
