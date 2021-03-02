package dr.sens.dental.clinic.services;

import java.util.List;
import java.util.Map;

import dr.sens.dental.clinic.documents.PatientInfo;
import dr.sens.dental.clinic.models.InvoiceForm;
import dr.sens.dental.clinic.models.PatientForm;
import dr.sens.dental.clinic.models.PatientSearchResult;
import dr.sens.dental.clinic.models.QueryContent;

public interface PatientInfoService {

	public Map<String, String> insertOrUpdate(PatientForm patientForm, InvoiceForm invoiceForm);

	public PatientInfo getPatientInfoByPatientId(String patientId);

	public PatientInfo getPatientInfoByPhoneNumber(String phoneNumber);

	public List<PatientSearchResult> searchRecords(QueryContent queryContent);

}
