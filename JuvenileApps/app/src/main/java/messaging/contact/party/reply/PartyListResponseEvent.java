/*
 * Created on May 2, 2006
 *
 */
package messaging.contact.party.reply;

import java.util.Date;

import messaging.contact.domintf.ISocialSecurity;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class PartyListResponseEvent extends ResponseEvent implements Comparable
{
	private String connectionId;
	private Date dateOfBirth;
	private String name;
	private String oid;
	private String raceId;
	private String race;
	private String sex;
	private String sexId;
	private String spn;
	private ISocialSecurity ssn;
	private String sid;
	private String firstName;
	private String lastName;
	private String middleName;
	private String jailInd;
	private String active;	
	
	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}
	/**
	 * @return Returns the jailInd.
	 */
	public String getJailInd() {
		return jailInd;
	}
	/**
	 * @param jailInd The jailInd to set.
	 */
	public void setJailInd(String jailInd) {
		this.jailInd = jailInd;
	}
	public int compareTo(Object arg0)
		{
			PartyListResponseEvent partyList = (PartyListResponseEvent) arg0;
			int comparisonResult = 0;
			String newString = "";
			
			if (partyList.getName() == null)
			{
				partyList.setName(newString);
			}

			return (this.getName().compareTo(partyList.getName()));
		}

	/**
	 * @return
	 */
	public String getConnectionId()
	{
		return connectionId;
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
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getOid()
	{
		return oid;
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
	public String getSexId()
	{
		return sexId;
	}

	/**
	 * @return
	 */
	public String getSpn()
	{
		return spn;
	}

	/**
	 * @return
	 */
	public ISocialSecurity getSsn()
	{
		return ssn;
	}

	/**
	 * @return
	 */
	public String getSid()
	{
		return sid;
	}

	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * @return Returns the middleName.
	 */
	public String getMiddleName()
	{
		return middleName;
	}
	
	/**
	 * @param string
	 */
	public void setConnectionId(String string)
	{
		connectionId = string;
	}

	/**
	 * @param date
	 */
	public void setDateOfBirth(Date date)
	{
		dateOfBirth = date;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @param string
	 */
	public void setOid(String string)
	{
		oid = string;
	}

	/**
	 * @param string
	 */
	public void setRaceId(String string)
	{
		race="";
//		if(string!=null && !string.trim().equals("")){
//		    
//			race=CodeHelper.getCodeDescription(PDCodeHelper.getRaceCodes(false),string);
//		}
		raceId = string;
	}

	/**
	 * @param string
	 */
	public void setSexId(String string)
	{
		sex="";
//		if(string!=null && !string.trim().equals("")){
//			sex=CodeHelper.getCodeDescription(CodeHelper.getSexCodes(false),string);
//		}
		sexId = string;
	}

	/**
	 * @param string
	 */
	public void setSpn(String string)
	{
		String str = string;
		while (str.length() < 8) {
			str = "0" + str;
	    }
		spn = str;
	}

	/**
	 * @param string
	 */
	public void setSsn(ISocialSecurity socialSec)
	{
		ssn = socialSec;
	}
	
	/**
	 * @param string
	 */
	public void setSid(String string)
	{
		sid = string;
	}
	
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	/**
	 * @param middleName The middleName to set.
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @return Returns the race.
	 */
	public String getRace() {
		return race;
	}
	/**
	 * @param race The race to set.
	 */
	public void setRace(String race) {
		this.race = race;
	}
	/**
	 * @return Returns the sex.
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex The sex to set.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
}
