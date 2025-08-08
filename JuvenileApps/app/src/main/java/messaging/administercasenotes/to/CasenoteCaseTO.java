/*
 * Transfer object for case data pertaining to creation and viewing of casenotes.
 */
package messaging.administercasenotes.to;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jmcnabb
 *
 */
public class CasenoteCaseTO implements Serializable
{

	private String caseNum;
	private String cdi="";
	private String courtNum;
	private String supervisionPeriodId;
	private Date supervisionPeriodBeginDate;
	private Date supervisionPeriodEndDate;
	private Date caseSupervisionPeriodBeginDate;
	private Date caseSupervisionPeriodEndDate;
	private String offenseCodeId;
	private String supOrderId="";
	private String superviseeName="";
	
	
	/**
	 * @return Returns the caseNum.
	 */
	public String getCaseNum()
	{
		return caseNum;
	}
	
	/**
	 * @return Returns the courtNum.
	 */
	public String getCourtNum()
	{
		return courtNum;
	}
	
	/**
	 * @return Returns the supervisionPeriodId.
	 */
	public String getSupervisionPeriodId()
	{
		return supervisionPeriodId;
	}
	
	/**
	 * @return Returns the caseSupervisionPeriodBeginDate.
	 */
	public Date getCaseSupervisionPeriodBeginDate()
	{
		return caseSupervisionPeriodBeginDate;
	}
	
	/**
	 * @return Returns the caseSupervisionPeriodEndDate.
	 */
	public Date getCaseSupervisionPeriodEndDate()
	{
		return caseSupervisionPeriodEndDate;
	}
	
	/**
	 * @return the offenseCodeId
	 */
	public String getOffenseCodeId() {
		return offenseCodeId;
	}

	/**
	 * @return Returns the supervisionPeriodBeginDate.
	 */
	public Date getSupervisionPeriodBeginDate()
	{
		return supervisionPeriodBeginDate;
	}
	
	/**
	 * @return Returns the supervisionPeriodEndDate.
	 */
	public Date getSupervisionPeriodEndDate()
	{
		return supervisionPeriodEndDate;
	}
	
	/**
	 * @param aCaseNum The caseNum to set.
	 */
	public void setCaseNum(String aCaseNum)
	{
		this.caseNum = aCaseNum;
	}
	
	/**
	 * @param aCourtNum The courtNum to set.
	 */
	public void setCourtNum(String aCourtNum)
	{
		this.courtNum = aCourtNum;
	}
	
	/**
	 * @param caseSupervisionPeriodBeginDate The caseSupervisionPeriodBeginDate to set.
	 */
	public void setCaseSupervisionPeriodBeginDate(Date caseSupervisionPeriodBeginDate)
	{
		this.caseSupervisionPeriodBeginDate = caseSupervisionPeriodBeginDate;
	}
	
	/**
	 * @param caseSupervisionPeriodEndDate The caseSupervisionPeriodEndDate to set.
	 */
	public void setCaseSupervisionPeriodEndDate(Date caseSupervisionPeriodEndDate)
	{
		this.caseSupervisionPeriodEndDate = caseSupervisionPeriodEndDate;
	}

	/**
	 * @param offenseCodeId the offenseCodeId to set
	 */
	public void setOffenseCodeId(String offenseCodeId) {
		this.offenseCodeId = offenseCodeId;
	}
	
	/**
	 * @param supervisionPeriodId The supervisionPeriodId to set.
	 */
	public void setSupervisionPeriodId(String supervisionPeriodId)
	{
		this.supervisionPeriodId = supervisionPeriodId;
	}
	/**
	 * @param aDate The supervisionPeriodBeginDate to set.
	 */
	public void setSupervisionPeriodBeginDate(Date aDate)
	{
		this.supervisionPeriodBeginDate = aDate;
	}
	
	/**
	 * @param aDate The supervisionPeriodEndDate to set.
	 */
	public void setSupervisionPeriodEndDate(Date aDate)
	{
		this.supervisionPeriodEndDate = aDate;
	}
	/**
	 * @return Returns the cdi.
	 */
	public String getCdi() {
		return cdi;
	}
	/**
	 * @param cdi The cdi to set.
	 */
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
	/**
	 * @return Returns the supOrderId.
	 */
	public String getSupOrderId() {
		return supOrderId;
	}
	/**
	 * @param supOrderId The supOrderId to set.
	 */
	public void setSupOrderId(String supOrderId) {
		this.supOrderId = supOrderId;
	}
	/**
	 * @return the superviseeName
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}

	/**
	 * @param superviseeName the superviseeName to set
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}
}
