/*
 * Created on May 8, 2006
 */
package messaging.managesupervisioncase.reply;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jmcnabb
 *
 * For displaying the list of Out of County Cases for a Defendant (SPN).
 */
public class OutOfCountyCaseTO implements Serializable, Comparable
{
	private String caseNum;
	private String cdi;
	private String offense;
	private String dispositionTypeId;
	private Date dispositionDate;
	private String caseStatus;
	private String defendantStatus;
	private boolean reactivateInd;
	private String instrumentTypeId;
	private Date filingDate;
	private String courtNum;
	private String connectionId;

	public OutOfCountyCaseTO()
	{

	}

	/**
	 * Access method for the offenseId property.
	 * 
	 * @return   the current value of the offenseId property
	 */
	public String getOffense()
	{
		return offense;
	}

	/**
	 * Sets the value of the stateOffenseCodeId property.
	 * 
	 * @param anOffense the new value of the offense property
	 */
	public void setOffense(String anOffense)
	{
		offense = anOffense;
	}

	/**
	 * Access method for the dispositionTypeId property.
	 * 
	 * @return   the current value of the dispositionTypeId property
	 */
	public String getDispositionTypeId()
	{
		return dispositionTypeId;
	}

	/**
	 * Sets the value of the dispositionTypeId property.
	 * 
	 * @param aDispositionTypeId the new value of the dispositionTypeId property
	 */
	public void setDispositionTypeId(String aDispositionTypeId)
	{
		dispositionTypeId = aDispositionTypeId;
	}

	/**
	 * Access method for the dispositionDate property.
	 * 
	 * @return   the current value of the dispositionDate property
	 */
	public Date getDispositionDate()
	{
		return dispositionDate;
	}

	/**
	 * Sets the value of the dispositionDate property.
	 * 
	 * @param aDispositionDate the new value of the dispositionDate property
	 */
	public void setDispositionDate(Date aDispositionDate)
	{
		dispositionDate = aDispositionDate;
	}

	/**
	 * Sets the value of the caseNum property.
	 * 
	 * @param aCaseNum the new value of the caseNum property
	 */
	public void setCaseNum(String aCaseNum)
	{
		caseNum = aCaseNum;
	}

	/**
	 * Access method for the caseNum property.
	 * 
	 * @return String   the current value of the caseNum property
	 */
	public String getCaseNum()
	{
		return caseNum;
	}

	/**
	 * Sets the value of the cdi property.
	 * 
	 * @param aCdi the new value of the cdi property
	 */
	public void setCdi(String aCdi)
	{
		cdi = aCdi;
	}

	/**
	 * Access method for the cdi property.
	 * 
	 * @return String   the current value of the cdi property
	 */
	public String getCdi()
	{
		return cdi;
	}

	/**
	 * Access method for the caseStatus property.
	 * 
	 * @return   the current value of the caseStatus property
	 */
	public String getCaseStatus()
	{
		return caseStatus;
	}

	/**
	 * Sets the value of the caseStatus property.
	 * 
	 * @param aCaseStatusId the new value of the caseStatus property
	 */
	public void setCaseStatus(String aCaseStatus)
	{
		caseStatus = aCaseStatus;
	}

	/**
	 * Access method for the defendantStatus property.
	 * 
	 * @return   the current value of the defendantStatus property
	 */
	public String getDefendantStatus()
	{
		return defendantStatus;
	}

	/**
	 * Sets the value of the defendantStatusId property.
	 * 
	 * @param aDefendantStatusId the new value of the defendantStatusId property
	 */
	public void setDefendantStatus(String aDefendantStatus)
	{
		defendantStatus = aDefendantStatus;
	}

	/**
	 * Indicates whether the OutOfCountyCase can be reactivated or not.
	 * 
	 * @return  current value of the reactivateInd
	 */
	public boolean getReactivateInd()
	{
		return reactivateInd;
	}
   
	/**
	 * Sets the value of the reactivateInd property.
	 * 
	 * @param aValue the new value of the reactivatenInd property
	 */
	public void setReactivateInd(boolean aValue)
	{
		reactivateInd = aValue;
	}
   
	/**
	 * Sets the value of the filingDate property.
	 * 
	 * @param aFilingDate the new value of the filingDate property
	 */
	public void setFilingDate(Date aFilingDate)
	{
		 filingDate = aFilingDate;
	}
   
	/**
	 * Access method for the filingDate property.
	 * 
	 * @return   the current value of the filingDate property
	 */
	public Date getFilingDate()
	{
		 return filingDate;
	}
   
	/**
	 * Access method for the instrumentTypeId property.
	 * 
	 * @return   the current value of the instrumentTypeId property
	 */
	public String getInstrumentTypeId()
	{
		 return instrumentTypeId;
	}

	/**
	 * Sets the value of the instrumentTypeId property.
	 * 
	 * @param anInstrumentTypeId the new value of the instrumentTypeId property
	 */
	public void setInstrumentTypeId(String anInstrumentTypeId)
	{
		 instrumentTypeId = anInstrumentTypeId;
	}

	/**
	 * Access method for the Primary Key for an Out Of County Case.
	 * 
	 * @return   the Primary Key for an Out Of County Case
	 */
	public String getPrimaryKey()
	{
		 return cdi+caseNum;
	}

	/**
	 * Access method for the courtNum property.
	 * 
	 * @return   the current value of the courtNum property
	 */
	public String getCourtNum()
	{
		 return courtNum;
	}
   
	/**
	 * Sets the value of the courtNum property.
	 * 
	 * @param aCourtNum the new value of the courtNum property
	 */
	public void setCourtNum(String aCourtNum)
	{
		 courtNum = aCourtNum;
	}

	/**
	 * Access method for the connectionId property.
	 * 
	 * @return   the current value of the connectionId property
	 */
	public String getConnectionId()
	{
		return connectionId;
	}

	/**
	 * Sets the value of the connectionId property.
	 * 
	 * @param aConnectionId the new value of the connectionId property
	 */
	public void setConnectionId(String aConnectionId)
	{
		connectionId = aConnectionId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object aCase) {
		if (aCase instanceof OutOfCountyCaseTO)
		{
			// Use Case number for comparison
			return getCaseNum().compareTo(((OutOfCountyCaseTO)aCase).getCaseNum());
		}
		else
		{
			throw new IllegalArgumentException("Must comapre to another OutOfCountyCaseTO object.");
		}
	}
}
