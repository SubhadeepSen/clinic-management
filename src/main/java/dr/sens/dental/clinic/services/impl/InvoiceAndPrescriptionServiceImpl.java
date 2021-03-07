package dr.sens.dental.clinic.services.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import dr.sens.dental.clinic.documents.PatientInfo;
import dr.sens.dental.clinic.pdf.HeaderFooterPageEvent;
import dr.sens.dental.clinic.pdf.InvoiceUtils;
import dr.sens.dental.clinic.pdf.PrescriptionUtils;
import dr.sens.dental.clinic.services.InvoiceAndPrescriptionService;

@Service
public class InvoiceAndPrescriptionServiceImpl implements InvoiceAndPrescriptionService {

	@Override
	public void createAndWritePdfToResponseStream(HttpServletResponse response, PatientInfo patientInfo) {
		String pdfFileName = String.format("%s_%s_%s.pdf",
				patientInfo.getPersonalInfo().getFullName().replaceAll(" ", "_"), patientInfo.getPatientId(),
				System.currentTimeMillis());

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + pdfFileName + "\"");

		Document document = new Document();
		try {
			PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
			pdfWriter.setPageEvent(new HeaderFooterPageEvent());
			document.open();
			InvoiceUtils.addInvoiceDetails(document, patientInfo);
			document.newPage();
			PrescriptionUtils.addPrescriptionDetails(document, patientInfo);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}
}
