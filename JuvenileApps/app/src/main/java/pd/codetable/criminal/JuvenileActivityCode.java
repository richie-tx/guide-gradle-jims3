package pd.codetable.criminal;

import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * 
 * @roseuid 467FBB940205
 */
public class JuvenileActivityCode extends PersistentObject
{
	private String code;
	private String description;
	private String groupNum;
	private String caseStatusCode;
	private String dispositionInd;
	private String tJCColumnNum;
	private String cJISCode;
	private String project1GroupNum;

	/**
	 * 
	 * @roseuid 467FBB940205
	 */
	public JuvenileActivityCode()
	{
	}

	/**
	 * 
	 * @return Returns the caseStatusCode.
	 */
	public String getCaseStatusCode()
	{
		fetch();
		return caseStatusCode;
	}

	/**
	 * 
	 * @param caseStatusCode The caseStatusCode to set.
	 */
	public void setCaseStatusCode(String caseStatusCode)
	{
		if (this.caseStatusCode == null || !this.caseStatusCode.equals(caseStatusCode))
		{
			markModified();
		}
		this.caseStatusCode = caseStatusCode;
	}

	/**
	 * 
	 * @return Returns the cJISCode.
	 */
	public String getCJISCode()
	{
		fetch();
		return cJISCode;
	}

	/**
	 * 
	 * @param code The cJISCode to set.
	 */
	public void setCJISCode(String code)
	{
		if (this.cJISCode == null || !this.cJISCode.equals(code))
		{
			markModified();
		}
		cJISCode = code;
	}

	/**
	 * 
	 * @return Returns the code.
	 */
	public String getCode()
	{
		fetch();
		return code;
	}

	/**
	 * 
	 * @param code The code to set.
	 */
	public void setCode(String code)
	{
		if (this.code == null || !this.code.equals(code))
		{
			markModified();
		}
		this.code = code;
	}

	/**
	 * 
	 * @return Returns the description.
	 */
	public String getDescription()
	{
		fetch();
		return description;
	}

	/**
	 * 
	 * @param description The description to set.
	 */
	public void setDescription(String description)
	{
		if (this.description == null || !this.description.equals(description))
		{
			markModified();
		}
		this.description = description;
	}

	/**
	 * 
	 * @return Returns the dispositionInd.
	 */
	public String getDispositionInd()
	{
		fetch();
		return dispositionInd;
	}

	/**
	 * 
	 * @param dispositionInd The dispositionInd to set.
	 */
	public void setDispositionInd(String dispositionInd)
	{
		if (this.dispositionInd == null || !this.dispositionInd.equals(dispositionInd))
		{
			markModified();
		}
		this.dispositionInd = dispositionInd;
	}

	/**
	 * 
	 * @return Returns the groupNum.
	 */
	public String getGroupNum()
	{
		fetch();
		return groupNum;
	}

	/**
	 * 
	 * @param groupNum The groupNum to set.
	 */
	public void setGroupNum(String groupNum)
	{
		if (this.groupNum == null || !this.groupNum.equals(groupNum))
		{
			markModified();
		}
		this.groupNum = groupNum;
	}

	/**
	 * 
	 * @return Returns the project1GroupNum.
	 */
	public String getProject1GroupNum()
	{
		fetch();
		return project1GroupNum;
	}

	/**
	 * 
	 * @param project1GroupNum The project1GroupNum to set.
	 */
	public void setProject1GroupNum(String project1GroupNum)
	{
		if (this.project1GroupNum == null || !this.project1GroupNum.equals(project1GroupNum))
		{
			markModified();
		}
		this.project1GroupNum = project1GroupNum;
	}

	/**
	 * 
	 * @return Returns the tJCColumnNum.
	 */
	public String getTJCColumnNum()
	{
		fetch();
		return tJCColumnNum;
	}

	/**
	 * 
	 * @param columnNum The tJCColumnNum to set.
	 */
	public void setTJCColumnNum(String columnNum)
	{
		if (this.tJCColumnNum == null || !this.tJCColumnNum.equals(columnNum))
		{
			markModified();
		}
		tJCColumnNum = columnNum;
	}
	/**
	 * 
	 * @return JuvenileActivityCode object
	 * @param activityCode
	 * @roseuid 41ACA9680353
	 *Find JuvenileActivityCode object by codeId
	 */
	public static JuvenileActivityCode find(String activityCode)
	{
		return (JuvenileActivityCode) new Home().find(activityCode, JuvenileActivityCode.class);
	}
}
