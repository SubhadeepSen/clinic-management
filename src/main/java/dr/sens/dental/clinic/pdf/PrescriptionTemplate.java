package dr.sens.dental.clinic.pdf;

import java.util.Arrays;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

public class PrescriptionTemplate {

	private static Font boldFont = new Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD);
	private static Font textFont = new Font(Font.FontFamily.HELVETICA, 12f);
	private static Font italicUnderlineFont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC | Font.UNDERLINE);

	public static void addPrescriptionDetails(Document document) throws DocumentException {
		addNewLine(document, 6);

		document.add(new Chunk(String.format("%10s%2s", "Name:", ""), boldFont));
		document.add(new Chunk("Subhadeep Sen", italicUnderlineFont));

		document.add(new Chunk(String.format("%35s%2s", "Age:", ""), boldFont));
		document.add(new Chunk("23", italicUnderlineFont));

		document.add(new Chunk(String.format("%35s%2s", "Gender:", ""), boldFont));
		document.add(new Chunk("Male", italicUnderlineFont));

		addNewLine(document);

		document.add(new Chunk(String.format("%13s%2s", "Address:", ""), boldFont));
		document.add(new Chunk("Banamali Sen Lane, Chawk Bazar, Purulia-723101, West bengal", italicUnderlineFont));

		addNewLine(document);

		document.add(new Chunk(String.format("%15s%2s", "Phone No.:", ""), boldFont));
		document.add(new Chunk("9944834903", italicUnderlineFont));

		document.add(new Chunk(String.format("%35s%2s", "Email Id:", ""), boldFont));
		document.add(new Chunk("subhadeep0977@gmail.com", italicUnderlineFont));

		addNewLine(document);

		document.add(new Chunk(String.format("%16s%2s", "Occupation:", ""), boldFont));
		document.add(new Chunk("Teacher", italicUnderlineFont));

		document.add(new Chunk(String.format("%35s%2s", "Date of Visit:", ""), boldFont));
		document.add(new Chunk("22/04/2020", italicUnderlineFont));

		addNewLine(document);
		addNewLine(document);

		addPrescription(document, "Medical History",
				Arrays.asList("Medical History 1", "Medical History 2", "Medical History 3"));
		addPrescription(document, "Investigation",
				Arrays.asList("Investigation 1", "Investigation 2", "Investigation 3"));
		addPrescription(document, "Advice", Arrays.asList("Advice 1", "Advice 2", "Advice 3"));

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
