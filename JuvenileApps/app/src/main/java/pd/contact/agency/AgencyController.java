//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\agency\\AgencyController.java

package pd.contact.agency;

import java.util.Date;

/**
 * @stereotype control
 */
public class AgencyController
{

	/**
	 * @roseuid 42E67E670159
	 */
	public AgencyController()
	{

	}

	/**
	 * @stereotype design
	 * @param agencyId
	 * @roseuid 42C58F38027C
	 */
	public void deleteAgency(String agencyId)
	{

	}

	/**
	 * @stereotype design
	 * @param departmentId
	 * @param contactId
	 * @roseuid 4306266801D8
	 */
	public void deleteContact(String departmentId, int contactId)
	{

	}

	/**
	 * @stereotype design
	 * @param departmentId
	 * @roseuid 4306266D00AD
	 */
	public void deleteDepartment(String departmentId)
	{

	}

	/**
	 * @stereotype design
	 * @param projectAnalystInd
	 * @param agencyType
	 * @param agencyName
	 * @param agencyId
	 * @roseuid 42C58F3601D4
	 */
	public void getAgencies(String projectAnalystInd, String agencyType, String agencyName, String agencyId)
	{

	}

	/**
	  * @stereotype design
	  * @roseuid 425551F800AB
	  */
	public void getAgenciesByJmcRep(String jmcRepInd)
	{
	}

	/**
	   * @stereotype design
	   * @param agencyId
	   * @roseuid 4177B89D02A2
	   */
	  public void getAgency(String agencyId)
	  {
	  }	

	/**
	 * @stereotype design
	 * @param departmentId
	 * @roseuid 42E65EA60160
	 */
	public void getDepartment(String departmentId)
	{

	}

	/**
	 * @stereotype design
	 * @param departmentName
	 * @param departmentId
	 * @roseuid 42E65EA500F2
	 */
	public void getDepartments(String departmentName, String departmentId)
	{

	}

	/**
     * @stereotype design
     * @roseuid 41E819270290
     */
	public void getDepartmentsForASA(String agencyId, String logonId)
	{
	}

	/**
	 * @stereotype design
	 * @roseuid 41E8062C02A4
	 */
	public void getLawEnforcementDepartments()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 41E8062C02A4
	 */	
	public void getAllAgencies() {
		
	}
	
	/**
	 * @param departmentid
	 * @stereotype design
	 * @roseuid 41E8062C02A4
	 */
	public void getDepartmentContacts(String departmentId)
	{

	}
	

	/**
	 * @stereotype design
	 * @param projectAnalystInd
	 * @param orgCode
	 * @param agencyType
	 * @param agencyName
	 * @param agencyId
	 * @roseuid 42C58F370369
	 */
	public void updateAgency(
		String projectAnalystInd,
		String orgCode,
		String agencyType,
		String agencyName,
		String agencyId)
	{

	}

	/**
	 * @stereotype design
	 * @param contactId
	 * @param departmentId
	 * @param liaisonTrainingInd
	 * @param email
	 * @param logonId
	 * @param title
	 * @param phoneExt
	 * @param phone
	 * @param middleName
	 * @param lastName
	 * @param firstName
	 * @roseuid 4306266C02ED
	 */
	public void updateContact(
		int contactId,
		String departmentId,
		String liaisonTrainingInd,
		String email,
		String logonId,
		String title,
		String phoneExt,
		String phone,
		String middleName,
		String lastName,
		String firstName)
	{

	}

	/**
	 * @stereotype design
	 * @param county
	 * @param agencyId
	 * @param warrantConfirmationPhoneExt
	 * @param warrantConfirmationPhone
	 * @param subscriberCriminalTerminationDate
	 * @param subscriberCriminalActivationDate
	 * @param subscriberCivilTerminationDate
	 * @param subscriberCivilActivationDate
	 * @param status
	 * @param setcicRenewDate
	 * @param setcicInactiveDate
	 * @param setcicDate
	 * @param setcicAccessInd
	 * @param originatingAgencyId
	 * @param orgCode
	 * @param labelInd
	 * @param inactiveDate
	 * @param activationDate
	 * @param gritsAccessInd
	 * @param fax
	 * @param comments
	 * @param departmentName
	 * @param accessType
	 * @param departmentId
	 * @roseuid 4306266B0251
	 */
	public void updateDepartment(
		String county,
		String agencyId,
		String warrantConfirmationPhoneExt,
		String warrantConfirmationPhone,
		Date subscriberCriminalTerminationDate,
		Date subscriberCriminalActivationDate,
		Date subscriberCivilTerminationDate,
		Date subscriberCivilActivationDate,
		String status,
		Date setcicRenewDate,
		Date setcicInactiveDate,
		Date setcicDate,
		String setcicAccessInd,
		String originatingAgencyId,
		String orgCode,
		String labelInd,
		Date inactiveDate,
		Date activationDate,
		String gritsAccessInd,
		String fax,
		String comments,
		String departmentName,
		String accessType,
		String departmentId)
	{

	}

	/**
	 * @stereotype design
	 * @param orgCode
	 * @param agencyName
	 * @param agencyId
	 * @roseuid 42C58F370184
	 */
	public void validateAgencyCreateRequirements(String orgCode, String agencyName, String agencyId)
	{

	}

	/**
	 * @stereotype design
	 * @param agencyId
	 * @roseuid 42C58F380195
	 */
	public void validateAgencyDeleteRequirements(String agencyId)
	{

	}

	/**
	 * @stereotype design
	 * @param agencyName
	 * @roseuid 42C58F38004E
	 */
	public void validateAgencyUpdateRequirements(String agencyName)
	{

	}

	/**
		 * @stereotype design
		 * @param orgCode
		 * @param departmentName
		 * @param agencyId
		 * @param departmentId
		 * @roseuid 4306266B004B
		 */
		public void validateDepartmentCreateRequirements(
			String orgCode,
			String departmentName,
			String agencyId,
			String departmentId)
		{

		}
		
	/**
		 * @stereotype design
		 * @param orgCode
		 * @param departmentName
		 * @param agencyId
		 * @param departmentId
		 * @roseuid 4306266B004B
		 */
		public void validateDepartmentUpdateRequirements(
			String orgCode,
			String departmentName,
			String agencyId,
			String departmentId)
		{

		}
		
	/**
	 * @stereotype design
	 * @param logonId
	 * @param departmentName
	 * @param departmentId
	 * @roseuid 41E819270290
	 */
	public void getASADepartments(String logonId, String departmentId, String departmentName)
	{
	}
	
	/**
	 * @stereotype design
	 * @param projectAnalystInd
	 * @param agencyType
	 * @param agencyName
	 * @param agencyId
	 * @roseuid 42C58F3601D4
	 */
	public void getAgenciesDepartments(String projectAnalystInd, String agencyType, String agencyName, String agencyId)
	{

	}
	
	/**
	 * @stereotype design
	 * @param departmentName
	 * @param departmentId
	 * @roseuid 42C58F3601D4
	 */
	public void getDepartmentsAndAgency(String departmentId, String departmentName)
	{

	}
}