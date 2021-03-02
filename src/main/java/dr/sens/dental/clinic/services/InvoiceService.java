package dr.sens.dental.clinic.services;

import javax.servlet.http.HttpServletResponse;

public interface InvoiceService {

	public void downloadInvoice(HttpServletResponse response);
}
