package pd.contact;

import mojo.km.persistence.PersistentObject;


/**
 * @author dgibler
 *
 */
public class Contact extends PersistentObject
{
	private String faxNum;
	private String homePhoneNum;
	private String workPhoneNum;
	private String pager;
	private String contactId;
	private String email;
	private String faxLocation;
	private String phoneExt;
	private String cellPhone;
	private String phoneNum;
	private String middleName;
	private String firstName;
	private String lastName;
	private String title;
	private String defendantName;
	
	/**
	* @roseuid 4107BF8403A9
	*/
	public Contact()
	{
	}
	/**
	* Access method for the cellPhone property.
	* @return the current value of the cellPhone property
	*/
	public String getCellPhone()
	{
		//fetch(); //87191
		return cellPhone;
	}
	/**
	* Sets the value of the cellPhone property.
	* @param aCellPhone the new value of the cellPhone property
	*/
	public void setCellPhone(String aCellPhone)
	{
		if (this.cellPhone == null || !this.cellPhone.equals(aCellPhone))
		{
		//	markModified();
		}
		cellPhone = aCellPhone;
	}
	/**
	* Access method for the contactId property.
	* @return the current value of the contactId property
	*/
	public String getContactId()
	{
	//	fetch();
		return contactId;
	}
	/**
	* Sets the value of the contactId property.
	* @param aContactId the new value of the contactId property
	*/
	public void setContactId(String aContactId)
	{
		if (this.contactId == null || !this.contactId.equals(aContactId))
		{
			//markModified();
		}
		contactId = aContactId;
	}
	/**
	* Access method for the email property.
	* @return the current value of the email property
	*/
	public String getEmail()
	{
		//fetch();
		return email;
	}
	/**
	* Sets the value of the email property.
	* @param aEmail the new value of the email property
	*/
	public void setEmail(String aEmail)
	{
		if (this.email == null || !this.email.equals(aEmail))
		{
			//markModified();
		}
		email = aEmail;
	}
	/**
	* Access method for the faxLocation property.
	* @return the current value of the faxLocation property
	*/
	public String getFaxLocation()
	{
		//fetch();
		return faxLocation;
	}
	/**
	* Sets the value of the faxLocation property.
	* @param aFaxLocation the new value of the faxLocation property
	*/
	public void setFaxLocation(String aFaxLocation)
	{
		if (this.faxLocation == null || !this.faxLocation.equals(aFaxLocation))
		{
			//markModified();
		}
		faxLocation = aFaxLocation;
	}
	/**
	* Access method for the faxNum property.
	* @return the current value of the faxNum property
	*/
	public String getFaxNum()
	{
		//fetch();
		return faxNum;
	}
	/**
	* Sets the value of the faxNum property.
	* @param aFaxNum the new value of the faxNum property
	*/
	public void setFaxNum(String aFaxNum)
	{
		if (this.faxNum == null || !this.faxNum.equals(aFaxNum))
		{
			//markModified();
		}
		faxNum = aFaxNum;
	}
	/**
	* Access method for the firstName property.
	* @return the current value of the firstName property
	*/
	public String getFirstName()
	{
	//	fetch();
		return firstName;
	}
	/**
	* Sets the value of the firstName property.
	* @param aFirstName the new value of the firstName property
	*/
	public void setFirstName(String aFirstName)
	{
		if (this.firstName == null || !this.firstName.equals(aFirstName))
		{
			//markModified();
		}
		firstName = aFirstName;
	}
	/**
	* Access method for the homePhoneNum property.
	* @return the current value of the homePhoneNum property
	*/
	public String getHomePhoneNum()
	{
		//fetch();
		return homePhoneNum;
	}
	/**
	* Sets the value of the homePhoneNum property.
	* @param aHomePhoneNum the new value of the homePhoneNum property
	*/
	public void setHomePhoneNum(String aHomePhoneNum)
	{
		if (this.homePhoneNum == null || !this.homePhoneNum.equals(aHomePhoneNum))
		{
			//markModified();
		}
		homePhoneNum = aHomePhoneNum;
	}
	/**
	* Access method for the lastName property.
	* @return the current value of the lastName property
	*/
	public String getLastName()
	{
		//fetch();
		return lastName;
	}
	/**
	* Sets the value of the lastName property.
	* @param aLastName the new value of the lastName property
	*/
	public void setLastName(String aLastName)
	{
		if (this.lastName == null || !this.lastName.equals(aLastName))
		{
			//markModified();
		}
		lastName = aLastName;
	}
	/**
	* Access method for the middleName property.
	* @return the current value of the middleName property
	*/
	public String getMiddleName()
	{
		//fetch();
		return middleName;
	}
	/**
	* Sets the value of the middleName property.
	* @param aMiddleName the new value of the middleName property
	*/
	public void setMiddleName(String aMiddleName)
	{
		if (this.middleName == null || !this.middleName.equals(aMiddleName))
		{
		//	markModified();
		}
		middleName = aMiddleName;
	}
	/**
	* Access method for the pager property.
	* @return the current value of the pager property
	*/
	public String getPager()
	{
		//fetch();
		return pager;
	}
	/**
	* Sets the value of the pager property.
	* @param aPager the new value of the pager property
	*/
	public void setPager(String aPager)
	{
		if (this.pager == null || !this.pager.equals(aPager))
		{
			//markModified();
		}
		pager = aPager;
	}
	/**
	* Access method for the phoneExt property.
	* @return the current value of the phoneExt property
	*/
	public String getPhoneExt()
	{
		//fetch();
		return phoneExt;
	}
	/**
	* Sets the value of the phoneExt property.
	* @param aPhoneExt the new value of the phoneExt property
	*/
	public void setPhoneExt(String aPhoneExt)
	{
		if (this.phoneExt == null || !this.phoneExt.equals(aPhoneExt))
		{
			//markModified();
		}
		phoneExt = aPhoneExt;
	}
	/**
	* Access method for the phoneNum property.
	* @return the current value of the phoneNum property
	*/
	public String getPhoneNum()
	{
		//fetch();
		return phoneNum;
	}
	/**
	* Sets the value of the phoneNum property.
	* @param aPhoneNum the new value of the phoneNum property
	*/
	public void setPhoneNum(String aPhoneNum)
	{
		if (this.phoneNum == null || !this.phoneNum.equals(aPhoneNum))
		{
			//markModified();
		}
		phoneNum = aPhoneNum;
	}
	/**
	* Access method for the title property.
	* @return the current value of the title property
	*/
	public String getTitle()
	{
		//fetch();
		return title;
	}
	/**
	* Sets the value of the title property.
	* @param aTitle the new value of the title property
	*/
	public void setTitle(String aTitle)
	{
		if (this.title == null || !this.title.equals(aTitle))
		{
			//markModified();
		}
		title = aTitle;
	}
	/**
	* Access method for the workPhoneNum property.
	* @return the current value of the workPhoneNum property
	*/
	public String getWorkPhoneNum()
	{
		//fetch();
		return workPhoneNum;
	}
	/**
	* Sets the value of the workPhoneNum property.
	* @param aWorkPhoneNum the new value of the workPhoneNum property
	*/
	public void setWorkPhoneNum(String aWorkPhoneNum)
	{
		if (this.workPhoneNum == null || !this.workPhoneNum.equals(aWorkPhoneNum))
		{
		//	markModified();
		}
		workPhoneNum = aWorkPhoneNum;
	}


	public String getFullNameWithLastNameFirst()
	{
	  String fullNameWithLastNameFirst = null ;
	  try
	  {
	  	fullNameWithLastNameFirst = this.lastName + ", " + this.firstName + " " + this.middleName ;  	
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ;
	  }
	  return fullNameWithLastNameFirst ; 
	}
	
	public void setFullNameWithLastNameFirst()
	{
		throw new UnsupportedOperationException() ; 
	}
	public String getDefendantName() {
		return defendantName;
	}
	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}
}
