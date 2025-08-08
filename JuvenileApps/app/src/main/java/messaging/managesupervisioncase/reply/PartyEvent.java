//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\managesupervisioncase\\reply\\PartyEvent.java

package messaging.managesupervisioncase.reply;

import java.util.Date;

import messaging.domintf.managesupervisioncase.IParty;
import mojo.km.messaging.ResponseEvent;

public class PartyEvent extends ResponseEvent implements IParty
{
	private String partyOid;
	private String partySpn;
	private String partyFirstName;
	private String partyMiddleName;
	private String partyLastName;
	private String partySSN;
	private Date partyDateOfBirth;
	private String partyRaceId;
	private String partySexId;
	private String partySID;
	private String partyName;
	private String partyNamePtr;
	private String partyNameSeqNum;
	/**
	 * 
	 */
	public PartyEvent()
	{

	}

	/**
	 * Access method for the getPartyOid property.
	 * 
	 * @return   the current value of the getPartyOid property
	 */
	public String getPartyOid()
	{
		return partyOid;
	}
	
	/**
	 * Sets the value of the getPartyOid property.
	 * 
	 * @param anOid the new value of the getPartyOid property
	 */
	public void setPartyOid(String anOid)
	{
		partyOid = anOid;
	}
   
	/**
	 * Access method for the partySpn property.
	 * 
	 * @return   the current value of the partySpn property
	 */
	public String getPartySpn()
	{
		return partySpn;
	}

	/**
	 * Sets the value of the partySpn property.
	 * 
	 * @param aSpn the new value of the partySpn property
	 */
	public void setPartySpn(String aSpn)
	{
		partySpn = aSpn;
	}

	/**
	 * Access method for the partyFirstName property.
	 * 
	 * @return   the current value of the partyFirstName property
	 */
	public String getPartyFirstName()
	{
		return partyFirstName;
	}
   
	/**
	 * Sets the value of the partyFirstName property.
	 * 
	 * @param aFirstName the new value of the partyFirstName property
	 */
	public void setPartyFirstName(String aFirstName)
	{
		partyFirstName = aFirstName;
	}
   
	/**
	 * Access method for the partyMiddleName property.
	 * 
	 * @return   the current value of the partyMiddleName property
	 */
	public String getPartyMiddleName()
	{
		return partyMiddleName;
	}
   
	/**
	 * Sets the value of the partyMiddleName property.
	 * 
	 * @param aMiddleName the new value of the partyMiddleName property
	 */
	public void setPartyMiddleName(String aMiddleName)
	{
		partyMiddleName = aMiddleName;
	}
   
	/**
	 * Access method for the partyLastName property.
	 * 
	 * @return   the current value of the partyLastName property
	 */
	public String getPartyLastName()
	{
		return partyLastName;
	}
   
	/**
	 * Sets the value of the partyLastName property.
	 * 
	 * @param aLastName the new value of the partyLastName property
	 */
	public void setPartyLastName(String aLastName)
	{
		partyLastName = aLastName;
	}
   
	/**
	 * Access method for the partySSN property.
	 * 
	 * @return   the current value of the partySSN property
	 */
	public String getPartySSN()
	{
		return partySSN;
	}
   
	/**
	 * Sets the value of the partySSN property.
	 * 
	 * @param aSSN the new value of the partySSN property
	 */
	public void setPartySSN(String aSSN)
	{
		partySSN = aSSN;
	}
   
	/**
	 * Access method for the partyDateOfBirth property.
	 * 
	 * @return   the current value of the partyDateOfBirth property
	 */
	public Date getPartyDateOfBirth()
	{
		return partyDateOfBirth;
	}
   
	/**
	 * Sets the value of the partyDateOfBirth property.
	 * 
	 * @param aDateOfBirth the new value of the partyDateOfBirth property
	 */
	public void setPartyDateOfBirth(Date aDateOfBirth)
	{
		partyDateOfBirth = aDateOfBirth;
	}
   
	/**
	 * Access method for the partyRaceId property.
	 * 
	 * @return   the current value of the partyRaceId property
	 */
	public String getPartyRaceId()
	{
		return partyRaceId;
	}
   
	/**
	 * Sets the value of the partyRaceId property.
	 * 
	 * @param aRaceId the new value of the partyRaceId property
	 */
	public void setPartyRaceId(String aRaceId)
	{
		partyRaceId = aRaceId;
	}
   
	/**
	 * Access method for the partySexId property.
	 * 
	 * @return   the current value of the partySexId property
	 */
	public String getPartySexId()
	{
		return partySexId;
	}
   
	/**
	 * Sets the value of the partySexId property.
	 * 
	 * @param aSexId the new value of the partySexId property
	 */
	public void setPartySexId(String aSexId)
	{
		partySexId = aSexId;
	}
	
	/**
	 * Access method for the partySID property.
	 * 
	 * @return   the current value of the partySID property
	 */
	public String getPartySID()
	{
		return partySID;
	}
   
	/**
	 * Sets the value of the partySID property.
	 * 
	 * @param aSID the new value of the partySID property
	 */
	public void setPartySID(String aSID)
	{
		partySID = aSID;
	}
   
	/**
	 * Sets the value of the name properties.
	 * 
	 * @param aName  Used to set the firstName, lastName and middleName properties
	 */
	public void setPartyName(String aName)
	{
		this.partyName = aName;
	}

	public String getPartyNamePtr() {
		return partyNamePtr;
	}

	public void setPartyNamePtr(String namePtr) {
		this.partyNamePtr = namePtr;
		
	}

	public String getPartyNameSeqNum() {
		return partyNameSeqNum;
	}

	public void setPartyNameSeqNum(String nameSeqNum) {
		this.partyNameSeqNum = nameSeqNum;
		
	}

}
