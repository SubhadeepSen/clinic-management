package dr.sens.dental.clinic.pdf;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import dr.sens.dental.clinic.documents.Consultation;
import dr.sens.dental.clinic.documents.PatientInfo;
import dr.sens.dental.clinic.documents.PersonalInfo;
import dr.sens.dental.clinic.models.WorkDoneAmount;

public final class InvoiceUtils {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private static Font boldFont = new Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD);
	private static Font textFont = new Font(Font.FontFamily.HELVETICA, 12f);
	private static Font italicUnderlineFont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC | Font.UNDERLINE);

	private InvoiceUtils() {
	}

	public static void addInvoiceDetails(Document document, PatientInfo patientInfo) throws DocumentException {
		PersonalInfo personalInfo = patientInfo.getPersonalInfo();
		Iterator<Consultation> it = patientInfo.getConsultations().iterator();
		Consultation consultation = null;

		while (it.hasNext()) {
			consultation = it.next();
			addNewLine(document, 6);

			document.add(new Chunk(String.format("%10s%2s", "Name:", ""), boldFont));
			document.add(new Chunk(personalInfo.getFullName(), italicUnderlineFont));

			document.add(new Chunk(String.format("%35s%2s", "Age:", ""), boldFont));
			document.add(new Chunk(String.valueOf(personalInfo.getAge()), italicUnderlineFont));

			document.add(new Chunk(String.format("%35s%2s", "Gender:", ""), boldFont));
			document.add(new Chunk(personalInfo.getGender().getValue(), italicUnderlineFont));

			addNewLine(document);

			document.add(new Chunk(String.format("%13s%2s", "Address:", ""), boldFont));
			document.add(new Chunk(personalInfo.getAddress(), italicUnderlineFont));

			addNewLine(document);

			document.add(new Chunk(String.format("%15s%2s", "Phone No.:", ""), boldFont));
			document.add(new Chunk(personalInfo.getPhoneNumber(), italicUnderlineFont));

			document.add(new Chunk(String.format("%35s%2s", "Email Id:", ""), boldFont));
			document.add(new Chunk(personalInfo.getEmailId(), italicUnderlineFont));

			addNewLine(document);

			document.add(new Chunk(String.format("%19s%2s", "Date of Visit:", ""), boldFont));
			document.add(new Chunk(consultation.getDateOfVisit().format(DateTimeFormatter.ofPattern(DD_MM_YYYY)),
					italicUnderlineFont));

			addNewLine(document);
			addNewLine(document);

			PdfPTable table = new PdfPTable(2);
			addTableHeader(table);

			for (WorkDoneAmount wda : consultation.getInvoice().getWorkDoneAmounts()) {
				table.addCell(createWorkDonePdfCell(wda.getWorkDone()));
				table.addCell(createAmountPdfCell(wda.getAmount()));
			}

			addTotalAmount(table, consultation.getInvoice().getTotalAmount());
			document.add(table);
			if (it.hasNext()) {
				document.newPage();
			}
		}
	}

	private static void addNewLine(Document document) throws DocumentException {
		document.add(new Paragraph("\n"));
	}

	private static void addNewLine(Document document, int noOfLines) throws DocumentException {
		for (int i = 1; i <= noOfLines; i++) {
			document.add(new Paragraph("\n"));
		}
	}

	private static void addTableHeader(PdfPTable table) throws DocumentException {
		PdfPCell workDone = new PdfPCell(new Paragraph("Work Done", boldFont));
		workDone.setHorizontalAlignment(Element.ALIGN_CENTER);
		addCellPadding(workDone);
		addLightGrayBackground(workDone);
		PdfPCell amountCharged = new PdfPCell(new Paragraph("Amount (rs./-)", boldFont));
		amountCharged.setHorizontalAlignment(Element.ALIGN_CENTER);
		addCellPadding(amountCharged);
		addLightGrayBackground(amountCharged);
		table.addCell(workDone);
		table.addCell(amountCharged);
		table.setHeaderRows(1);
	}

	private static void addTotalAmount(PdfPTable table, String totalAmount) throws DocumentException {
		PdfPCell workDone = new PdfPCell(new Paragraph("Total", boldFont));
		workDone.setHorizontalAlignment(Element.ALIGN_RIGHT);
		addCellPadding(workDone);
		addLightGrayBackground(workDone);
		PdfPCell amountCharged = new PdfPCell(new Paragraph(totalAmount, boldFont));
		amountCharged.setHorizontalAlignment(Element.ALIGN_RIGHT);
		addCellPadding(amountCharged);
		addLightGrayBackground(amountCharged);
		table.addCell(workDone);
		table.addCell(amountCharged);
	}

	private static PdfPCell createWorkDonePdfCell(String cellValue) throws DocumentException {
		PdfPCell workDonePdfCell = new PdfPCell(new Paragraph(cellValue, textFont));
		workDonePdfCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		addCellPadding(workDonePdfCell);
		return workDonePdfCell;
	}

	private static PdfPCell createAmountPdfCell(String cellValue) throws DocumentException {
		PdfPCell amountPdfCell = new PdfPCell(new Paragraph(cellValue, textFont));
		amountPdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		addCellPadding(amountPdfCell);
		return amountPdfCell;
	}

	private static void addCellPadding(PdfPCell pdfPCell) throws DocumentException {
		pdfPCell.setPaddingBottom(5);
		pdfPCell.setPaddingTop(5);
		pdfPCell.setPaddingLeft(5);
		pdfPCell.setPaddingRight(5);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
	}

	private static void addLightGrayBackground(PdfPCell pdfPCell) throws DocumentException {
		pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	}

}
