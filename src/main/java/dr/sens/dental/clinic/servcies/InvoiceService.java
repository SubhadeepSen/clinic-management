package dr.sens.dental.clinic.servcies;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import dr.sens.dental.clinic.pdf.HeaderFooterPageEvent2;
import dr.sens.dental.clinic.pdf.InvoiceTemplate;
import dr.sens.dental.clinic.pdf.PrescriptionTemplate;

@Service
public class InvoiceService {

	public void downloadInvoice(HttpServletResponse response) throws DocumentException, IOException {
		String pdfFileName = String.format("test_%s.pdf", System.currentTimeMillis());
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + pdfFileName + "\"");

		Document document = new Document();
		PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
		pdfWriter.setPageEvent(new HeaderFooterPageEvent2());
		document.open();
		InvoiceTemplate.addInvoiceDetails(document);
		document.newPage();
		PrescriptionTemplate.addPrescriptionDetails(document);
		document.close();
	}

}
