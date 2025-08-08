/*
 * Created on Oct 20, 2004
 */
package messaging.juvenilewarrant;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author dnikolis
 */
public class JuvenileAssociateRequestEvent extends RequestEvent
{
	public String associateNum;
	public Date dateOfBirth;
	public String firstName;
	public String lastName;
	public String middleName;
	public String race;
	public String relationshipToJuvenile;
	public String releasedTo;
	public String sex;
	public String ssn;
	public String warrantNum;
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
	public String getRelationshipToJuvenile()
	{
		return relationshipToJuvenile;
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
	 * @param relationshipToJuvenile
	 */
	public void setRelationshipToJuvenile(String relationshipToJuvenile)
	{
		this.relationshipToJuvenile = relationshipToJuvenile;
	}

	/**
	 * @param releasedTo
	 */
	public void setReleasedTo(String releasedTo)
	{
		this.releasedTo = releasedTo;
	}

	/**
	 * @param sex
	 */
	public void setSex(String sex)
	{
		this.sex = sex;
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

}