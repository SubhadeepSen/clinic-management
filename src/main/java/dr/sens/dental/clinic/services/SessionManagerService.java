package dr.sens.dental.clinic.services;

import javax.servlet.http.HttpSession;

public interface SessionManagerService {

	public boolean isValidSession(HttpSession session);

	public void setSessionAttribute(HttpSession session, String key, Object value);

	public Object getSessionAttribute(HttpSession session, String key);

	public void removeSessionAttribute(HttpSession session, String key);

	public void invalidateSession(HttpSession session);

	public void clearSessionData(HttpSession session);
}
