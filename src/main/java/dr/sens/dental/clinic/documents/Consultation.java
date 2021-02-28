package dr.sens.dental.clinic.documents;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.lang.NonNull;

public class Consultation {

	@NonNull
	private LocalDate dateOfVisit;

	private List<String> chiefComplaints = new ArrayList<>();

	private List<String> onExaminations = new ArrayList<>();

	private List<String> medicalHistories = new ArrayList<>();

	private List<String> investigations = new ArrayList<>();

	private List<String> advices = new ArrayList<>();

	private List<String> workDones = new ArrayList<>();

	private List<String> medicines = new ArrayList<>();

	private LocalDate nextAppointmentDate;

	@NonNull
	private Invoice invoice;

	private LocalDateTime timestamp;

	public Consultation() {
		timestamp = LocalDateTime.now();
	}

	public LocalDate getDateOfVisit() {
		return dateOfVisit;
	}

	public void setDateOfVisit(LocalDate dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	public List<String> getChiefComplaints() {
		return chiefComplaints;
	}

	public void setChiefComplaints(List<String> chiefComplaints) {
		this.chiefComplaints = chiefComplaints;
	}

	public List<String> getOnExaminations() {
		return onExaminations;
	}

	public void setOnExaminations(List<String> onExaminations) {
		this.onExaminations = onExaminations;
	}

	public List<String> getMedicalHistories() {
		return medicalHistories;
	}

	public void setMedicalHistories(List<String> medicalHistories) {
		this.medicalHistories = medicalHistories;
	}

	public List<String> getInvestigations() {
		return investigations;
	}

	public void setInvestigations(List<String> investigations) {
		this.investigations = investigations;
	}

	public List<String> getAdvices() {
		return advices;
	}

	public void setAdvices(List<String> advices) {
		this.advices = advices;
	}

	public List<String> getWorkDones() {
		return workDones;
	}

	public void setWorkDones(List<String> workDones) {
		this.workDones = workDones;
	}

	public List<String> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<String> medicines) {
		this.medicines = medicines;
	}

	public LocalDate getNextAppointmentDate() {
		return nextAppointmentDate;
	}

	public void setNextAppointmentDate(LocalDate nextAppointmentDate) {
		this.nextAppointmentDate = nextAppointmentDate;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}
