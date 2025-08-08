//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\LoginController.java

package pd.security.authentication;

import messaging.authentication.ValidateJIMS2AccountByEmpIdAndLogonIdEvent;

/**
 * @stereotype control
 */
public class LogonController
{

	/**
	 * @roseuid 4399CCE501F9
	 */
	public LogonController()
	{

	}

	/**
	 * @stereotype design
	 * @param password
	 * @param logonId
	 * @roseuid 439711A800CC
	 */
	public void manageSession(String password, String logonId)
	{

	}

	/**
	 * @stereotype design
	 * @param password
	 * @param logonId
	 * @roseuid 439711A800CC
     */
	public void getJIMS2Account(String password, String logonId)
	{

	}
	
	/**
	 * @stereotype design
	 * @param password
	 * @param logonId
	 * @roseuid 439711A800CC
	 */
	public void getJIMS2AccountByJIMSLogonId(String logonId)
	{

	}

	/**
	 * @stereotype design
	 * @param employeeId
	 * @param serviceProviderId
	 * @roseuid 439711A80217
	 */
	public void validateServiceProviderSecurity(String employeeId, String serviceProviderId)
	{

	}

	/**
	 * @stereotype design
	 * @param passwordQuestion
	 * @param JIMS2Password
	 * @param JIMS2LogonId
	 * @param JIMS2AccountTypeOID
	 * @param JIMS2AccountTypeId
	 * @param answer
	 * @param logonId
	 * @roseuid 439711A8033B
	 */
	public void createJIMS2Account(
		String passwordQuestion,
		String JIMS2Password,
		String JIMS2LogonId,
		String JIMS2AccountTypeOID,
		String JIMS2AccountTypeId,
		String answer,
		String logonId)
	{

	}
	
	/**
	 * @stereotype design
	 * @param jims2AccountId
	 * @param jims2LogonId
	 * @param statusId
	 * @roseuid 439711A8033B
	 */
	public void updateJIMS2Account(
		String jims2AccountId,
		String jims2LogonId,
		String statusId)
	{

	}

	/**
	 * @stereotype design
	 * @param JIMS2LogonId
	 * @param JIMS2AccountTypeOID
	 * @param JIMS2AccountTypeId
	 * @param logonId
	 * @roseuid 439711A900BC
	 */
	public void validateJIMS2Account(
		String JIMS2LogonId,
		String JIMS2AccountTypeOID,
		String JIMS2AccountTypeId,
		String logonId)
	{

	}

	/**
	 * @stereotype design
	 * @param logonId
	 * @roseuid 439711A901EA
	 */
	public void validateUserProfile(String logonId)
	{

	}

	/**
	 * @stereotype design
	 * @param answer
	 * @param passwordQuestion
	 * @param JIMS2Password
	 * @param JIMS2LogonId
	 * @roseuid 4398426802D1
	 */
	public void updateJIMS2AccountPassword(
		String answer,
		String passwordQuestion,
		String JIMS2Password,
		String JIMS2LogonId)
	{

	}
	/**
	 * @stereotype design
	 * @param officerId
	 * @roseuid 4398426802D1
	 */
	public void getJIMS2AccountByOfficerId(String officerId)
	{

	}
	/**
	 * @stereotype design
	 * @param jims2LogonId
	 * @roseuid 439711A800CC
     */
	public void getJIMS2Accounts(String jims2LogonId)
	{

	}
	
	/**
	 * @stereotype design
	 * @param jims2LogonId
	 * @param userAccountId
	 * @roseuid 439711A800CC
     */
	public void validateJIMS2AccountByEmpIdAndLogonId(String jims2LogonId, String userAccountId)
	{

	}
	
//	/**
//	 * @stereotype design
//	 * @param password
//	 * @param logonId
//	 * @roseuid 439842680331
//	 */
//	public void updateACF2Password(String password, String logonId)
//	{
//
//	}
}
