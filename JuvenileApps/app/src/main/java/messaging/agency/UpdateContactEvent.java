//Source file: C:\\views\\dev\\app\\src\\messaging\\agency\\UpdateContactEvent.java

package messaging.agency;

import mojo.km.messaging.RequestEvent;

public class UpdateContactEvent extends RequestEvent
{
	private String contactId;
	private String departmentId;
	private String email;
	private String firstName;
	private String lastName;
	private String liaisonTrainingInd;
	private String liaisonTraining;
	private String logonId;
	private String middleName;
	private String phone;
	private String phoneExt;
	private String primaryContact;
	private String title;
	private String areaCode = "";
	private String prefix = "";
	private String last4Digit = "";
	private String contactTypeId;
	
	private boolean deletable=false;

	/**
	 * @roseuid 43063787014B
	 */
	public UpdateContactEvent()
	{

	}

	/**
	 * Access method for the contactId property.
	 * 
	 * @return   the current value of the contactId property
	 */
	public String getContactId()
	{
		return contactId;
	}

	/**
	 * Access method for the departmentId property.
	 * 
	 * @return   the current value of the departmentId property
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * Access method for the email property.
	 * 
	 * @return   the current value of the email property
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Access method for the firstName property.
	 * 
	 * @return   the current value of the firstName property
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Access method for the lastName property.
	 * 
	 * @return   the current value of the lastName property
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Access method for the liaisonTrainingInd property.
	 * 
	 * @return   the current value of the liaisonTrainingInd property
	 */
	public String getLiaisonTrainingInd()
	{
		return liaisonTrainingInd;
	}
	
	/**
	 * 
	 * @return the current value of the liaisonTrainingInd property as yes or no
	 */
	public String getLiaisonTrainingIndAsYesNo()
					{
						if(liaisonTrainingInd==null)
							return "";
						if(liaisonTrainingInd.equalsIgnoreCase("Y"))
							return "YES";
						else
							return "NO";
					}

	/**
	 * Access method for the logonId property.
	 * 
	 * @return   the current value of the logonId property
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * Access method for the middleName property.
	 * 
	 * @return   the current value of the middleName property
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * Access method for the phone property.
	 * 
	 * @return   the current value of the phone property
	 */
	public String getPhone()
	{
		if(this.phone != null && !(this.phone.trim().equals(""))){
		   return this.phone;
		}else{
			return this.areaCode + this.prefix + this.last4Digit;
		}
	}

	/**
	 * Access method for the phoneExt property.
	 * 
	 * @return   the current value of the phoneExt property
	 */
	public String getPhoneExt()
	{
		return phoneExt;
	}

	/**
	 * Access method for the title property.
	 * 
	 * @return   the current value of the title property
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Sets the value of the contactId property.
	 * 
	 * @param aContactId the new value of the contactId property
	 */
	public void setContactId(String aContactId)
	{
		contactId = aContactId;
	}

	/**
	 * Sets the value of the departmentId property.
	 * 
	 * @param aDepartmentId the new value of the departmentId property
	 */
	public void setDepartmentId(String aDepartmentId)
	{
		departmentId = aDepartmentId;
	}

	/**
	 * Sets the value of the email property.
	 * 
	 * @param aEmail the new value of the email property
	 */
	public void setEmail(String aEmail)
	{
		email = aEmail;
	}

	/**
	 * Sets the value of the firstName property.
	 * 
	 * @param aFirstName the new value of the firstName property
	 */
	public void setFirstName(String aFirstName)
	{
		firstName = aFirstName;
	}

	/**
	 * Sets the value of the lastName property.
	 * 
	 * @param aLastName the new value of the lastName property
	 */
	public void setLastName(String aLastName)
	{
		lastName = aLastName;
	}

	/**
	 * Sets the value of the liaisonTrainingInd property.
	 * 
	 * @param aLiaisonTrainingInd the new value of the liaisonTrainingInd property
	 */
	public void setLiaisonTrainingInd(String aLiaisonTrainingInd)
	{
		liaisonTrainingInd = aLiaisonTrainingInd;
	}

