package dr.sens.dental.clinic.pdf;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		try {
			Image header = Image.getInstance("src\\main\\resources\\header.jpg");
			header.scalePercent(200f);
			header.setAlignment(Image.MIDDLE);
			header.setAbsolutePosition(0, document.top() - 70);
			document.add(header);
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			Image header = Image.getInstance("src\\main\\resources\\footer.jpg");
			header.scalePercent(150f);
			header.setAlignment(Image.MIDDLE);
			header.setAbsolutePosition(document.left() - 100, document.bottom() - 100);
			document.add(header);
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}
}
