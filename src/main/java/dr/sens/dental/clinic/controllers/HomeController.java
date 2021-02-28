package dr.sens.dental.clinic.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import dr.sens.dental.clinic.servcies.SessionManagerService;

@Controller
public class HomeController {

	@Autowired
	private SessionManagerService sessionManagerService;

	@GetMapping("/home")
	public String getHomePage(HttpSession session) {
		if (!sessionManagerService.isValidSession(session)) {
			return "redirect:/";
		}
		sessionManagerService.clearSessionData(session);
		return "home";
	}
}
