/*
 * Project: JIMS2
 * Class:   messaging.juvenileFamily.reply.MemberResponseEvent
 * Version: 0.8.15 
 *
 * Date:    2005-09-28
 *
 * Author:  ANAND THORAT
 * Email:   athorat@jims.hctx.net
 */

package messaging.juvenilecase.reply;

import java.util.Date;
import java.util.List;

import messaging.family.IFamilyMember;
import mojo.km.messaging.ResponseEvent;

/**
 *  
 * @author  athorat
 * @version  1.0.0
 */
public class MemberResponseEvent  extends ResponseEvent implements IFamilyMember
{

	private String familyMemberNum;

	private boolean guardian;

	private String juvenileAgeAtDeath;

	private Date confirmDate;

	private String involvmentLevelId;
    
	private String relationToJuvenileId;

	private String  memberId;
	private String	firstName;
	private String	lastName;
	private String	middleName;
	private String	ssn;
	private Date	dateOfBirth;
	private String	raceId;
	private String	sexId;
	private String	alienRegistrationNum;
	private String	isUSCitizenId;
	private String	ethnicityId;
	private String	nationalityId;
	private String	sidNum;
	
	private String	primarylanguageId;
	private String	secondaryLanguageId;
	private boolean	deceasedInd;
	private String	causeOfDeathId;
	private String	comments;

	private String driverLicenceNumber;
	private String driverLicenceStateId;
	private Date   driverLicenceExpiryDate;
	private String driverLicenceClassId;
	private String psportNum; //added for passport details
	private Date psportExpiryDate; //added for passport details
	private String psportIssueCountryId; //added for passport details
	private String idCardNum;
	private String idCardStateId;
	
	private boolean incarcerated;
	
	private boolean over21;
	
	private String suspiciousMatchType;
	


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
	public String getRaceId()
	{
		return raceId;
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
	public void setRaceId(String string)
	{
		raceId = string;
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

	public List getSuspiciousMatches() {
		return null;
	}

	public void setSuspiciousMatches(List list) {
		
	}

	@Override
	public String getPsportNum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getPsportExpiryDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPsportIssueCountryId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPsportNum(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPsportExpiryDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPsportIssueCountryId(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void setOver21(boolean b) {
		over21 = b;		
	}
	
	public boolean isOver21(){
		return over21;
	}

	@Override
	public void setSuspiciousMatchType(String suspiciousMatchType)
	{
	    suspiciousMatchType = suspiciousMatchType;
	    
	}

	@Override
	public String getSuspiciousMatchType()
	{
	    return suspiciousMatchType;
	}
} // end MemberResponseEvent
