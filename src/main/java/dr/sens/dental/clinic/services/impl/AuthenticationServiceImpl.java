package dr.sens.dental.clinic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dr.sens.dental.clinic.documents.UserAccount;
import dr.sens.dental.clinic.models.LoginForm;
import dr.sens.dental.clinic.repository.UserAccountRepository;
import dr.sens.dental.clinic.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public boolean isValidCredential(LoginForm loginForm) {
		UserAccount userAccount = new UserAccount(loginForm.getUsername(), loginForm.getPassword());
		return userAccountRepository.isUserAccountExist(userAccount);
	}
}
