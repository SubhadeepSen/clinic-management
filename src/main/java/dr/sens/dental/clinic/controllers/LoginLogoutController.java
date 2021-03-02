package dr.sens.dental.clinic.controllers;

import static dr.sens.dental.clinic.constants.ClinicManagementConstants.ModelAttributes.LOGIN_FORM;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.PathMapping.REDIRECT_TO_HOME;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.PathMapping.REDIRECT_TO_LOGIN;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.SessionAttributes.USERNAME;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.Views.LOGIN_PAGE;
import static dr.sens.dental.clinic.utils.DentalClinicUtils.addToModel;
import static dr.sens.dental.clinic.utils.DentalClinicUtils.removeFromModel;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dr.sens.dental.clinic.models.LoginForm;
import dr.sens.dental.clinic.services.LoginService;
import dr.sens.dental.clinic.services.SessionManagerService;

@Controller
public class LoginLogoutController {

	@Autowired
	private SessionManagerService sessionManagerService;

	@Autowired
	private LoginService loginService;

	@GetMapping("/")
	public String getLoginPage(Model model, HttpSession session) {
		if (!sessionManagerService.isValidSession(session)) {
			addToModel(model, LOGIN_FORM, new LoginForm());
			return LOGIN_PAGE;
		}
		return REDIRECT_TO_HOME;
	}

	@PostMapping("/login")
	public String login(@ModelAttribute(LOGIN_FORM) LoginForm loginForm, Model model, HttpSession session) {
		if (loginService.isValidLoginData(loginForm)) {
			loginForm.setPassword(null);
			sessionManagerService.setSessionAttribute(session, USERNAME, loginForm.getUsername());
			removeFromModel(model, "errorMessage");
			return REDIRECT_TO_HOME;
		}
		addToModel(model, "errorMessage", "Invalid username or password");
		return LOGIN_PAGE;
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		sessionManagerService.invalidateSession(session);
		return REDIRECT_TO_LOGIN;
	}

}
