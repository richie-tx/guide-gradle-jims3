//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\domintf\\managesupervisioncase\\IParty.java

package messaging.domintf.managesupervisioncase;

import java.util.Date;

public interface IParty
{

	/**
	 * Access method for the getPartyOid property.
	 * 
	 * @return   the current value of the getPartyOid property
	 */
	String getPartyOid();
   
	/**
	 * Sets the value of the getPartyOid property.
	 * 
	 * @param anOid the new value of the getPartyOid property
	 */
	void setPartyOid(String anOid);
   
	/**
	 * Access method for the partySpn property.
	 * 
	 * @return   the current value of the partySpn property
	 */
	String getPartySpn();
   
	/**
	 * Sets the value of the partySpn property.
	 * 
	 * @param aSpn the new value of the partySpn property
	 */
	void setPartySpn(String aSpn);
   
	/**
	 * Access method for the partyFirstName property.
	 * 
	 * @return   the current value of the partyFirstName property
	 */
	String getPartyFirstName();
   
	/**
	 * Sets the value of the partyFirstName property.
	 * 
	 * @param aFirstName the new value of the partyFirstName property
	 */
	void setPartyFirstName(String aFirstName);
   
	/**
	 * Access method for the partyMiddleName property.
	 * 
	 * @return   the current value of the partyMiddleName property
	 */
	String getPartyMiddleName();
   
	/**
	 * Sets the value of the partyMiddleName property.
	 * 
	 * @param aMiddleName the new value of the partyMiddleName property
	 */
	void setPartyMiddleName(String aMiddleName);
   
	/**
	 * Access method for the partyLastName property.
	 * 
	 * @return   the current value of the partyLastName property
	 */
	String getPartyLastName();
   
	/**
	 * Sets the value of the partyLastName property.
	 * 
	 * @param aLastName the new value of the partyLastName property
	 */
	void setPartyLastName(String aLastName);
   
	/**
	 * Access method for the partySSN property.
	 * 
	 * @return   the current value of the partySSN property
	 */
	String getPartySSN();
   
	/**
	 * Sets the value of the partySSN property.
	 * 
	 * @param aSSN the new value of the partySSN property
	 */
	void setPartySSN(String aSSN);
   
	/**
	 * Access method for the partyDateOfBirth property.
	 * 
	 * @return   the current value of the partyDateOfBirth property
	 */
	Date getPartyDateOfBirth();
   
	/**
	 * Sets the value of the partyDateOfBirth property.
	 * 
	 * @param aDateOfBirth the new value of the partyDateOfBirth property
	 */
	void setPartyDateOfBirth(Date aDateOfBirth);
   
	/**
	 * Access method for the partyRaceId property.
	 * 
	 * @return   the current value of the partyRaceId property
	 */
	String getPartyRaceId();
   
	/**
	 * Sets the value of the partyRaceId property.
	 * 
	 * @param aRaceId the new value of the partyRaceId property
	 */
	void setPartyRaceId(String aRaceId);
   
	/**
	 * Access method for the partySexId property.
	 * 
	 * @return   the current value of the partySexId property
	 */
	String getPartySexId();
   
	/**
	 * Sets the value of the partySexId property.
	 * 
	 * @param aSexId the new value of the partySexId property
	 */
	void setPartySexId(String aSexId);
	
    /**
	 * Access method for the partySID property.
	 * 
	 * @return   the current value of the partySID property
	 */
	String getPartySID();
   
	/**
	 * Sets the value of the partySID property.
	 * 
	 * @param aSID the new value of the partySID property
	 */
	void setPartySID(String aSID);
	
	void setPartyNamePtr(String namePtr);
	
	String getPartyNamePtr();
	
	void setPartyNameSeqNum(String nameSeqNum);
	
	String getPartyNameSeqNum();
	
}
