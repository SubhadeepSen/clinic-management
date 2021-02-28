package dr.sens.dental.clinic.controllers;

import static dr.sens.dental.clinic.constants.DentalClinicConstants.USERNAME_SESSION_ATTRIBUTE;
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
import dr.sens.dental.clinic.servcies.LoginService;
import dr.sens.dental.clinic.servcies.SessionManagerService;

@Controller
public class LoginLogoutController {

	@Autowired
	private SessionManagerService sessionManagerService;

	@Autowired
	private LoginService loginService;

	@GetMapping("/")
	public String getLoginPage(Model model, HttpSession session) {
		if (sessionManagerService.isValidSession(session)) {
			return "redirect:/home";
		}
		addToModel(model, "loginForm", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model, HttpSession session) {
		if (loginService.isValidLoginData(loginForm)) {
			loginForm.setPassword(null);
			sessionManagerService.setSessionAttribute(session, USERNAME_SESSION_ATTRIBUTE, loginForm.getUsername());
			removeFromModel(model, "errorMessage");
			return "redirect:/home";
		}
		addToModel(model, "errorMessage", "Invalid username or password");
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		sessionManagerService.invalidateSession(session);
		return "redirect:/";
	}

}
