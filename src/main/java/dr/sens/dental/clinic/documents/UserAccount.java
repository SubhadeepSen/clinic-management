package dr.sens.dental.clinic.documents;

import static dr.sens.dental.clinic.utils.DentalClinicUtils.getUniqueId;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Document(collection = "userAccount")
public class UserAccount {

	@Id
	private String id;

	@Indexed(unique = true)
	@NonNull
	private String username;

	@NonNull
	private String password;

	public UserAccount(String username, String password) {
		this.id = getUniqueId();
		this.username = username;
		setPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = DigestUtils.sha256Hex(password);
	}
}
