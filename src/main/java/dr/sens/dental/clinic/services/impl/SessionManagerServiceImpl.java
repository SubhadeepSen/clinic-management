package dr.sens.dental.clinic.services.impl;

import static dr.sens.dental.clinic.constants.ClinicManagementConstants.SessionAttributes.INVOICE_FORM;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.SessionAttributes.PATIENT_FORM;
import static dr.sens.dental.clinic.constants.ClinicManagementConstants.SessionAttributes.USERNAME;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import dr.sens.dental.clinic.services.SessionManagerService;

@Service
public class SessionManagerServiceImpl implements SessionManagerService {

	@Override
	public boolean isValidSession(HttpSession session) {
		return StringUtils.hasLength((String) session.getAttribute(USERNAME));
	}

	@Override
	public void setSessionAttribute(HttpSession session, String key, Object value) {
		session.setAttribute(key, value);
	}

	@Override
	public Object getSessionAttribute(HttpSession session, String key) {
		return session.getAttribute(key);
	}

	@Override
	public void removeSessionAttribute(HttpSession session, String key) {
		session.removeAttribute(key);
	}

	@Override
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
