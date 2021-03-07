package dr.sens.dental.clinic.controllers;

import java.time.LocalDate;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dr.sens.dental.clinic.documents.PatientInfo;
import dr.sens.dental.clinic.exceptions.DentalClinicOperationException;
import dr.sens.dental.clinic.services.InvoiceAndPrescriptionService;
import dr.sens.dental.clinic.services.PatientInfoService;
import dr.sens.dental.clinic.services.SessionManagerService;

@Controller
public class InvoiceAndPrescriptionController {

	@Autowired
	private InvoiceAndPrescriptionService invoiceAndPrescriptionService;

	@Autowired
	private SessionManagerService sessionManagerService;

	@Autowired
	private PatientInfoService patientInfoService;

	@GetMapping("/downloadInvoice")
	public void downloadInvoiceAndPrescription(@RequestParam String patientId, @RequestParam String invoiceId,
			@RequestParam String dateOfVisit, HttpSession session, HttpServletResponse response) throws Exception {
		if (!sessionManagerService.isValidSession(session)) {
			throw new DentalClinicOperationException("Login in order to download invoice and prescription");
		}

		PatientInfo patientInfo = patientInfoService.getPatientInfoByPatientId(patientId);

		if (Objects.isNull(patientInfo)) {
			throw new DentalClinicOperationException("Patient information not found with id: " + patientId);
		}

		patientInfo.getConsultations()
				.removeIf(consultation -> consultation.getDateOfVisit().compareTo(LocalDate.parse(dateOfVisit)) != 0);

		if (patientInfo.getConsultations().isEmpty()) {
			throw new DentalClinicOperationException("Patient information not found with id: " + patientId);
		}

		invoiceAndPrescriptionService.createAndWritePdfToResponseStream(response, patientInfo);
	}
}
