//Source file: C:\\views\\dev\\app\\src\\messaging\\codetable\\UpdateCodeTableRecordEvent.java

package messaging.codetable;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class UpdateCodeTableRecordEvent extends RequestEvent
{
	public String codeId;
	public String code;
	public String codeTableName;
	public String description;
	public String status;
	public Date inactiveDate;
	public Date inactiveEffectiveDate;
	public Date activeDate;

	/**
	 * @roseuid 41642CD403E3
	 */
	public UpdateCodeTableRecordEvent()
	{

	}

	/**
	 * @param codeId
	 * @roseuid 41642C0F0133
	 */
	public void setCodeId(String codeId)
	{
		this.codeId = codeId;
	}

	/**
	 * @return String
	 * @roseuid 41642C0F0135
	 */
	public String getCodeId()
	{
		return this.codeId;
	}

	/**
	 * @param code
	 * @roseuid 41642C0F0137
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * @return String
	 * @roseuid 41642C0F0139
	 */
	public String getCode()
	{
		return this.code;
	}

	/**
	 * @param codeTableName
	 * @roseuid 41642C0F0144
	 */
	public void setCodeTableName(String codeTableName)
	{
		this.codeTableName = codeTableName;
	}

	/**
	 * @return String
	 * @roseuid 41642C0F0146
	 */
	public String getCodeTableName()
	{
		return this.codeTableName;
	}

	/**
	 * @param description
	 * @roseuid 41642C0F0148
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return String
	 * @roseuid 41642C0F0153
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * @param status
	 * @roseuid 41642C0F0155
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * @return String
	 * @roseuid 41642C0F0157
	 */
	public String getStatus()
	{
		return this.status;
	}

	/**
	 * @param inactiveDate
	 * @roseuid 41642C0F0163
	 */
	public void setInactiveDate(Date inactiveDate)
	{
		this.inactiveDate = inactiveDate;
	}

	/**
	 * @return Date
	 * @roseuid 41642C0F0165
	 */
	public Date getInactiveDate()
	{
		return this.inactiveDate;
	}

	/**
	 * @param inactiveEffectiveDate
	 * @roseuid 41642C0F0172
	 */
	public void setInactiveEffectiveDate(Date inactiveEffectiveDate)
	{
		this.inactiveEffectiveDate = inactiveEffectiveDate;
	}

	/**
	 * @return Date
	 * @roseuid 41642C0F0174
	 */
	public Date getInactiveEffectiveDate()
	{
		return this.inactiveEffectiveDate;
	}

	/**
	 * @param activeDate
	 * @roseuid 41642C0F0176
	 */
	public void setActiveDate(Date activeDate)
	{
		this.activeDate = activeDate;
	}

	/**
	 * @return Date
	 * @roseuid 41642C0F0178
	 */
	public Date getActiveDate()
	{
		return this.activeDate;
	}
}
