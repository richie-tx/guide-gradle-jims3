//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SearchJuvenileCasefilesEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;
import mojo.km.persistence.AttributeEvent;

public class SearchJuvenileCasefilesEvent extends RequestEvent 
{
   public String searchType;
   public String firstName;
   public String middleName;
   public String lastName;
   public String officerFirstName;
   public String officerMiddleName;
   public String officerLastName;
   public String juvenileNum;
   public String supervisionNum;
   public String supervisionType;
   public String caseStatus;
   public String location;
   public String zipCode; //#32659 changes

   private String attributeName;
   private String attributeValue;
   private String casefileExpectedEndDateFrom;
   private String casefileExpectedEndDateTo;
   private String casefileDispositionDateFrom; // Task 50044 changes
   private String casefileDispositionDateTo; // Task 50044 changes
   /**
	* @roseuid 4278C831024E
	*/
   public SearchJuvenileCasefilesEvent() 
   {    
   }
   
   /**
	* @param type
	* @roseuid 4278C7B900ED
	*/
   public void setSearchType(String aSearchType) 
   {
		this.searchType = aSearchType;
   }
   
   /**
	* @return String
	* @roseuid 4278C7B900F8
	*/
   public String getSearchType() 
   {
	return this.searchType;
   }
   
   /**
	* @param firstName
	* @roseuid 4278C7B9010B
	*/
   public void setFirstName(String aFirstName) 
   {
		this.firstName = aFirstName;
   }
   
   /**
	* @return String
	* @roseuid 4278C7B90115
	*/
   public String getFirstName() 
   {
	return this.firstName;
   }
   
   /**
	* @param middleName
	* @roseuid 4278C7B9011F
	*/
   public void setMiddleName(String aMiddleName) 
   {
	this.middleName = aMiddleName;
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
	* @param lastName
	* @roseuid 4278C7B9013D
	*/
   public void setLastName(String aLastName) 
   {
	this.lastName = aLastName;
   }
   
   /**
	* @return String
	* @roseuid 4278C7B90147
	*/
   public String getLastName() 
   {
	return this.lastName;
   }
   
   /**
	* @param aOfficerfirstName
	* @roseuid 4278C7B9010B
	*/
   public void setOfficerFirstName(String aOfficerfirstName)
   {
		this.officerFirstName = aOfficerfirstName;
   }
   
   /**
	* @return String
	* @roseuid 4278C7B90115
	*/
   public String getOfficerFirstName() 
   {
	return this.officerFirstName;
   }
   
   /**
	* @param aOfficerMiddleName
	* @roseuid 4278C7B9011F
	*/
   public void setOfficerMiddleName(String aOfficerMiddleName)
   {
	this.officerMiddleName = aOfficerMiddleName;
   }
   
   /**
	* @return String
	* @roseuid 4278C7B9012A
	*/
   public String getOfficerMiddleName() 
   {
	return this.officerMiddleName;
   }
   
   /**
	* @param aOfficerLastName
	* @roseuid 4278C7B9013D
	*/
   public void setOfficerLastName(String aOfficerLastName) 
   {
	this.officerLastName = aOfficerLastName;
   }
   
   /**
	* @return String
	* @roseuid 4278C7B90147
	*/
   public String getOfficerLastName() 
   {
	return this.officerLastName;
   }

   /**
	* @param juvenileNum
	* @roseuid 4278C7B9015B
	*/
   public void setJuvenileNum(String aJuvenileNum) 
   {
		this.juvenileNum = aJuvenileNum;
   }
   
   /**
	* @roseuid 4278C7B9015D
	*/
   public String getJuvenileNum() 
   {
		return this.juvenileNum;
   }
   
   /**
	* @param supervisionNum
	* @roseuid 4278C7B90165
	*/
   public void setSupervisionNum(String aSupervisionNum) 
   {
	this.supervisionNum = aSupervisionNum;
   }
   
   /**
	* @return String
	* @roseuid 4278C7B90170
	*/
   public String getSupervisionNum() 
   {
	return this.supervisionNum;
   }
   
   /**
	* @param supervisionType
	* @roseuid 4278C7B90179
	*/
   public void setSupervisionType(String aSupervisionType) 
   {
	this.supervisionType = aSupervisionType;
   }
   
   /**
	* @roseuid 4278C7B90183
	*/
   public String getSupervisionType() 
   {
	return this.supervisionType;
   }
   
   /**
	* @param status
	* @roseuid 4278C7B9018D
	*/
   public void setCaseStatus(String aCaseStatus) 
   {
	this.caseStatus = aCaseStatus;
   }
   
   /**
	* @roseuid 4278C7B9018F
	*/
   public String getCaseStatus() 
   {
	return this.caseStatus;
   }
   
   /**
    *  @param String    
    */
  public void setLocation(String alocation)
  {
   this.location = alocation;
  }

  /**
   * @return location
   */
  public String getLocation()
  {
   return this.location;
  }
	/**
	 * @return Returns the attributeName.
	 */
	public String getAttributeName() {
		return attributeName;
	}
	/**
	 * @param attributeName The attributeName to set.
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	/**
	 * @return Returns the attributeValue.
	 */
	public String getAttributeValue() {
		return attributeValue;
	}
	/**
	 * @param attributeValue The attributeValue to set.
	 */
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	/**
	 * @return the casefileExpectedEndDateFrom
	 */
	public String getCasefileExpectedEndDateFrom() {
		return casefileExpectedEndDateFrom;
	}

	/**
	 * @param casefileExpectedEndDateFrom the casefileExpectedEndDateFrom to set
	 */
	public void setCasefileExpectedEndDateFrom(String casefileExpectedEndDateFrom) {
		this.casefileExpectedEndDateFrom = casefileExpectedEndDateFrom;
	}

	/**
	 * @return the casefileExpectedEndDateTo
	 */
	public String getCasefileExpectedEndDateTo() {
		return casefileExpectedEndDateTo;
	}

	/**
	 * @param casefileExpectedEndDateTo the casefileExpectedEndDateTo to set
	 */
	public void setCasefileExpectedEndDateTo(String casefileExpectedEndDateTo) {
		this.casefileExpectedEndDateTo = casefileExpectedEndDateTo;
	}
	   /**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCasefileDispositionDateFrom() {
	    return casefileDispositionDateFrom;
	}

	public void setCasefileDispositionDateFrom(String casefileDispositionDateFrom) {
	    this.casefileDispositionDateFrom = casefileDispositionDateFrom;
	}

	public String getCasefileDispositionDateTo() {
	    return casefileDispositionDateTo;
	}

	public void setCasefileDispositionDateTo(String casefileDispositionDateTo) {
	    this.casefileDispositionDateTo = casefileDispositionDateTo;
	}

	
}
