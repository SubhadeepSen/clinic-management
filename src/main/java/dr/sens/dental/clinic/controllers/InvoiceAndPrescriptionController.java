package dr.sens.dental.clinic.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dr.sens.dental.clinic.exceptions.DentalClinicOperationException;
import dr.sens.dental.clinic.services.InvoiceAndPrescriptionService;
import dr.sens.dental.clinic.services.SessionManagerService;

@Controller
public class InvoiceAndPrescriptionController {

	@Autowired
	private InvoiceAndPrescriptionService invoiceAndPrescriptionService;

	@Autowired
	private SessionManagerService sessionManagerService;

	@GetMapping("/downloadInvoice")
	public void downloadInvoiceAndPrescription(@RequestParam String patientId, @RequestParam String invoiceId,
			@RequestParam String dateOfVisit, HttpSession session, HttpServletResponse response) throws Exception {
		if (!sessionManagerService.isValidSession(session)) {
			throw new DentalClinicOperationException("Login in order to download invoice and prescription");
		}

		invoiceAndPrescriptionService.createAndWriteToResponseStream(response, patientId, invoiceId);
	}
}
