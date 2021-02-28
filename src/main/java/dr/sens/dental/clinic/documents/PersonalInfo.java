package dr.sens.dental.clinic.documents;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.index.Indexed;

import com.mongodb.lang.NonNull;

public class PersonalInfo {

	@NonNull
	private String fullName;

	@NonNull
	private int age;

	@NonNull
	private Gender gender;

	private String address;

	@Indexed(unique = true)
	@NonNull
	private String phoneNumber;

	private String emailId;

	private String occupation;

	private LocalDateTime timestamp;

	public PersonalInfo() {
		timestamp = LocalDateTime.now();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
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

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

}
