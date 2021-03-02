package dr.sens.dental.clinic.repository.impl;

import static dr.sens.dental.clinic.constants.DBConstants.QueryAttributes.PASSWORD;
import static dr.sens.dental.clinic.constants.DBConstants.QueryAttributes.USERNAME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import dr.sens.dental.clinic.documents.UserAccount;
import dr.sens.dental.clinic.repository.UserAccountRepository;

@Repository
public class UserAccountRepositoryImpl implements UserAccountRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public boolean isUserAccountExist(UserAccount userAccount) {
		Criteria criteria = Criteria.where(USERNAME).is(userAccount.getUsername()).and(PASSWORD)
				.is(userAccount.getPassword());
		if (criteria.getCriteriaObject().isEmpty()) {
			return false;
		}
		return mongoTemplate.exists(new Query(criteria), UserAccount.class);
	}

}
