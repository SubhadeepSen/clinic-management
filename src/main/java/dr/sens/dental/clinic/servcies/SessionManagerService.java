package dr.sens.dental.clinic.servcies;

import static dr.sens.dental.clinic.constants.DentalClinicConstants.USERNAME_SESSION_ATTRIBUTE;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SessionManagerService {

	public boolean isValidSession(HttpSession session) {
		return StringUtils.hasLength((String) session.getAttribute(USERNAME_SESSION_ATTRIBUTE));
	}

	public void setSessionAttribute(HttpSession session, String key, Object value) {
		session.setAttribute(key, value);
	}
	
	public Object getSessionAttribute(HttpSession session, String key) {
		return session.getAttribute(key);
	}
	
	public void removeSessionAttribute(HttpSession session, String key) {
		session.removeAttribute(key);
	}

	public void invalidateSession(HttpSession session) {
		session.removeAttribute(USERNAME_SESSION_ATTRIBUTE);
		clearSessionData(session);
		session.invalidate();
	}
	
	public void clearSessionData(HttpSession session) {
		session.removeAttribute("invoiceForm");
		session.removeAttribute("patientForm");
	}

}
