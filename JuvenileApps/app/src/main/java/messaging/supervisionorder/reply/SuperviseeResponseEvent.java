/*
 * Created on Nov 18, 2005
 */
package messaging.supervisionorder.reply;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import messaging.administercasenotes.to.CasenoteCaseTO;
import messaging.contact.party.reply.PartyResponseEvent;
import messaging.domintf.contact.party.ISupervisee;

/**
 * @author jmcnabb
 *
 */
public class SuperviseeResponseEvent extends PartyResponseEvent implements ISupervisee
{
	private Collection cases = new ArrayList();
	private String unit;
	private String occupation;
	private Date nextContactDate;
	private String contactMethod;
	private String contactReason;
	private String officerName;
	private String officerPosition;	
	private String probationOfficerId;
	private boolean inComplianceInd=true; // default is out of complieance = false
	
	
	/**
	 * @return Returns the collection of messaging.administercasenotes.to.CasenoteCaseTO.
	 */
	public Collection getCases()
	{
		return cases;
	}

	public String getOfficerPosition() {
		return officerPosition;
	}

	public void setOfficerPosition(String officerPosition) {
		this.officerPosition = officerPosition;
	}
	
	/**
	 * @return Returns the contactMethod.
	 */
	public String getContactMethod()
	{
		return contactMethod;
	}
	
	/**
	 * @return Returns the contactReason.
	 */
	public String getContactReason()
	{
		return contactReason;
	}
	
	/**
	 * @return Returns the contactReason.
	 */
	public String getCourtNums()
	{
		StringBuffer lBuff = new StringBuffer();
		boolean hasFirst = false;
		// get court numbers from the cases
		for (Iterator i = getCases().iterator(); i.hasNext();)
		{
			if (hasFirst)
			{
				lBuff.append(", ");
			}
			CasenoteCaseTO aCaseTO = (CasenoteCaseTO) i.next();
			lBuff.append(aCaseTO.getCourtNum());
			hasFirst = true;
		}
		
		return lBuff.toString();
	}
	
	/**
	 * @return Returns the nextContactDate.
	 */
	public Date getNextContactDate()
	{
		return nextContactDate;
	}
	
	/**
	 * @return Returns the occupation.
	 */
	public String getOccupation()
	{
		return occupation;
	}
	
	/**
	 * @return
	 */
	public String getOfficerName()
	{
		return officerName;
	}

	/**
	 * @return
	 */
	public String getUnit()
	{
		return unit;
	}

	/**
	 * @param aContactMethod The contactMethod to set.
	 */
	public void setContactMethod(String aContactMethod)
	{
		this.contactMethod = aContactMethod;
	}
	
	/**
	 * @param contactReason The contactReason to set.
	 */
	public void setContactReason(String aContactReason)
	{
		this.contactReason = aContactReason;
	}
	
	/**
	 * @param aDate The nextContactDate to set.
	 */
	public void setNextContactDate(Date aDate)
	{
		this.nextContactDate = aDate;
	}
	
	/**
	 * @param aCaseTO The CasenoteCaseTO to add to the cases collection.
	 */
	public void insertCase(CasenoteCaseTO aCaseTO)
	{
		this.cases.add(aCaseTO);
	}

	/**
	 * @param anOccupation The occupation to set.
	 */
	public void setOccupation(String anOccupation)
	{
		this.occupation = anOccupation;
	}
	
	/**
	 * @param aName
	 */
	public void setOfficerName(String aName)
	{
		officerName = aName;
	}
	
	/**
	 * @param aUnit
	 */
	public void setUnit(String aUnit)
	{
		unit = aUnit;
	}
	
	/**
	 * @return Returns the inComplianceInd.
	 */
	public boolean isInComplianceInd() {
		return inComplianceInd;
	}
	/**
	 * @param inComplianceInd The inComplianceInd to set.
	 */
	public void setInComplianceInd(boolean inComplianceInd) {
		this.inComplianceInd = inComplianceInd;
	}
	/**
	 * @return Returns the probationOfficerId.
	 */
	public String getProbationOfficerId() {
		return probationOfficerId;
	}
	/**
	 * @param probationOfficerId The probationOfficerId to set.
	 */
	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}
}
