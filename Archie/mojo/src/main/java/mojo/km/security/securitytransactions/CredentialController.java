package mojo.km.security.securitytransactions;

/**
 * @eventPackage mojo.messaging.securitytransactionsevents
 * @stereotype control
 * @serverName LoginController
 * @modelguid {802797DE-5C3B-418D-BFA6-826FB9B4FDD2}
 */
public class CredentialController {
	/** 
	 * @stereotype design 
	 * @commandList mojo.km.security.securitytransactions.LoginCommand
	 * @modelguid {A4D5D4C1-1749-484A-916A-8D7FC2C703E8}
	 */
	public void login(String username, String password) { }
	/** 
	 * @stereotype design	 
	 * @commandList mojo.km.security.securitytransactions.UpdateACF2PasswordCommand
	*/
	public void updateACF2Password(String username, String password, String newPassword) { }
}
