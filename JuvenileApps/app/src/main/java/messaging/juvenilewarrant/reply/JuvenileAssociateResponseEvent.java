/*
 * Created on Oct 15, 2004
 */
package messaging.juvenilewarrant.reply;

import java.util.Date;

import messaging.contact.domintf.IAddress;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dnikolis
 */
public class JuvenileAssociateResponseEvent extends ResponseEvent
{
	private String associateNum;
	private Date dateOfBirth;
	private String firstName;
	private String lastName;
	private String middleName;
	private String race;
	private String raceId;
	private String relationshipToJuvenile;
	private String relationshipToJuvenileId;
	private String releasedTo;
	private String sex;
	private String sexId;
	private String ssn;
	private String warrantNum;
	
	private IAddress address;
	
	// new attributes
	private boolean releaseTo;
	private String homePhoneNum;
	private String workPhoneNum;
	private String cellPhoneNum;
	private String pager;
	private String faxNum;
	private String faxLocation;
	private String email1;
	private String email2;
	private String email3;
	private String dlStateId;
	private String dlState;
	private String dlNumber;

	/**
	 * @return
	 */
	public String getAssociateNum()
	{
		return associateNum;
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
	public String getFirstName()
	{
		return firstName;
	}
//	/**
//	 * @return
//	 */
//	public String getIdVerification()
//	{
//		return idVerification;
//	}
//
//	/**
//	 * @return
//	 */
//	public String getIdVerificationId()
//	{
//		return idVerificationId;
//	}

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
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return
	 */
	public String getRace()
	{
		return race;
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
	public String getRelationshipToJuvenile()
	{
		return relationshipToJuvenile;
	}

	/**
	 * @return
	 */
	public String getRelationshipToJuvenileId()
	{
		return relationshipToJuvenileId;
	}

	/**
	 * @return
	 */
	public String getReleasedTo()
	{
		return releasedTo;
	}

	/**
	 * @return
	 */
	public String getSex()
	{
		return sex;
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
	public String getSsn()
	{
		return ssn;
	}

	/**
	 * @return
	 */
	public String getWarrantNum()
	{
		return warrantNum;
	}

	/**
	 * @param associateNum
	 */
	public void setAssociateNum(String associateNum)
	{
		this.associateNum = associateNum;
	}

	/**
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

//	/**
//	 * @param idVerification
//	 */
//	public void setIdVerification(String idVerification)
//	{
//		this.idVerification = idVerification;
//	}
//
//	/**
//	 * @param idVerificationId
//	 */
//	public void setIdVerificationId(String idVerificationId)
//	{
//		this.idVerificationId = idVerificationId;
//	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @param middleName
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @param race
	 */
	public void setRace(String race)
	{
		this.race = race;
	}

	/**
	 * @param raceId
	 */
	public void setRaceId(String raceId)
	{
		this.raceId = raceId;
	}

	/**
	 * @param relationshipToJuvenile
	 */
	public void setRelationshipToJuvenile(String relationshipToJuvenile)
	{
		this.relationshipToJuvenile = relationshipToJuvenile;
	}

	/**
	 * @param relationshipToJuvenileId
	 */
	public void setRelationshipToJuvenileId(String relationshipToJuvenileId)
	{
		this.relationshipToJuvenileId = relationshipToJuvenileId;
	}

	/**
	 * @param releasedTo
	 */
	public void setReleasedTo(String releaseTo)
	{
		this.releasedTo = releaseTo;
	}

	/**
	 * @param sex
	 */
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	/**
	 * @param sexId
	 */
	public void setSexId(String sexId)
	{
		this.sexId = sexId;
	}

	/**
	 * @param ssn
	 */
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	/**
	 * @param warrantNum
	 */
	public void setWarrantNum(String warrantNum)
	{
		this.warrantNum = warrantNum;
	}
	/**
	 * @return
	 */
	public String getCellPhoneNum()
	{
		return cellPhoneNum;
	}

	/**
	 * @return
	 */
	public String getEmail1()
	{
		return email1;
	}

	/**
	 * @return
	 */
	public String getEmail2()
	{
		return email2;
	}

	/**
	 * @return
	 */
	public String getEmail3()
	{
		return email3;
	}

	/**
	 * @return
	 */
	public String getFaxLocation()
	{
		return faxLocation;
	}

	/**
	 * @return
	 */
	public String getFaxNum()
	{
		return faxNum;
	}

	/**
	 * @return
	 */
	public String getHomePhoneNum()
	{
		return homePhoneNum;
	}

	/**
	 * @return
	 */
	public String getPager()
	{
		return pager;
	}

	/**
	 * @return
	 */
	public boolean isReleaseTo()
	{
		return releaseTo;
	}

	/**
	 * @return
	 */
	public String getWorkPhoneNum()
	{
		return workPhoneNum;
	}

	/**
	 * @param string
	 */
	public void setCellPhoneNum(String string)
	{
		cellPhoneNum = string;
	}

	/**
	 * @param string
	 */
	public void setEmail1(String string)
	{
		email1 = string;
	}

	/**
	 * @param string
	 */
	public void setEmail2(String string)
	{
		email2 = string;
	}

	/**
	 * @param string
	 */
	public void setEmail3(String string)
	{
		email3 = string;
	}

	/**
	 * @param string
	 */
	public void setFaxLocation(String string)
	{
		faxLocation = string;
	}

	/**
	 * @param string
	 */
	public void setFaxNum(String string)
	{
		faxNum = string;
	}

	/**
	 * @param string
	 */
	public void setHomePhoneNum(String string)
	{
		homePhoneNum = string;
	}

	/**
	 * @param string
	 */
	public void setPager(String string)
	{
		pager = string;
	}

	/**
	 * @param b
	 */
	public void setReleaseTo(boolean b)
	{
		releaseTo = b;
	}

	/**
	 * @param string
	 */
	public void setWorkPhoneNum(String string)
	{
		workPhoneNum = string;
	}

	/**
	 * @return
	 */
	public String getDlNumber()
	{
		return dlNumber;
	}

	/**
	 * @return
	 */
	public String getDlState()
	{
		return dlState;
	}

	/**
	 * @return
	 */
	public String getDlStateId()
	{
		return dlStateId;
	}

	/**
	 * @param string
	 */
	public void setDlNumber(String dlNumber)
	{
		this.dlNumber = dlNumber;
	}

	/**
	 * @param string
	 */
	public void setDlState(String dlState)
	{
		this.dlState = dlState;
	}

	/**
	 * @param string
	 */
	public void setDlStateId(String dlStateId)
	{
		this.dlStateId = dlStateId;
	}

    /**
     * @return Returns the address.
     */
    public IAddress getAddress()
    {
        return address;
    }
    /**
     * @param address The address to set.
     */
    public void setAddress(IAddress address)
    {
        this.address = address;
    }
}