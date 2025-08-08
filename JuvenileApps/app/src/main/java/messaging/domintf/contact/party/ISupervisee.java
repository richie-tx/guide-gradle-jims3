/*
 * Created on Oct 12, 2006
 *
 */
package messaging.domintf.contact.party;

import java.sql.Date;
import java.util.Collection;

import messaging.administercasenotes.to.CasenoteCaseTO;

/**
 * @author jmcnabb
 *
 */
public interface ISupervisee extends IParty
{
	public boolean isInComplianceInd();
	public void setInComplianceInd(boolean inCompliance);

	/**
	 * @return Returns the cases.
	 */
	Collection getCases();

	/**
	 * @return Returns the contactMethod.
	 */
	String getContactMethod();
	
	/**
	 * @return Returns the contactReason.
	 */
	String getContactReason();
	
	/**
	 * @return Returns the courtNums.
	 */
	String getCourtNums();
	
	/**
	 * @return Returns the nextContactDate.
	 */
	Date getNextContactDate();
	
	/**
	 * @return Returns the occupation.
	 */
	String getOccupation();
	
	/**
	 * @return
	 */
	String getOfficerName();
	
	/**
	 * @return
	 */
	String getProbationOfficerId();

	/**
	 * @return
	 */
	String getUnit();
	
	/**
	 * @return
	 */
	String getOfficerPosition();

	/**
	 * @param aContactMethod The contactMethod to set.
	 */
	void setContactMethod(String aContactMethod);
	
	/**
	 * @param aContactReason The contactReason to set.
	 */
	void setContactReason(String aContactReason);
	
	/**
	 * @param aDate The nextContactDate to set.
	 */
	void setNextContactDate(Date aDate);
	
	/**
	 * @param aCaseTO The CasenoteCaseTO to add to the cases collection.
	 */
	void insertCase(CasenoteCaseTO aCaseTO);

	/**
	 * @param anOccupation The occupation to set.
	 */
	void setOccupation(String anOccupation);
	
	/**
	 * @param aName
	 */
	void setOfficerName(String aName);
	
	/**
	 * @param String
	 */
	void setProbationOfficerId(String probationOfficerId);
	
	/**
	 * @param aUnit
	 */
	void setUnit(String aUnit);
	
	/**
	 * @param position
	 */
	void setOfficerPosition(String position);
	
}
