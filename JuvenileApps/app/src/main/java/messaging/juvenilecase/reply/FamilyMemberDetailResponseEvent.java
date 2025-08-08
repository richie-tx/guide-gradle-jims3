/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.reply.FamilyMemberDetailResponseEvent
 * Version: 1.0.0
 *
 * Date:    2005-09-19
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.juvenilecase.reply;

import java.util.Date;

import naming.PDJuvenileFamilyConstants;
import mojo.km.messaging.ResponseEvent;

/**
 * 
 * @author athorat To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * @version 1.0.0
 */
public class FamilyMemberDetailResponseEvent extends ResponseEvent
{

	private String memberId;

	private String firstName;

	private String lastName;

	private String middleName;

	private String ssn;

	private Date dateOfBirth;

	private String sexId;
	
	private String sexDesc;

	private String alienRegistrationNum;

	private String isUSCitizenId;

	private String ethnicityId;
	
	private String ethnicityDesc;

	private String nationalityId;
	
	private String nationalityDesc;

	private String sidNum;

	private String juvenileAgeAtDeath;

	private String primarylanguageId;

	private String secondaryLanguageId;

	private boolean deceasedInd;
	
	private boolean incarcerated;
	
	private String causeOfDeathId;

	private String comments;

	private String driverLicenceNumber;

	private String driverLicenceStateId;

	private Date driverLicenceExpiryDate;

	private String driverLicenceClassId;

	private String idCardNum;

	private String idCardStateId;
	
	private String psportNum; //added for passport details
	
	private String psportIssueCountry; //added for passport details
	
	private Date psportExpirationDate; //added for passport details
	
	private String psportIssueCountryId; //added for passport details

	private String suspiciousMember;

	private String juvenileNum;
	
	//added for User Story 43892
   	private boolean over21;
   	
   	private String completeSSN;  //US 39892

