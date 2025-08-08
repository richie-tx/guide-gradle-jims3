/*
 * Created on Oct 4, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import java.util.Date;
import java.util.List;

/**
 * @author athorat
 *
 */
public interface IFamilyMember
{
	/**
	 * @return
	 */
	public String getAlienRegistrationNum();

	/**
	 * @return
	 */
	public String getCauseOfDeathId();

	/**
	 * @return
	 */
	public String getComments();

	/**
	 * @return
	 */
	public Date getDateOfBirth();

	/**
	 * @return
	 */
	public boolean isDeceasedInd();
	
	/**
	 * @return
	 */
	public boolean isIncarcerated();

	/**
	 * @return
	 */
	public String getDriverLicenceClassId();

	/**
	 * @return
	 */
	public Date getDriverLicenceExpiryDate();

	/**
	 * @return
	 */
	public String getDriverLicenceNumber();

	/**
	 * @return
	 */
	public String getDriverLicenceStateId();

	/**
	 * @return
	 */
	public String getEthnicityId();

	/**
	 * @return
	 */
	public String getFirstName();

	/**
	 * @return
	 */
	public String getIdCardNum();

	/**
	 * @return
	 */
	public String getIdCardStateId();
	/**
	 * @return
	 */
	public String getPsportNum();
	/**
	 *@return
	 */
	public Date getPsportExpiryDate();

	/**
	 * @return
	 */
	public String getLastName();

	/**
	 * @return
	 */
	public String getMemberId();

	/**
	 * @return
	 */
	public String getMiddleName();

	/**
	 * @return
	 */
	public String getNationalityId();
	
	/**
	 * @return
	 */
	public String getPsportIssueCountryId();

	/**
	 * @return
	 */
	public String getPrimarylanguageId();

	/**
	 * @return
	 */
	public String getSecondaryLanguageId();

	/**
	 * @return
	 */
	public String getSexId();

	/**
	 * @return
	 */
	public String getSidNum();

	/**
	 * @return
	 */
	public String getSsn();

	/**
	 * @return
	 */
	public String getIsUSCitizenId();
	
	public List getSuspiciousMatches(); 
	
	public String getSuspiciousMatchType();//suspiciousMatchType

	/**
	 * @param string
	 */
	public void setAlienRegistrationNum(String string);

	/**
	 * @param string
	 */
	public void setCauseOfDeathId(String string);

	/**
	 * @param string
	 */
	public void setComments(String string);

	/**
	 * @param date
	 */
	public void setDateOfBirth(Date date);

	/**
	 * @param b
	 */
	public void setDeceasedInd(boolean b);
	
	/**
	 * @param b
	 */
	public void setIncarcerated(boolean b);

	/**
	 * @param string
	 */
	public void setDriverLicenceClassId(String string);

	/**
	 * @param date
	 */
	public void setDriverLicenceExpiryDate(Date date);

	/**
	 * @param string
	 */
	public void setDriverLicenceNumber(String string);

	/**
	 * @param string
	 */
	public void setDriverLicenceStateId(String string);

	/**
	 * @param string
	 */
	public void setEthnicityId(String string);

	/**
	 * @param string
	 */
	public void setFirstName(String string);

	/**
	 * @param string
	 */
	public void setIdCardNum(String string);

	

	/**
	 * @param string
	 */
	public void setIdCardStateId(String string);
	
	/**
	 * @param string
	 */
	public void setPsportNum(String string);
	/**
	 * @param string
	 */
	public void setPsportExpiryDate(Date date);
    /**
	 * @param string
	 */
	public void setLastName(String string);

	/**
	 * @param string
	 */
	public void setMemberId(String string);

	/**
	 * @param string
	 */
	public void setMiddleName(String string);

	/**
	 * @param string
	 */
	public void setNationalityId(String string);

	/**
	 * @param string
	 */
	public void setPsportIssueCountryId(String string);

	/**
	 * @param string
	 */
	public void setPrimarylanguageId(String string);
	
	/**
	 * @param string
	 */
	public void setSecondaryLanguageId(String string);

	/**
	 * @param string
	 */
	public void setSexId(String string);

	/**
	 * @param string
	 */
	public void setSidNum(String string);

	/**
	 * @param string
	 */
	public void setSsn(String string);
	/**
	 * @param string
	 */
	public void setIsUSCitizenId(String string);
	
	public void setSuspiciousMatches(List aList);
	
	public boolean isOver21();
	
	public void setOver21(boolean b);
	
	public void setSuspiciousMatchType(String string); //US 181437
}
