//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SearchJuvenileCasefilesEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SearchJuvenileCasefileByTypesEvent extends RequestEvent
{
	public String supervisionTypeId;
	public String caseStatusId;
	public String firstName;
	public String lastName;
	public String middleName;
		

	/**
	 * @roseuid 4278C831024E
	 */
	public SearchJuvenileCasefileByTypesEvent()
	{
	}

	/**
	 * @param supervisionType
	 * @roseuid 4278C7B9010B
	 */
	public void setSupervisionTypeId(String aSupervisionType)
	{
		this.supervisionTypeId = aSupervisionType;
	}

	/**
	 * @return String
	 * @roseuid 4278C7B90115
	 */
	public String getSupervisionTypeId()
	{
		return this.supervisionTypeId;
	}

	/**
	 * @param caseStatus
	 * @roseuid 4278C7B9011F
	 */
	public void setCaseStatusId(String aCaseStatus)
	{
		this.caseStatusId = aCaseStatus;
	}

	/**
	 * @return String
	 * @roseuid 4278C7B9012A
	 */
	public String getCaseStatusId()
	{
		return this.caseStatusId;
	}
	/**
	 * @param String
	 *
	 */
	public void setLastName(String aLastName)
	{
		this.lastName=aLastName;
		
	}
	
	/**
	 * @param String
	 *
	 */
		public void setFirstName(String aFirstName)
		{
			this.firstName=aFirstName;
		
		}
		
	/**
	 * @param String
	 *
	 */
		public void setMiddleName(String aMiddleName)
		{
			this.middleName=aMiddleName;
		
		}
		
	/**
	 * @param String
	 *
	 */
	
		
	/**
	 * @return String
	 * @roseuid 4278C7B9012A
	 */
		public String getFirstName()
		{
			return this.firstName;
		}
		
	/**
	 * @return String
	 * @roseuid 4278C7B9012A
	 */
		public String getLastName()
		{
			return this.lastName;
		}
		
	/**
	 * @return String
	 * @roseuid 4278C7B9012A
	 */
		public String getMiddleName()
		{
			return this.middleName;
		}
		
	/**
	 * @return String
	 * @roseuid 4278C7B9012A
	 */
		
}