	/**
	 * 
	 */
	public FamilyMemberDetailResponseEvent()
	{
		this.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_DETAIL_TOPIC);
	}
	
	/**
	 * @return the fullName
	 */
	public String getFullNamelfm() {
		if(firstName!=null && !firstName.equals("")){
			String lfmName = lastName + ", " + firstName;
			if (middleName!=null){
				lfmName += " " + middleName;
			}
			return lfmName;
		}
		else{
			return lastName;
		}
	}

	/**
	 * @return
	 */
	public String getAlienRegistrationNum()
	{
		return alienRegistrationNum;
	}

	/**
	 * @return
	 */
	public String getCauseOfDeathId()
	{
		return causeOfDeathId;
	}

	/**
	 * @return
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @return
	 */
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @return
	 */
	public boolean isDeceasedInd()
	{
		return deceasedInd;
	}
	
	/**
	 * @return
	 */
	public boolean isIncarcerated()
	{
		return incarcerated;
	}

	/**
	 * @return
	 */
	public String getDriverLicenceClassId()
	{
		return driverLicenceClassId;
	}

	/**
	 * @return
	 */
	public Date getDriverLicenceExpiryDate()
	{
		return driverLicenceExpiryDate;
	}

	/**
	 * @return
	 */
	public String getDriverLicenceNumber()
	{
		return driverLicenceNumber;
	}

	/**
	 * @return
	 */
	public String getDriverLicenceStateId()
	{
		return driverLicenceStateId;
	}

	/**
	 * @return
	 */
	public String getEthnicityId()
	{
		return ethnicityId;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getIdCardNum()
	{
		return idCardNum;
	}

	/**
	 * @return
	 */
	public String getIdCardStateId()
	{
		return idCardStateId;
	}
	
	/**
		 * @return
		 */
		public String getPsportNum()
		{
			return psportNum;
		}
	/**
		 * @return
		 */
		public String getPsportIssueCountry()
		{
			return psportIssueCountry;
		}

	/**
		 * @return
		 */
		public Date getPsportExpirationDate()
		{
			return psportExpirationDate;
		}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMemberId()
	{
		return memberId;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return
	 */
	public String getNationalityId()
	{
		return nationalityId;
	}

	/**
	 * @return
	 */
	public String getPrimarylanguageId()
	{
		return primarylanguageId;
	}

	/**
	 * @return
	 */
	public String getSecondaryLanguageId()
	{
		return secondaryLanguageId;
	}

	/**
	 * @return
	 */
	public String getSexId()
	{
		return sexId;
	}

	/**
	 * @return
	 */
	public String getSidNum()
	{
		return sidNum;
	}

	/**
	 * @return
	 */
	public String getSsn()
	{
		return ssn;
	}

	/**
	 * @param string
	 */
	public void setAlienRegistrationNum(String string)
	{
		alienRegistrationNum = string;
	}

	/**
	 * @param string
	 */
	public void setCauseOfDeathId(String string)
	{
		causeOfDeathId = string;
	}

	/**
	 * @param string
	 */
	public void setComments(String string)
	{
		comments = string;
	}

	/**
	 * @param date
	 */
	public void setDateOfBirth(Date date)
	{
		dateOfBirth = date;
	}

	/**
	 * @param b
	 */
	public void setDeceasedInd(boolean b)
	{
		deceasedInd = b;
	}
	
	/**
	 * @param b
	 */
	public void setIncarcerated(boolean b)
	{
		incarcerated = b;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenceClassId(String string)
	{
		driverLicenceClassId = string;
	}

	/**
	 * @param date
	 */
	public void setDriverLicenceExpiryDate(Date date)
	{
		driverLicenceExpiryDate = date;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenceNumber(String string)
	{
		driverLicenceNumber = string;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenceStateId(String string)
	{
		driverLicenceStateId = string;
	}

	/**
	 * @param string
	 */
	public void setEthnicityId(String string)
	{
		ethnicityId = string;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setIdCardNum(String string)
	{
		idCardNum = string;
	}

	/**
	 * @param string
	 */
	public void setIdCardStateId(String string)
	{
		idCardStateId = string;
	}


	/**
		 * @param string
		 */
		public void setPsportNum(String string)
		{
			psportNum = string;
		}
	/**
		 * @param string
		 */
		public void setPsportIssueCountry(String string)
		{
			psportIssueCountry = string;
		}

		/**
		 * @param date
		 */
		public void setPsportExpirationDate(Date date)
		{
			psportExpirationDate = date;
		}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setMemberId(String string)
	{
		memberId = string;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	}

	/**
	 * @param string
	 */
	public void setNationalityId(String string)
	{
		nationalityId = string;
	}

	/**
	 * @param string
	 */
	public void setPrimarylanguageId(String string)
	{
		primarylanguageId = string;
	}

	/**
	 * @param string
	 */
	public void setSecondaryLanguageId(String string)
	{
		secondaryLanguageId = string;
	}

	/**
	 * @param string
	 */
	public void setSexId(String string)
	{
		sexId = string;
	}

	/**
	 * @param string
	 */
	public void setSidNum(String string)
	{
		sidNum = string;
	}

	/**
	 * @param string
	 */
	public void setSsn(String string)
	{
		ssn = string;
	}

	/**
	 * @return Returns the isUSCitizenId.
	 */
	public String getIsUSCitizenId()
	{
		return isUSCitizenId;
	}

	/**
	 * @param isUSCitizenId
	 *            The isUSCitizenId to set.
	 */
	public void setIsUSCitizenId(String isUSCitizenId)
	{
		this.isUSCitizenId = isUSCitizenId;
	}

	/**
	 * @return
	 */
	public String getSuspiciousMember()
	{
		return suspiciousMember;
	}

	/**
	 * @param string
	 */
	public void setSuspiciousMember(String string)
	{
		suspiciousMember = string;
	}

	/**
	 * @return Returns the juvenileAgeAtDeath.
	 */
	public String getJuvenileAgeAtDeath()
	{
		return juvenileAgeAtDeath;
	}
	/**
	 * @return
	 */
	public String getPsportIssueCountryId()
	{
		return psportIssueCountryId;
	
	}
	
	
	/**
	 * @param string
	 */
	public void setPsportIssueCountryId(String string)
	{
		psportIssueCountryId = string;
	}
	/**
	 * @param juvenileAgeAtDeath
	 *            The juvenileAgeAtDeath to set.
	 */
	public void setJuvenileAgeAtDeath(String juvenileAgeAtDeath)
	{
		this.juvenileAgeAtDeath = juvenileAgeAtDeath;
	}

	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 *            The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;
	}

	public boolean isOver21() {
		return over21;
	}

	public void setOver21(boolean over21) {
		this.over21 = over21;
	}

	public String getCompleteSSN() {
		return completeSSN;
	}

	public void setCompleteSSN(String completeSSN) {
		this.completeSSN = completeSSN;
	}

	public String getEthnicityDesc()
	{
	    return ethnicityDesc;
	}

	public void setEthnicityDesc(String ethnicityDesc)
	{
	    this.ethnicityDesc = ethnicityDesc;
	}

	public String getNationalityDesc()
	{
	    return nationalityDesc;
	}

	public void setNationalityDesc(String nationalityDesc)
	{
	    this.nationalityDesc = nationalityDesc;
	}

	public String getSexDesc()
	{
	    return sexDesc;
	}

	public void setSexDesc(String sexDesc)
	{
	    this.sexDesc = sexDesc;
	}

} // end FamilyMemberDetailResponseEvent
