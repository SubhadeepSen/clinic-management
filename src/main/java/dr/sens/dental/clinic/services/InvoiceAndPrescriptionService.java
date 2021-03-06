package dr.sens.dental.clinic.services;

import javax.servlet.http.HttpServletResponse;

import dr.sens.dental.clinic.documents.PatientInfo;

public interface InvoiceAndPrescriptionService {

	public void createAndWritePdfToResponseStream(HttpServletResponse response, PatientInfo patientInfo);
}
