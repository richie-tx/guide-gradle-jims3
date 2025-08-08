package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
 * 
 * @roseuid 462D0565038F
 */
public class JuvenileHealthIssue extends PersistentObject
{
	private JuvenileHealthIssue[] healthIssues;
	/**
	 * Properties for issue
	 */
	private Code issue = null;
	/**
	 * Properties for issueStatus
	 */
	private Code issueStatus = null;
	/**
	 * Properties for conditionSeverity
	 */
	private Code conditionSeverity = null;
	/**
	 * Properties for conditionLevel
	 */
	private Code conditionLevel = null;
	private String issueId;
	private String issueStatusId;
	private String conditionSeverityId;
	private String conditionLevelId;
	private Date entryDate;
	private String healthIssueId;
	private String juvenileNum;
	
	private Code healthStatus = null;
	private String healthStatusId;
	private String modificationReason;
	
	/**
	 * 
	 * @roseuid 462D0565038F
	 */
	public JuvenileHealthIssue()
	{
	}

	/**
	 * @param event
	 * @return
	 * @roseuid 45AF7A0A0190
	 */
	static public JuvenileHealthIssue find(String oid)
	{
		IHome home = new Home();
		JuvenileHealthIssue juvenileHealthIssue = (JuvenileHealthIssue) home.find(oid, JuvenileHealthIssue.class);
		return juvenileHealthIssue;
	}
	/**
	 * @param event
	 * @return
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileHealthIssue.class);
	}
	
	
	/**
	 * @roseuid 45AF7A0A0190
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}

	/**
	 * @roseuid 45AF7A0A0190
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
		{
			markModified();
		}
		this.juvenileNum = juvenileNum;
	}

	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setIssueId(String issueId)
	{
		if (this.issueId == null || !this.issueId.equals(issueId))
		{
			markModified();
		}
		issue = null;
		this.issueId = issueId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getIssueId()
	{
		fetch();
		return issueId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initIssue()
	{
		if (issue == null)
		{
			issue = (Code) new mojo.km.persistence.Reference(issueId, Code.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getIssue()
	{
		fetch();
		initIssue();
		return issue;
	}

	/**
	 * set the type reference for class member issue
	 */
	public void setIssue(Code issue)
	{
		if (this.issue == null || !this.issue.equals(issue))
		{
			markModified();
		}
		if (issue.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(issue);
		}
		setIssueId("" + issue.getOID());
		this.issue = (Code) new mojo.km.persistence.Reference(issue).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setIssueStatusId(String issueStatusId)
	{
		if (this.issueStatusId == null || !this.issueStatusId.equals(issueStatusId))
		{
			markModified();
		}
		issueStatus = null;
		this.issueStatusId = issueStatusId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getIssueStatusId()
	{
		fetch();
		return issueStatusId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initIssueStatus()
	{
		if (issueStatus == null)
		{
			issueStatus = (Code) new mojo.km.persistence.Reference(issueStatusId, Code.class)
					.getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getIssueStatus()
	{
		fetch();
		initIssueStatus();
		return issueStatus;
	}

	/**
	 * set the type reference for class member issueStatus
	 */
	public void setIssueStatus(Code issueStatus)
	{
		if (this.issueStatus == null || !this.issueStatus.equals(issueStatus))
		{
			markModified();
		}
		if (issueStatus.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(issueStatus);
		}
		setIssueStatusId("" + issueStatus.getOID());
		this.issueStatus = (Code) new mojo.km.persistence.Reference(issueStatus).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setConditionSeverityId(String conditionSeverityId)
	{
		if (this.conditionSeverityId == null || !this.conditionSeverityId.equals(conditionSeverityId))
		{
			markModified();
		}
		conditionSeverity = null;
		this.conditionSeverityId = conditionSeverityId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getConditionSeverityId()
	{
		fetch();
		return conditionSeverityId;
	}

	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initConditionSeverity()
	{
		if (conditionSeverity == null)
		{
			conditionSeverity = (Code) new mojo.km.persistence.Reference(conditionSeverityId,
					Code.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getConditionSeverity()
	{
		fetch();
		initConditionSeverity();
		return conditionSeverity;
	}

	/**
	 * set the type reference for class member conditionSeverity
	 */
	public void setConditionSeverity(Code conditionSeverity)
	{
		if (this.conditionSeverity == null || !this.conditionSeverity.equals(conditionSeverity))
		{
			markModified();
		}
		if (conditionSeverity.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(conditionSeverity);
		}
		setConditionSeverityId("" + conditionSeverity.getOID());
		this.conditionSeverity = (Code) new mojo.km.persistence.Reference(conditionSeverity).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setConditionLevelId(String conditionLevelId)
	{
		if (this.conditionLevelId == null || !this.conditionLevelId.equals(conditionLevelId))
		{
			markModified();
		}
		conditionLevel = null;
		this.conditionLevelId = conditionLevelId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getConditionLevelId()
	{
		fetch();
		return conditionLevelId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initConditionLevel()
	{
		if (conditionLevel == null)
		{
			conditionLevel = (Code) new mojo.km.persistence.Reference(conditionLevelId,
					Code.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getConditionLevel()
	{
		fetch();
		initConditionLevel();
		return conditionLevel;
	}

	/**
	 * set the type reference for class member conditionLevel
	 */
	public void setConditionLevel(Code conditionLevel)
	{
		if (this.conditionLevel == null || !this.conditionLevel.equals(conditionLevel))
		{
			markModified();
		}
		if (conditionLevel.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(conditionLevel);
		}
		setConditionLevelId("" + conditionLevel.getOID());
		this.conditionLevel = (Code) new mojo.km.persistence.Reference(conditionLevel).getObject();
	}
	

	/**
	 * 
	 * @return Returns the modificationReason.
	 */
	public String getModificationReason()
	{
		fetch();
		return modificationReason;
	}

	/**
	 * 
	 * @param modificationReason The modificationReason to set.
	 */
	public void setModificationReason(String modificationReason)
	{
		if (this.modificationReason == null || !this.modificationReason.equals(modificationReason))
		{
			markModified();
		}
		this.modificationReason = modificationReason;
	}	
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setHealthStatusId(String healthStatusId)
	{
		if (this.healthStatusId == null || !this.healthStatusId.equals(healthStatusId))
		{
			markModified();
		}
		healthStatus = null;
		this.healthStatusId = healthStatusId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getHealthStatusId()
	{
		fetch();
		return healthStatusId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initHealthStatus()
	{
		if (healthStatus == null)
		{
			healthStatus = (Code) new mojo.km.persistence.Reference(healthStatusId,
					Code.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getHealthStatus()
	{
		fetch();
		initHealthStatus();
		return healthStatus;
	}

	/**
	 * set the type reference for class member healthStatusId
	 */
	public void setHealthStatus(Code healthStatus)
	{
		if (this.healthStatus == null || !this.healthStatus.equals(healthStatus))
		{
			markModified();
		}
		if (healthStatus.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(healthStatus);
		}
		setHealthStatusId("" + healthStatus.getOID());
		this.healthStatus = (Code) new mojo.km.persistence.Reference(healthStatus).getObject();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() {
		fetch();
		return entryDate;
	}
	/**
	 * @param entryDate The entryDate to set.
	 */
	public void setEntryDate(Date entryDate) {
		if (this.entryDate == null || !this.entryDate.equals(entryDate))
		{
			markModified();
		}
		this.entryDate = entryDate;
	}
	/**
	 * @return Returns the healthIssueId.
	 */
	public String getHealthIssueId() {
		fetch();
		return healthIssueId;
	}
	/**
	 * @param healthIssueId The healthIssueId to set.
	 */
	public void setHealthIssueId(String healthIssueId) {
		if (this.healthIssueId == null || !this.healthIssueId.equals(healthIssueId))
		{
			markModified();
		}
		this.healthIssueId = healthIssueId;
	}
}
