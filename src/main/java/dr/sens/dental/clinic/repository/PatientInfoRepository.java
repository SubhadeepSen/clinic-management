package dr.sens.dental.clinic.repository;

import java.util.List;

import dr.sens.dental.clinic.documents.Consultation;
import dr.sens.dental.clinic.documents.PatientInfo;
import dr.sens.dental.clinic.models.QueryContent;

public interface PatientInfoRepository {

	public PatientInfo insertNewPatientInfo(PatientInfo patientInfo);

	public boolean updateExistingPatientInfoConsultations(String patientId, Consultation consultation);

	public PatientInfo findByPatientIdAndOrPhoneNumber(String patientId, String phoneNumber);

	public List<PatientInfo> findByQueryContent(QueryContent queryContent);

	public String getPatientIdIfExits(String phoneNumber);
}
