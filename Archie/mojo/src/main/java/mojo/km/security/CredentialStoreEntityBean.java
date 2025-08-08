package mojo.km.security;
/**
 * U.S 79250
 * Added to integrate with the security manager.Bean associated to userAuthenticateResponse object.
 * @author sthyagarajan
 *
 */
public class CredentialStoreEntityBean {

	private int CredentialtypeID;
	private String CredentialtypeDescription;
	private String CredentialtypeValue;

	public int getCredentialtypeID() {
		return CredentialtypeID;
	}

	public void setCredentialtypeID(int credentialtypeID) {
		CredentialtypeID = credentialtypeID;
	}

	public String getCredentialtypeDescription() {
		return CredentialtypeDescription;
	}

	public void setCredentialtypeDescription(String credentialtypeDescription) {
		CredentialtypeDescription = credentialtypeDescription;
	}

	public String getCredentialtypeValue() {
		return CredentialtypeValue;
	}

	public void setCredentialtypeValue(String credentialtypeValue) {
		CredentialtypeValue = credentialtypeValue;
	}

}