	/**
	 * Sets the value of the logonId property.
	 * 
	 * @param aLogonId the new value of the logonId property
	 */
	public void setLogonId(String aLogonId)
	{
		logonId = aLogonId;
	}

	/**
	 * Sets the value of the middleName property.
	 * 
	 * @param aMiddleName the new value of the middleName property
	 */
	public void setMiddleName(String aMiddleName)
	{
		middleName = aMiddleName;
	}

	/**
		 * Sets the value of the phone property.
		 * 
		 * @param aPhone the new value of the phone property
		 */
	public String getFormattedPhone()
	{
		StringBuffer phNumber = new StringBuffer();
		phNumber = phNumber.append(this.areaCode).append("-").append(this.prefix).append("-").append(this.last4Digit);
		String ph = phNumber.toString();
		if (ph.equals("--") || ph.equalsIgnoreCase("null-null-null"))
		{
			return ""; 
		}
		return ph;
	}


	/**
	 * Sets the value of the phone property.
	 * 
	 * @param aPhone the new value of the phone property
	 */
	public void setPhone(String aPhone)
	{
		phone = aPhone;
	}

	/**
	 * Sets the value of the phoneExt property.
	 * 
	 * @param aPhoneExt the new value of the phoneExt property
	 */
	public void setPhoneExt(String aPhoneExt)
	{
		phoneExt = aPhoneExt;
	}

	/**
	 * Sets the value of the title property.
	 * 
	 * @param aTitle the new value of the title property
	 */
	public void setTitle(String aTitle)
	{
		title = aTitle;
	}
	/**
	 * @return
	 */
	public boolean isDeletable()
	{
		return deletable;
	}

	/**
	 * @param b
	 */
	public void setDeletable(boolean b)
	{
		deletable = b;
	}

	/**
	 * @return
	 */
	public String getAreaCode()
	{
		return areaCode;
	}

	/**
	 * @return
	 */
	public String getLast4Digit()
	{
		return last4Digit;
	}

	/**
	 * @return
	 */
	public String getPrefix()
	{
		return prefix;
	}

	/**
	 * @param string
	 */
	public void setAreaCode(String string)
	{
		areaCode = string;
	}

	/**
	 * @param string
	 */
	public void setLast4Digit(String string)
	{
		last4Digit = string;
	}

	/**
	 * @param string
	 */
	public void setPrefix(String string)
	{
		prefix = string;
	}

	/**
	 * @return
	 */
	public String getLiaisonTraining()
	{
		if(liaisonTrainingInd==null)
			return "";
		if(liaisonTrainingInd.equalsIgnoreCase("N") || liaisonTrainingInd.equalsIgnoreCase("NO"))
			setLiaisonTraining("NO");
		else if (liaisonTrainingInd.equalsIgnoreCase("Y") || liaisonTrainingInd.equalsIgnoreCase("YES"))
			setLiaisonTraining("YES");
		else
			return "";
		return liaisonTraining;
	}

	/**
	 * @param string
	 */
	public void setLiaisonTraining(String string)
	{
		liaisonTraining = string;
	}

	/**
	 * @return
	 */
	public String getContactTypeId()
	{
		return contactTypeId;
	}

	/**
	 * @param string
	 */
	public void setContactTypeId(String string)
	{
		contactTypeId = string;
	}

	/**
	 * @return
	 */
	public String getPrimaryContact()
	{
		return primaryContact;
	}

	/**
	 * @param string
	 */
	public void setPrimaryContact(String string)
	{
		primaryContact = string;
	}
	
	public String getPrimaryContactAsYesNo()
				{
					if(primaryContact==null)
						return "";
					if(primaryContact.equalsIgnoreCase("Y"))
						return "YES";
					else
						return "NO";
				}


}