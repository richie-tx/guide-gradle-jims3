package pd.supervision.legacyupdates.entities;

import java.util.Date;

import pd.supervision.legacyupdates.LegacyUpdateLog;

import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * This attribute represents the legacy POI code.
 */
public class SupervisingOfficer extends PersistentObject
{
	/**
	 * This attribute represents the legacy POI code.
	 */
	private String probationOfficerIndicator;
	private Date officerBeginDate;
	private String caseNumber;
	private String cdi;
	private String officerNumber;
	private String opId; 
	private String seqNum;
	
	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	/**
	 * Properties for legacyAssignmentLog
	 */
	private LegacyUpdateLog legacyAssignmentLog = null;
	private String legacyAssignmentLogId;

	/**
	 * 
	 * @roseuid 4761AA860378
	 */
	public SupervisingOfficer()
	{
	}

	public String update() throws Exception
	{
		new Home().bind(this);
	    return this.getSeqNum();
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
	 * @return Returns the officerBeginDate.
	 */
	public Date getOfficerBeginDate()
	{
		fetch();
		return officerBeginDate;
	}

	/**
	 * 
	 * @param officerBeginDate
	 The officerBeginDate to set.
	 */
	public void setOfficerBeginDate(Date officerBeginDate)
	{
		if (this.officerBeginDate == null || !this.officerBeginDate.equals(officerBeginDate))
		{
			markModified();
		}
		this.officerBeginDate = officerBeginDate;
	}

	/**
	 * 
	 * @return Returns the officerNumber.
	 */
	public String getOfficerNumber()
	{
		fetch();
		return officerNumber;
	}

	/**
	 * 
	 * @param officerNumber
	 The officerNumber to set.
	 */
	public void setOfficerNumber(String officerNumber)
	{
		if (this.officerNumber == null || !this.officerNumber.equals(officerNumber))
		{
			markModified();
		}
		this.officerNumber = officerNumber;
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

	/**
	 * Set the reference value to class :: pd.supervision.legacyupdates.LegacyUpdateLog
	 */
	public void setLegacyAssignmentLogId(String legacyAssignmentLogId)
	{
		if (this.legacyAssignmentLogId == null || !this.legacyAssignmentLogId.equals(legacyAssignmentLogId))
		{
			markModified();
		}
		legacyAssignmentLog = null;
		this.legacyAssignmentLogId = legacyAssignmentLogId;
	}

	/**
	 * Get the reference value to class :: pd.supervision.legacyupdates.LegacyUpdateLog
	 */
	public String getLegacyAssignmentLogId()
	{
		fetch();
		return legacyAssignmentLogId;
	}

	/**
	 * Initialize class relationship to class pd.supervision.legacyupdates.LegacyUpdateLog
	 */
	private void initLegacyAssignmentLog()
	{
		if (legacyAssignmentLog == null)
		{
			legacyAssignmentLog = (LegacyUpdateLog) new mojo.km.persistence.Reference(
					legacyAssignmentLogId, LegacyUpdateLog.class)
					.getObject();
		}
	}

	/**
	 * Gets referenced type pd.supervision.legacyupdates.LegacyUpdateLog
	 */
	public LegacyUpdateLog getLegacyAssignmentLog()
	{
		initLegacyAssignmentLog();
		return legacyAssignmentLog;
	}

	/**
	 * set the type reference for class member legacyAssignmentLog
	 */
	public void setLegacyAssignmentLog(LegacyUpdateLog legacyAssignmentLog)
	{
		if (this.legacyAssignmentLog == null || !this.legacyAssignmentLog.equals(legacyAssignmentLog))
		{
			markModified();
		}
		if (legacyAssignmentLog.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(legacyAssignmentLog);
		}
		setLegacyAssignmentLogId("" + legacyAssignmentLog.getOID());
		this.legacyAssignmentLog = (LegacyUpdateLog) new mojo.km.persistence.Reference(
				legacyAssignmentLog).getObject();
	}

	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}

	public String getSeqNum() {
		return seqNum;
	}
}
