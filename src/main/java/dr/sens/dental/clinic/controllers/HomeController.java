package dr.sens.dental.clinic.controllers;

import static dr.sens.dental.clinic.constants.ClinicManagementConstants.PathMapping.REDIRECT_TO_LOGIN;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.Views.HOME_PAGE;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import dr.sens.dental.clinic.services.SessionManagerService;

@Controller
public class HomeController {

	@Autowired
	private SessionManagerService sessionManagerService;

	@GetMapping("/home")
	public String getHomePage(HttpSession session) {
		if (!sessionManagerService.isValidSession(session)) {
			return REDIRECT_TO_LOGIN;
		}
		sessionManagerService.clearSessionData(session);
		return HOME_PAGE;
	}
}
