package pd.supervision.legacyupdates.entities;

import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * This attribute represents the legacy POI code.
 */
public class SupervisoryFees extends PersistentObject
{
	/**
	 * This attribute represents the legacy POI code.
	 */
	private String probationOfficerIndicator;
	private String caseNumber;
	private String cdi;
	private String officerNumber;
	private String opId;
	private String seqNum;
	
	
	public String getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String update()
	{
		new Home().bind(this);
	    return this.getSeqNum();
	}

	/**
	 * @return the officerNumber
	 */
	public String getOfficerNumber() {
		fetch();
		return officerNumber;
	}

	/**
	 * @param officerNumber the officerNumber to set
	 */
	public void setOfficerNumber(String officerNumber) {
		if (this.officerNumber == null || !this.officerNumber.equals(officerNumber))
		{
			markModified();
		}
		this.officerNumber = officerNumber;
	}
	
	/**
	 * 
	 * @return Returns the caseNumber.
	 */
	public String getCaseNumber()
	{
		fetch();
		return caseNumber;
	}

	/**
	 * 
	 * @param caseNumber
	 The caseNumber to set.
	 */
	public void setCaseNumber(String caseNumber)
	{
		if (this.caseNumber == null || !this.caseNumber.equals(caseNumber))
		{
			markModified();
		}
		this.caseNumber = caseNumber;
	}

	/**
	 * 
	 * @return Returns the cdi.
	 */
	public String getCdi()
	{
		fetch();
		return cdi;
	}

	/**
	 * 
	 * @param cdi
	 The cdi to set.
	 */
	public void setCdi(String cdi)
	{
		if (this.cdi == null || !this.cdi.equals(cdi))
		{
			markModified();
		}
		this.cdi = cdi;
	}

	/**
	 * 
	 * @return Returns the probationOfficerIndicator.
	 */
	public String getProbationOfficerIndicator()
	{
		fetch();
		return probationOfficerIndicator;
	}

	/**
	 * 
	 * @param probationOfficerIndicator
	 The probationOfficerIndicator to set.
	 */
	public void setProbationOfficerIndicator(String probationOfficerIndicator)
	{
		if (this.probationOfficerIndicator == null || !this.probationOfficerIndicator.equals(probationOfficerIndicator))
		{
			markModified();
		}
		this.probationOfficerIndicator = probationOfficerIndicator;
	}
}
