package dr.sens.dental.clinic.repository;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import dr.sens.dental.clinic.documents.Consultation;
import dr.sens.dental.clinic.documents.PatientInfo;
import dr.sens.dental.clinic.exceptions.DentalClinicOperationException;
import dr.sens.dental.clinic.models.QueryContent;

@Repository
public class PatientInfoRepositoryImpl implements PatientInfoRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public PatientInfo insertNewPatientInfo(PatientInfo patientInfo) {
		return mongoTemplate.insert(patientInfo);
	}

	@Override
	public PatientInfo updateExistingPatientInfoConsultations(String patientId, Consultation consultation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientInfo findByPatientIdAndOrPhoneNumber(String patientId, String phoneNumber) {
		if (isBlank(patientId) && isBlank(phoneNumber)) {
			throw new DentalClinicOperationException(
					String.format("provided patient id is [%s] and phone number is [%s]", patientId, phoneNumber));
		}

		Criteria criteria = new Criteria();

		if (isNotBlank(patientId)) {
			criteria.and("_id").is(patientId);
		}

		if (isNotBlank(phoneNumber)) {
			criteria.and("personalInfo.phoneNumber").is(phoneNumber);
		}

		List<PatientInfo> patientInfo = mongoTemplate.find(new Query(criteria), PatientInfo.class);

		if (!CollectionUtils.isEmpty(patientInfo)) {
			return patientInfo.stream().findFirst().get();
		}

		return null;
	}

	@Override
	public List<PatientInfo> findByQueryContent(QueryContent queryContent) {
		Criteria criteria = buildQueryCriteria(queryContent);
		if (criteria.getCriteriaObject().isEmpty()) {
			return Collections.emptyList();
		}
		return mongoTemplate.find(new Query(criteria), PatientInfo.class);
	}

	@Override
	public String getPatientIdIfExits(String phoneNumber) {
		PatientInfo patientInfo = findByPatientIdAndOrPhoneNumber(null, phoneNumber);
		if (Objects.nonNull(patientInfo)) {
			return patientInfo.getPatientId();
		}
		return null;
	}

	private Criteria buildQueryCriteria(QueryContent queryContent) {
		Criteria criteria = new Criteria();

		if (StringUtils.isNotBlank(queryContent.getDateOfVisit())) {
			criteria.and("consultations")
					.elemMatch(Criteria.where("dateOfVisit").is(LocalDate.parse(queryContent.getDateOfVisit())));
		}

		if (StringUtils.isNotBlank(queryContent.getEmailId())) {
			criteria.and("personalInfo.emailId").is(queryContent.getEmailId());
		}

		if (StringUtils.isNotBlank(queryContent.getFullName())) {
			criteria.and("personalInfo.fullName").is(queryContent.getFullName());
		}

		if (StringUtils.isNotBlank(queryContent.getPatientId())) {
			criteria.and("_id").is(queryContent.getPatientId());
		}

		if (StringUtils.isNotBlank(queryContent.getInvoiceId())) {
			criteria.and("consultations")
					.elemMatch(Criteria.where("invoice.invoiceId").is(queryContent.getInvoiceId()));
		}

		if (StringUtils.isNotBlank(queryContent.getNextAppointmentDate())) {
			criteria.and("consultations").elemMatch(
					Criteria.where("nextAppointmentDate").is(LocalDate.parse(queryContent.getNextAppointmentDate())));
		}

		if (StringUtils.isNotBlank(queryContent.getPhoneNumber())) {
			criteria.and("personalInfo.phoneNumber").is(queryContent.getPhoneNumber());
		}

		return criteria;
	}
}
