package dr.sens.dental.clinic.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientForm {

	private String fullName;
	private String age;
	private String gender;
	private String address;
	private String phoneNumber;
	private String emailId;
	private String occupation;
	private String dateOfVisit = LocalDate.now().toString();
	private List<String> chiefComplaints = new ArrayList<>();
	private List<String> onExaminations = new ArrayList<>();
	private List<String> medicalHistories = new ArrayList<>();
	private List<String> investigations = new ArrayList<>();
	private List<String> advices = new ArrayList<>();
	private List<String> workDones = new ArrayList<>();
	private List<String> medicines = new ArrayList<>();
	private String nextAppointmentDate;

	public PatientForm() {
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDateOfVisit() {
		return dateOfVisit;
	}

	public void setDateOfVisit(String dateOfVisit) {
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

	public String getNextAppointmentDate() {
		return nextAppointmentDate;
	}

	public void setNextAppointmentDate(String nextAppointmentDate) {
		this.nextAppointmentDate = nextAppointmentDate;
	}
}
