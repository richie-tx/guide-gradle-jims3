/*
 * Created on Oct 14, 2004
 */
package pd.contact.user;

import java.util.Date;

/**
 * @stereotype control
 */
public class UserController
{

	/**
	 * @roseuid 42E67E830176
	 */
	public UserController()
	{

	}

	/**
	 * @stereotype design
	 * @param logonId
	 * @roseuid 42E65EA601EB
	 */
	public void getUserProfile(String logonId)
	{

	}

	/**
	 * @stereotype design
	 * @param suspendToDate
	 * @param activationToDate
	 * @param expirationDate
	 * @param expirationToDate
	 * @param logonId
	 * @param status
	 * @param ssn
	 * @param middleName
	 * @param lastName
	 * @param firstName
	 * @param dateOfBirth
	 * @param suspendDate
	 * @param activationDate
	 * @param divisionId
	 * @param agencyId
	 * @roseuid 4177B859030D
	 */
	public void getUserProfiles(
		Date suspendToDate,
		Date activationToDate,
		Date expirationDate,
		Date expirationToDate,
		String logonId,
		String status,
		String ssn,
		String middleName,
		String lastName,
		String firstName,
		Date dateOfBirth,
		Date suspendDate,
		Date activationDate,
		String divisionId,
		String agencyId)
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 43692833017D
	 */
	public void getUsers()
	{

	}

	/**
	 * @stereotype design
	 * @param logonId
	 * @param departmentId
	 * @param transferDate
	 * @param transferTime
	 * @roseuid 43EA4DC3032F
	 */
	public void transferUserProfile(
		String logonId,
		String departmentId,
		Date transferDate,
		String transferTime)
	{

	}

	/**
		* @stereotype design
		* @param logonId
		* @param departmentId
		* @roseuid 43EA4DC3032F
		*/
	public void processUserProfileTransfer(String logonId, String departmentId)
	{

	}

	/**
	 * @stereotype design
	 * @param departmentId
	 * @roseuid 43EA4DC4010A
	 */
	public void updateUserProfile(String departmentId)
	{

	}

	/**
	 * @stereotype design
	 * @param logonId
	 * @roseuid 43EA4DCE017E
	 */
	public void getUserProfileHistory(String logonId)
	{

	}

	/**
	 * @stereotype design
	 * @param genericLogonInd
	 * @param departmentId
	 * @param phone
	 * @param dateOfBirth
	 * @param lastName
	 * @param firstName
	 * @roseuid 43EA4DD6028E
	 */
	public void validateUserCreateUpdateRequirements(
		int genericLogonInd,
		String departmentId,
		String phone,
		Date dateOfBirth,
		String lastName,
		String firstName)
	{

	}

	/**
	 * @stereotype design
	 * @param ssn
	 * @param departmentId
	 * @param dateOfBirth
	 * @param middleName
	 * @param lastName
	 * @param firstName
	 * @roseuid 43EA4DD801B5
	 */
	public void saveNewUserProfile(
		String ssn,
		String departmentId,
		Date dateOfBirth,
		String middleName,
		String lastName,
		String firstName)
	{

	}

	/**
	 * @stereotype design
	 * @param passwordMinDays
	 * @param passwordMaxDays
	 * @roseuid 43EA4DE103E9
	 */
	public void validateUserUpdateRequirements(String passwordMinDays, String passwordMaxDays)
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 43EA4DE4017E
	 */
	public void validateUserDeleteRequirements()
	{

	}

	/**
	 * @stereotype design
	 * @param logonId
	 * @roseuid 43EA4DE60004
	 */
	public void deleteUserProfile(String logonId)
	{

	}

	/**
	* @stereotype design
	* @param logonId
	* @roseuid 43EA4DE60004
	*/
	public void getUserProfilesByUserAttributeAndLogonId(String logonId)
	{

	}
	
	/**
	* @stereotype design
	* @param lastName
	* @param middleName
	* @param firstName
	* @roseuid 43EA4DE60004
	*/
	public void getMatchingUserProfiles(String lastName, String middleName, String firstName)
	{

	}
	/**
	* @stereotype design
	* @param departmentId
	* @roseuid 43EA4DE60004
	*/
	public void getAvailableGenericLogonIds(String departmentId)
	{

	}
	
	/**
	* @stereotype design
	* @param userId
	*/	
	public void getUser(String userId)
	{
	}
}
