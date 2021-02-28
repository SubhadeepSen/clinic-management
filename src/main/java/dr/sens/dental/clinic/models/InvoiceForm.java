package dr.sens.dental.clinic.models;

import java.util.ArrayList;
import java.util.List;

public class InvoiceForm {

	private String fullName;
	private String age;
	private String gender;
	private String address;
	private String phoneNumber;
	private String emailId;
	private String occupation;
	private String dateOfVisit;
	private List<WorkDoneAmount> workDoneAmounts = new ArrayList<>();
	private String totalAmount = "0.0";

	public InvoiceForm() {
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
