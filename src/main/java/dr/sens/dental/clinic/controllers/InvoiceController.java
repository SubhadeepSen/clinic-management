package dr.sens.dental.clinic.controllers;

import static dr.sens.dental.clinic.utils.DentalClinicUtils.addToModel;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

import dr.sens.dental.clinic.models.InvoiceForm;
import dr.sens.dental.clinic.models.PatientForm;
import dr.sens.dental.clinic.servcies.PatientInfoService;
import dr.sens.dental.clinic.servcies.SessionManagerService;

@Controller
public class InvoiceController {

	@Autowired
	private PatientInfoService patientInfoService;

	@Autowired
	private SessionManagerService sessionManagerService;

	@GetMapping("/invoiceForm")
	public String getInvoiceForm(Model model, HttpSession session) throws JsonProcessingException {
		if (!sessionManagerService.isValidSession(session)) {
			return "redirect:/";
		}

		InvoiceForm invoiceForm = (InvoiceForm) sessionManagerService.getSessionAttribute(session, "invoiceForm");
		PatientForm patientForm = (PatientForm) sessionManagerService.getSessionAttribute(session, "patientForm");

		if (invoiceForm == null) {
			invoiceForm = buildNewInvoiceForm(patientForm);
		}

		addToModel(model, "invoiceForm", invoiceForm);

		return "invoiceForm";
	}

	@PostMapping("/reviewInvoiceForm")
	public String reviewInvoiceForm(@ModelAttribute("invoiceForm") InvoiceForm invoiceForm, Model model,
			HttpSession session) {
		if (!sessionManagerService.isValidSession(session)) {
			return "redirect:/";
		}

		PatientForm patientForm = (PatientForm) sessionManagerService.getSessionAttribute(session, "patientForm");

		sessionManagerService.setSessionAttribute(session, "invoiceForm", updateInvoiceForm(invoiceForm, patientForm));

		return "reviewInvoiceForm";
	}

	@PostMapping("/confirmation")
	public String submitForms(Model model, HttpSession session, HttpServletResponse response) {
		if (!sessionManagerService.isValidSession(session)) {
			return "redirect:/";
		}

		PatientForm patientForm = (PatientForm) sessionManagerService.getSessionAttribute(session, "patientForm");
		InvoiceForm invoiceForm = (InvoiceForm) sessionManagerService.getSessionAttribute(session, "invoiceForm");

		sessionManagerService.clearSessionData(session);

		Map<String, String> ids = patientInfoService.insertOrUpdate(patientForm, invoiceForm);
		for (Entry<String, String> id : ids.entrySet()) {
			sessionManagerService.setSessionAttribute(session, id.getKey(), id.getValue());
		}

		return "confirmation";
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
