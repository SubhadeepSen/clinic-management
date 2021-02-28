package dr.sens.dental.clinic.documents;

import static dr.sens.dental.clinic.utils.DentalClinicUtils.getUniqueId;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;

import com.mongodb.lang.NonNull;

import dr.sens.dental.clinic.models.WorkDoneAmount;

public class Invoice {

	@Indexed(unique = true)
	private String invoiceId;

	@NonNull
	private List<WorkDoneAmount> workDoneAmounts = new ArrayList<>();

	@NonNull
	private String totalAmount;

	public Invoice() {
		this.invoiceId = getUniqueId();
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public List<WorkDoneAmount> getWorkDoneAmounts() {
		return workDoneAmounts;
	}

	public void setWorkDoneAmounts(List<WorkDoneAmount> workDoneAmounts) {
		this.workDoneAmounts = workDoneAmounts;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
}
