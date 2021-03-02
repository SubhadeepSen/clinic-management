package dr.sens.dental.clinic.services;

import dr.sens.dental.clinic.models.LoginForm;

public interface LoginService {

	public boolean isValidLoginData(LoginForm loginForm);
}
