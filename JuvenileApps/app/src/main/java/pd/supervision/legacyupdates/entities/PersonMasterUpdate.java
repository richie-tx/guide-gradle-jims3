package pd.supervision.legacyupdates.entities;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;

import pd.supervision.legacyupdates.LegacyUpdateLog;

/**
 * This attribute represents the legacy POI code.
 */
public class PersonMasterUpdate extends PersistentObject
{
	/**
	 * This attribute represents the legacy POI code.
	 */
	private String probationOfficerIndicator;
	private String caseNumber;
	private String cdi;
	private Date transactionDate;
	private String transDate;
	private String officerNumber;
	private String opId;
	
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
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
	 * @return the transDate
	 */
	public String getTransDate() {
		fetch();
		return transDate;
	}
	/**
	 * @param transDate the transDate to set
	 */
	public void setTransDate(String transDate) {
		if (this.transDate == null || !this.transDate.equals(transDate))
		{
			markModified();
		}
		this.transDate = transDate;
	}
	/**
	 * @return the caseNumber
	 */
	public String getCaseNumber() {
		fetch();
		return caseNumber;
	}
	/**
	 * @return the cdi
	 */
	public String getCdi() {
		fetch();
		return cdi;
	}
	/**
	 * @return the probationOfficerIndicator
	 */
	public String getProbationOfficerIndicator() {
		fetch();
		return probationOfficerIndicator;
	}
	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		fetch();
		return transactionDate;
	}
	/**
	 * @param caseNumber the caseNumber to set
	 */
	public void setCaseNumber(String caseNumber) {
		if (this.caseNumber == null || !this.caseNumber.equals(caseNumber))
		{
			markModified();
		}
		this.caseNumber = caseNumber;
	}
	/**
	 * @param cdi the cdi to set
	 */
	public void setCdi(String cdi) {
		if (this.cdi == null || !this.cdi.equals(cdi))
		{
			markModified();
		}
		this.cdi = cdi;
	}
	/**
	 * @param probationOfficerIndicator the probationOfficerIndicator to set
	 */
	public void setProbationOfficerIndicator(String probationOfficerIndicator) {
		if (this.probationOfficerIndicator == null || !this.probationOfficerIndicator.equals(probationOfficerIndicator))
		{
			markModified();
		}
		this.probationOfficerIndicator = probationOfficerIndicator;
	}
	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		if (this.transactionDate == null || !this.transactionDate.equals(transactionDate))
		{
			markModified();
		}
		this.transactionDate = transactionDate;
	}
	
	public String update() throws Exception
	{
		new Home().bind(this);
	    return this.getOID();
	}
}
