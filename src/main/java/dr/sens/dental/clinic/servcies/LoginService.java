package dr.sens.dental.clinic.servcies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import dr.sens.dental.clinic.documents.UserAccount;
import dr.sens.dental.clinic.models.LoginForm;

@Service
public class LoginService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public boolean isValidLoginData(LoginForm loginForm) {
		UserAccount userAccount = new UserAccount(loginForm.getUsername(), loginForm.getPassword());

		Query query = new Query(
				Criteria.where("username").is(userAccount.getUsername()).and("password").is(userAccount.getPassword()));
		return mongoTemplate.exists(query, UserAccount.class);
	}
}
