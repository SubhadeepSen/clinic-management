package dr.sens.dental.clinic.servcies;

import static dr.sens.dental.clinic.constants.ClinicManagementConstants.SessionAttributes.INVOICE_FORM;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.SessionAttributes.PATIENT_FORM;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.SessionAttributes.USERNAME;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SessionManagerService {

	public boolean isValidSession(HttpSession session) {
		return StringUtils.hasLength((String) session.getAttribute(USERNAME));
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
		session.removeAttribute(USERNAME);
		clearSessionData(session);
		session.invalidate();
	}

	public void clearSessionData(HttpSession session) {
		session.removeAttribute(INVOICE_FORM);
		session.removeAttribute(PATIENT_FORM);
	}

}
