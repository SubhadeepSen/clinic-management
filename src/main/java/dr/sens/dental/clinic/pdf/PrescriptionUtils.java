package dr.sens.dental.clinic.pdf;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

import dr.sens.dental.clinic.documents.Consultation;
import dr.sens.dental.clinic.documents.PatientInfo;
import dr.sens.dental.clinic.documents.PersonalInfo;

public final class PrescriptionUtils {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private static Font boldFont = new Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD);
	private static Font textFont = new Font(Font.FontFamily.HELVETICA, 12f);
	private static Font italicUnderlineFont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC | Font.UNDERLINE);

	private PrescriptionUtils() {
	}

	public static void addPrescriptionDetails(Document document, PatientInfo patientInfo) throws DocumentException {
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

			addPrescription(document, "Chief Complaint(C/C)", consultation.getChiefComplaints());
			addPrescription(document, "On Examination(O/E)", consultation.getOnExaminations());
			addPrescription(document, "Medical History", consultation.getMedicalHistories());
			addPrescription(document, "Investigations", consultation.getInvestigations());
			addPrescription(document, "Advice", consultation.getAdvices());
			addPrescription(document, "Work Done", consultation.getWorkDones());
			addPrescription(document, "Medicine", consultation.getMedicines());

			if (Objects.nonNull(consultation.getNextAppointmentDate())) {
				document.add(new Chunk(String.format("%10s%2s", "Next Appointment Date:", ""), boldFont));
				document.add(
						new Chunk(consultation.getNextAppointmentDate().format(DateTimeFormatter.ofPattern(DD_MM_YYYY)),
								italicUnderlineFont));
			}

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

	private static void addPrescription(Document document, String heading, List<String> contents)
			throws DocumentException {
		if (!contents.isEmpty()) {
			document.add(new Paragraph(String.format("%10s%2s", heading + ":", ""), boldFont));
			for (int i = 0; i < contents.size(); i++) {
				document.add(new Chunk(String.format("%5s.%s", (i + 1), " " + contents.get(i)), textFont));
			}
			addNewLine(document);
		}
	}
}
