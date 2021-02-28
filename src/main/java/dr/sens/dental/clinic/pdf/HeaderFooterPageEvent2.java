package dr.sens.dental.clinic.pdf;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterPageEvent2 extends PdfPageEventHelper {

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		try {
			Image header = Image.getInstance("src\\main\\resources\\images\\header.jpg");
			int indentation = 0;
			float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()
					- indentation) / header.getWidth()) * 100;
			header.scalePercent(scaler);
			header.scaleAbsoluteWidth(document.getPageSize().getWidth());
			header.setAlignment(Image.MIDDLE);
			header.setAbsolutePosition(0, document.top() - 78);
			document.add(header);
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			Image footer = Image.getInstance("src\\main\\resources\\images\\footer.jpg");
			footer.scaleAbsoluteWidth(document.getPageSize().getWidth());
			footer.setAlignment(Image.MIDDLE);
			footer.setAbsolutePosition(document.left() - 36, document.bottom() - 40);
			footer.scaleAbsoluteWidth(document.getPageSize().getWidth());
			document.add(footer);
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}
}
