//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\officer\\OfficerProfileController.java

package pd.contact.officer;

import java.util.Collection;

/**
 * @stereotype control
 */
public class OfficerProfileController
{

	/**
	 * @roseuid 42E67D3B0060
	 */
	public OfficerProfileController()
	{

	}
	
	/**
	 * @stereotype design
	 */
	public void searchJuvenileOfficer()
	{	    
	}

	/**
	 * @stereotype design
	 * @param otherIdNum
	 * @param badgeNum
	 * @param departmentId
	 * @param logonId
	 * @roseuid 42E65EA60016
	 */
	public void validateOfficerProfile(String otherIdNum, String badgeNum, String departmentId, String logonId)
	{

	}

	/**
	 * @stereotype design
	 * @param officerProfileId
	 * @param streetType
	 * @param zipCode
	 * @param streetNum
	 * @param streetName
	 * @param state
	 * @param city
	 * @param apartmentNum
	 * @param officerTypeId
	 * @param managerLastName
	 * @param managerMiddleName
	 * @param managerId
	 * @param manageFirstName
	 * @param divisionName
	 * @param departmentId
	 * @param juvUnit
	 * @param juvLocation
	 * @param middleName
	 * @param lastName
	 * @param firstName
	 * @param status
	 * @param logonId
	 * @param officerRadioNum
	 * @param badgeNum
	 * @param email
	 * @param phone
	 * @param workPhone
	 * @param cellPhone
	 * @param workShift
	 * @param ssn
	 * @param rank
	 * @param pager
	 * @param payrollNum
	 * @param faxLocation
	 * @param fax
	 * @param assignedArea
	 * @roseuid 42E65EA7010F
	 */
	public void updateOfficerProfile(
		String officerProfileId,
		String streetType,
		String zipCode,
		String streetNum,
		String streetName,
		String state,
		String city,
		String apartmentNum,
		String officerTypeId,
		String managerLastName,
		String managerMiddleName,
		String managerId,
		String manageFirstName,
		String divisionName,
		String departmentId,
		String juvUnit,
		String juvLocation,
		String middleName,
		String lastName,
		String firstName,
		String status,
		String logonId,
		String officerRadioNum,
		String badgeNum,
		String email,
		String phone,
		String workPhone,
		String cellPhone,
		String workShift,
		String ssn,
		String rank,
		String pager,
		String payrollNum,
		String faxLocation,
		String fax,
		String assignedArea)
	{

	}

	/**
	 * @stereotype design
	 * @param officerTypeId
	 * @param payrollNum
	 * @param badgeNum
	 * @param departmentName
	 * @param logonId
	 * @param firstName
	 * @param lastName
	 * @roseuid 42E65EA702AA
	 */
	public void getOfficerProfiles(
		String officerTypeId,
		String payrollNum,
		String badgeNum,
		String departmentName,
		String logonId,
		String firstName,
		String lastName)
	{

	}

	/**
	 * @stereotype design
	 * @param payrollNum
	 * @param badgeNum
	 * @param departmentId
	 * @roseuid 42E65EA702AA
	 */
	public void getOfficerProfileById(
		String payrollNum,
		String badgeNum,
		String departmentId)
	{

	}

	/**
	 * @stereotype design
	 * @param officerProfileId
	 * @roseuid 42E65EA703A2
	 */
	public void getOfficerProfile(String officerProfileId)
	{

	}
	
	/**
	 * @stereotype design
	 * @param officerProfileId
	 * @roseuid 42E65EA703A2
	 */
	public void getOfficerProfileByLogonId(String userId)
	{

	}

	/**
	 * @stereotype design
	 * @param officerProfileId
	 * @roseuid 42E65EA80038
	 */
	public void deleteOfficerProfile(String officerProfileId)
	{

	}

	/**
	* @stereotype design
	* @param logonId
	* @param officerId
	* @param officerIdType
	* @param departmentId
	* @roseuid 42E65EA80038
	*/
	public void validateOfficerOrUser(String logonId, String officerId, String officerIdType, String departmentId)
	{
	}
	
	/**
	* @stereotype design
	* @param officerId
	* @param officerIdType
	* @param departmentId
	*/	
	public void validateOfficer(String officerId, String officerIdType, String departmentId)
	{
	}
	
	/**
	* @stereotype design
	* @param managerId
	*/	
	public void getOfficerProfilesByManager(String managerId)
	{
	}
	
	/**
	* @stereotype design
	* @param attributeName
	* @param atrributeValue
	*/	
	public void getOfficerProfilesByAttribute(String attributeName,String atrributeValue)
	{
	}
	
	/** 
	 * @stereotype design
	 */
	public void updateOfficerProfiles(Collection officerProfiles) {
	}
	
	/**
	* @stereotype design
	* @param attributeName
	* @param atrributeValue
	*/	
	public void getOfficerProfileFromUserGroup(String userGroupId){
		
	}
	

	/** 
	 * @stereotype design
	 */
	
	public void SearchOfficerProfilesByName(String firstName, String middleName, String lastName){}
	
	/** 
	 * @stereotype design
	 */	
	public void getAllTrainingTopics(){}
	
	/** 
	 * @stereotype design
	 */	
	public void getOfficerTraining(String officerProfileId){}
	
	/** 
	 * @stereotype design
	 */
	public void updateOfficerTraining(String officerProfileId){
	    
	}

}
