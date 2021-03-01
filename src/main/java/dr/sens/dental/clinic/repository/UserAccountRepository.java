package dr.sens.dental.clinic.repository;

import dr.sens.dental.clinic.documents.UserAccount;

public interface UserAccountRepository {

	public boolean isUserAccountExist(UserAccount userAccount);
}
