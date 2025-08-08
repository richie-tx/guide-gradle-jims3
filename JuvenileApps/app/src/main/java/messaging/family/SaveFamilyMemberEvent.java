/*
 * Created on Oct 4, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.Composite.CompositeRequest;

/**
 * @author athorat
 *
  */
public class SaveFamilyMemberEvent extends CompositeRequest implements IFamilyMember
{
	
	private String  memberId;
	private String	firstName;
	private String	lastName;
	private String	middleName;
	private String	ssn;
	private Date	dateOfBirth;
	private String	sexId;
	private String	alienRegistrationNum;
	private String	isUSCitizenId;
	private String	ethnicityId;
	private String	nationalityId;
	private String	sidNum;
	
	private String	primarylanguageId;
	private String	secondaryLanguageId;
	private boolean	deceasedInd;
	private boolean	incarcerated;
	private String	causeOfDeathId;
	private String	comments;

	private String driverLicenceNumber;
	private String driverLicenceStateId;
	private Date   driverLicenceExpiryDate;
	private String driverLicenceClassId;
	private String idCardNum;
	private String idCardStateId;
	private String psportNum ; //added for passport details
	private String psportIssueCountryId;  //added for passport details
	//private String countryOfIssuanceId; //added for passport details
	private Date psportExpiryDate; //added for passport details
	private String juvenileAgeAtDeath;
	private List suspiciousMatches;
	private String suspiciousMemberId;
	private String juvRelation;
	private String suspiciousMatchType; //added for //US 181437
	//added for User Story 43892
	private boolean over21;

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
		public String getPsportIssueCountryId()
		{
			return psportIssueCountryId;
		}

	/**
		 * @return
		 */
		public Date getPsportExpiryDate()
		{
			return psportExpiryDate;
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
		public void setPsportIssueCountryId(String string)
		{
			psportIssueCountryId = string;
		}

		/**
		 * @param date
		 */
		public void setPsportExpiryDate(Date date)
		{
			psportExpiryDate = date;
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
	public String getIsUSCitizenId() {
		return isUSCitizenId;
	}
	/**
	 * @param isUSCitizenId The isUSCitizenId to set.
	 */
	public void setIsUSCitizenId(String isUSCitizenId) {
		this.isUSCitizenId = isUSCitizenId;
	}
	/**
	 * @return Returns the juvenileAgeAtDeath.
	 */
	public String getJuvenileAgeAtDeath() {
		return juvenileAgeAtDeath;
	}
	/**
	 * @param juvenileAgeAtDeath The juvenileAgeAtDeath to set.
	 */
	public void setJuvenileAgeAtDeath(String juvenileAgeAtDeath) {
		this.juvenileAgeAtDeath = juvenileAgeAtDeath;
	}

	public List getSuspiciousMatches() {
		return suspiciousMatches;
	}

	public void setSuspiciousMatches(List suspiciousMatches) {
		this.suspiciousMatches = suspiciousMatches;
	}

	public boolean isOver21() {
		return over21;
	}

	public void setOver21(boolean over21) {
		this.over21 = over21;
	}

	public String getSuspiciousMemberId()
	{
	    return suspiciousMemberId;
	}

	public void setSuspiciousMemberId(String suspiciousMemberId)
	{
	    this.suspiciousMemberId = suspiciousMemberId;
	}
	
	public String getJuvRelation()
	{
	    return juvRelation;
	}
	
	public void setJuvRelation(String juvRelation)
	{
	    this.juvRelation = juvRelation;
	}

	@Override
	public void setSuspiciousMatchType(String suspiciousMatchType)
	{
	    this.suspiciousMatchType = suspiciousMatchType;
	}

	@Override
	public String getSuspiciousMatchType()
	{
	    return suspiciousMatchType;
	}
}
