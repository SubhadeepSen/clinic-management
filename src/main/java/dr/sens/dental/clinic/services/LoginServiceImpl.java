package dr.sens.dental.clinic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dr.sens.dental.clinic.documents.UserAccount;
import dr.sens.dental.clinic.models.LoginForm;
import dr.sens.dental.clinic.repository.UserAccountRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public boolean isValidLoginData(LoginForm loginForm) {
		UserAccount userAccount = new UserAccount(loginForm.getUsername(), loginForm.getPassword());
		return userAccountRepository.isUserAccountExist(userAccount);
	}
}
