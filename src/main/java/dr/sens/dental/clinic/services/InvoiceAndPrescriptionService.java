package dr.sens.dental.clinic.services;

import javax.servlet.http.HttpServletResponse;

public interface InvoiceAndPrescriptionService {

	public void createAndWriteToResponseStream(HttpServletResponse response, String patientId, String invoiceId);
}
