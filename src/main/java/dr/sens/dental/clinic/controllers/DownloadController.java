package dr.sens.dental.clinic.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dr.sens.dental.clinic.servcies.InvoiceService;

@Controller
public class DownloadController {

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping("/downloadInvoice/{patientId}/{invoiceId}")
	public void downloadInvoice(@PathVariable String patientId, @PathVariable String invoiceId, HttpSession session,
			HttpServletResponse response) throws Exception {
		invoiceService.downloadInvoice(response);
	}
}
