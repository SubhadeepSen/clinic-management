package dr.sens.dental.clinic.documents;

import static dr.sens.dental.clinic.utils.DentalClinicUtils.getUniqueId;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Document(collection = "patientInfo")
public class PatientInfo {

	@Id
	private String patientId;

	@NonNull
	private PersonalInfo personalInfo;

	private List<Consultation> consultations = new ArrayList<>();

	public PatientInfo(PersonalInfo personalInfo, List<Consultation> consultations) {
		this.patientId = getUniqueId();
		this.personalInfo = personalInfo;
		this.consultations = consultations;
	}

	public String getPatientId() {
		return patientId;
	}

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public List<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}
}
