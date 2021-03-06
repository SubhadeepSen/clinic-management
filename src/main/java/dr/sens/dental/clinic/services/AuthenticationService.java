package dr.sens.dental.clinic.services;

import dr.sens.dental.clinic.models.LoginForm;

public interface AuthenticationService {

	public boolean isValidCredential(LoginForm loginForm);
}
